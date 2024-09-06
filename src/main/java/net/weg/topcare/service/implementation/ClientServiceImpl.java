package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.client.*;
import net.weg.topcare.entity.Client;
import net.weg.topcare.entity.Address;
import net.weg.topcare.entity.Image;
import net.weg.topcare.entity.People;
import net.weg.topcare.exceptions.CPFAlreadyBeingUsedException;
import net.weg.topcare.repository.ClientRepository;
import net.weg.topcare.repository.AddressRepository;
import net.weg.topcare.repository.ImageRepository;
import net.weg.topcare.service.interfaces.ClientServiceInt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientServiceInt {
    private ClientRepository repository;
    private AddressRepository addressRepository;
    private ImageRepository imageRepository;

    @Override
    public Long register(ClientPostDTO clientDTO) throws CPFAlreadyBeingUsedException {
        Client cliente = new Client(clientDTO);

        if (repository.existsClientByCpf(cliente.getCpf())) {
            throw new CPFAlreadyBeingUsedException();
        }

        Address endereco = addressRepository.save(clientDTO.address());
        cliente.getAddress().add(endereco);
        cliente.setMainAddress(endereco);

        cliente = repository.save(cliente);
        return cliente.getId();
    };

    @Override
    public Boolean exists(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public ClientGetDTO editClientImages(Long id,MultipartFile profilePicture, MultipartFile banner) {
        Client client = repository.findById(id).orElseThrow();
        if (profilePicture == null) {
            client.setProfilePicture(client.getProfilePicture());
        } else {
            try {
                client.setProfilePicture(imageRepository.save(new Image(profilePicture)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (banner == null) {
            client.setBanner(client.getBanner());
        } else {
            try {
                client.setBanner(imageRepository.save(new Image(banner)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        repository.save(client);
        return client.toGetDTO();
    }
    @Override
    public Client putClient(ClientPutDTO clientPutDTO, Long id) {
        Client client = findOneClient(id);
        client.setEmail(clientPutDTO.email());
        client.setName(clientPutDTO.name());
        client.setPhone(clientPutDTO.phone());
        client.setBirthdate(clientPutDTO.birthdate());
        return repository.save(client);
    }

    @Override
    public Boolean changePassword(ClientPatchDTO dto, Long id) {
        Client client = findOneClient(id);
        if (dto.newPassword().equals(dto.confirmPassword())) {
            client.setPassword(dto.newPassword());
            repository.save(client);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteAccount(Long id) {
        Client client = findOneClient(id);
        if(client == null) return false;
        client.setEnabled(false);
        repository.save(client);
        return true;
    }

    @Override
    public Integer checkEmailAndCreateToken(ClientEmailDTO email, Long id) {
        Client client = findOneClient(id);
        String emailClient = email.email();
        System.out.println("Email: "+emailClient);
        System.out.println(" outro Email: "+email);
        if(client.getEmail().equals(emailClient)){
            Random random = new Random();
            int token = random.nextInt(5000 - 1000)+1000;
            System.out.println(token);
            return token;
        }
        return 0;
    }

    @Override
    public Boolean checkToken(ClientTokenDTO dto) {
        return dto.token().equals(dto.clientToken());
    }

    @Override
    public ClientGetDTO findOne(Long id) {
        Optional<Client> client = repository.findById(id);
        return client.map(Client::toGetDTO).orElse(null);
    }

    @Override
    public Client findOneClient(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<ClientGetDTO> findAll() {
        return repository.findAll().stream().map(Client::toGetDTO).toList();
    }
}

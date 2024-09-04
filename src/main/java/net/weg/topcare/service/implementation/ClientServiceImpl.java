package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.client.ClientGetDTO;
import net.weg.topcare.controller.dto.client.ClientPostDTO;
import net.weg.topcare.controller.dto.client.LoginDTO;
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
        if (profilePicture == null){
            client.setProfilePicture(client.getProfilePicture());
        } else {
            try {
                client.setProfilePicture(imageRepository.save(new Image(profilePicture)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (banner == null){
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
    public ClientGetDTO findOne(Long id) {
        Optional<Client> client = repository.findById(id);
        return client.map(Client::toGetDTO).orElse(null);
    }

    @Override
    public List<ClientGetDTO> findAll() {
        return repository.findAll().stream().map(Client::toGetDTO).toList();
    }
}

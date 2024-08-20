package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.client.*;
import net.weg.topcare.entity.Client;
import net.weg.topcare.entity.Address;
import net.weg.topcare.entity.People;
import net.weg.topcare.repository.ClientRepository;
import net.weg.topcare.repository.AddressRepository;
import net.weg.topcare.service.interfaces.ClientServiceInt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientServiceInt {
    private ClientRepository repository;
    private AddressRepository addressRepository;

    @Override
    public Long register(ClientPostDTO clientDTO) {
        Client cliente = new Client(clientDTO);

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
        if (client.getPassword().equals(dto.oldPassword())) {
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

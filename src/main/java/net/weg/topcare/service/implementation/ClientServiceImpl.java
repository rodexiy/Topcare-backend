package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.client.ClientGetDTO;
import net.weg.topcare.controller.dto.client.ClientPostDTO;
import net.weg.topcare.entity.Client;
import net.weg.topcare.entity.Address;
import net.weg.topcare.repository.ClientRepository;
import net.weg.topcare.repository.AddressRepository;
import net.weg.topcare.service.interfaces.ClientServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Boolean exists(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public ClientGetDTO findOne(Long id) {
        return repository.findById(id).get().toGetDTO();
    }

    @Override
    public List<ClientGetDTO> findAll() {
        return repository.findAll().stream().map(Client::toGetDTO).toList();
    }


}

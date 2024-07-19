package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.client.ClienteGetDTO;
import net.weg.topcare.controller.dto.client.ClientePostDTO;
import net.weg.topcare.entity.Client;
import net.weg.topcare.entity.Address;
import net.weg.topcare.repository.ClienteRepository;
import net.weg.topcare.repository.EnderecoRepository;
import net.weg.topcare.service.interfaces.ClientServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientServiceInt {
    private ClienteRepository repository;
    private EnderecoRepository enderecoRepository;

    @Override
    public Long register(ClientePostDTO clientDTO) {
        Client cliente = new Client(clientDTO);

        Address endereco = enderecoRepository.save(clientDTO.address());
        cliente.getAddress().add(endereco);
        cliente.setMainAddress(endereco);

        cliente = repository.save(cliente);
        return cliente.getId();
    };

    public Boolean exists(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public ClienteGetDTO findOne(Long id) {
        return repository.findById(id).get().toGetDTO();
    }

    @Override
    public List<ClienteGetDTO> findAll() {
        return repository.findAll().stream().map(Client::toGetDTO).toList();
    }


}

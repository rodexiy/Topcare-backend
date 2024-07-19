package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.cliente.ClienteGetDTO;
import net.weg.topcare.controller.dto.cliente.ClientePostDTO;
import net.weg.topcare.entity.Carrinho;
import net.weg.topcare.entity.Cliente;
import net.weg.topcare.entity.Endereco;
import net.weg.topcare.repository.ClienteRepository;
import net.weg.topcare.repository.EnderecoRepository;
import net.weg.topcare.service.interfaces.ClienteServiceInt;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteServiceInt {
    private ClienteRepository repository;
    private EnderecoRepository enderecoRepository;

    @Override
    public Long cadastrar(ClientePostDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO);

        Endereco endereco = enderecoRepository.save(clienteDTO.endereco());
        cliente.getEnderecos().add(endereco);
        cliente.setEnderecoPrincipal(endereco);

        cliente = repository.save(cliente);
        return cliente.getId();
    };

    public Boolean existe(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public ClienteGetDTO buscarUm(Long id) {
        return repository.findById(id).get().toGetDTO();
    }

    @Override
    public List<ClienteGetDTO> buscarTodos() {
        return repository.findAll().stream().map(Cliente::toGetDTO).toList();
    }


}

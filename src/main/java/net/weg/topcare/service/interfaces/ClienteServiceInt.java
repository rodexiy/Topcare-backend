package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.cliente.ClienteGetDTO;
import net.weg.topcare.controller.dto.cliente.ClientePostDTO;

import java.util.List;

public interface ClienteServiceInt {
    ClienteGetDTO cadastrar(ClientePostDTO clienteDTO);
    ClienteGetDTO buscarUm(Long id);
    List<ClienteGetDTO> buscarTodos();
    Boolean existe(String email);
}

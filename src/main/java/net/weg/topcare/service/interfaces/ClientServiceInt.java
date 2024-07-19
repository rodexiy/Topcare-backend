package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.client.ClienteGetDTO;
import net.weg.topcare.controller.dto.client.ClientePostDTO;

import java.util.List;

public interface ClientServiceInt {
    Long register(ClientePostDTO clientDTO);
    ClienteGetDTO findOne(Long id);
    List<ClienteGetDTO> findAll();
    Boolean exists(String email);
}

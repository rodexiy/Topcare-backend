package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.client.ClientGetDTO;
import net.weg.topcare.controller.dto.client.ClientPostDTO;
import net.weg.topcare.controller.dto.client.LoginDTO;

import java.util.List;

public interface ClientServiceInt {
    Long register(ClientPostDTO clientDTO);
    ClientGetDTO findOne(Long id);
    List<ClientGetDTO> findAll();
    Boolean exists(String email);
}

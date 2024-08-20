package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.client.*;
import net.weg.topcare.entity.Client;

import java.util.List;

public interface ClientServiceInt {
    Long register(ClientPostDTO clientDTO);
    ClientGetDTO findOne(Long id);
    Client findOneClient(Long id);
    List<ClientGetDTO> findAll();
    Boolean exists(String email);
    Client putClient(ClientPutDTO clientPutDTO, Long id);
    Boolean changePassword(ClientPatchDTO dto, Long id);
    Boolean deleteAccount(Long id);
}

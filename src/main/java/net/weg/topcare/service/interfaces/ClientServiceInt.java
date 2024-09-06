package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.client.*;
import net.weg.topcare.entity.Client;
import net.weg.topcare.controller.dto.client.ClientGetDTO;
import net.weg.topcare.controller.dto.client.ClientPostDTO;
import net.weg.topcare.controller.dto.client.LoginDTO;
import net.weg.topcare.exceptions.CPFAlreadyBeingUsedException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClientServiceInt {
    Long register(ClientPostDTO clientDTO) throws CPFAlreadyBeingUsedException;
    ClientGetDTO findOne(Long id);
    Client findOneClient(Long id);
    List<ClientGetDTO> findAll();
    Boolean exists(String email);

    ClientGetDTO editClientImages(Long id, MultipartFile profilePicture, MultipartFile banner);

    Client putClient(ClientPutDTO clientPutDTO, Long id);
    Boolean changePassword(ClientPatchDTO dto, Long id);
    Boolean deleteAccount(Long id);
    Integer checkEmailAndCreateToken(ClientEmailDTO email, Long id);
    Boolean checkToken(ClientTokenDTO dto);

}

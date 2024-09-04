package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.client.ClientGetDTO;
import net.weg.topcare.controller.dto.client.ClientPostDTO;
import net.weg.topcare.controller.dto.client.LoginDTO;
import net.weg.topcare.exceptions.CPFAlreadyBeingUsedException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClientServiceInt {
    Long register(ClientPostDTO clientDTO) throws CPFAlreadyBeingUsedException;
    ClientGetDTO findOne(Long id);
    List<ClientGetDTO> findAll();
    Boolean exists(String email);
    ClientGetDTO editClientImages(Long id, MultipartFile profilePicture, MultipartFile banner);
}

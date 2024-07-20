package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.client.ClientGetDTO;
import net.weg.topcare.controller.dto.client.LoginDTO;

public interface LoginServiceInt {
    Long login(LoginDTO loginDTO);

    String getLoginContext(Long id);

}

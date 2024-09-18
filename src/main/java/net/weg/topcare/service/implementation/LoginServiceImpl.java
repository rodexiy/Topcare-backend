package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.client.ClientGetDTO;
import net.weg.topcare.controller.dto.client.LoginDTO;
import net.weg.topcare.entity.Client;
import net.weg.topcare.repository.ClientRepository;
import net.weg.topcare.repository.EmployeeRepository;
import net.weg.topcare.service.interfaces.LoginServiceInt;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginServiceInt {
    EmployeeRepository employeeRepository;
    ClientRepository clientRepository;

    @Override
    public Long login(LoginDTO loginDTO) {
        Client client = clientRepository.findByEmail(loginDTO.email());
        Client employee = clientRepository.findByEmail(loginDTO.email());

        if (client != null) {
            if(client.getEnabled()){
                if (client.getPassword().equals(loginDTO.password())) {
                    return client.getId();
                }
            }

        }else if (employee != null) {
            if(employee.getEnabled()){
                if (employee.getPassword().equals(loginDTO.password())) {
                    return employee.getId();
                }
            }

        }

        return null;
    }

    @Override
    public String getLoginContext(Long id) {
        if (clientRepository.existsById(id)) {
            return "client";
        }else if (employeeRepository.existsById(id)) {
            return "employee";
        }

        return null;
    }

}

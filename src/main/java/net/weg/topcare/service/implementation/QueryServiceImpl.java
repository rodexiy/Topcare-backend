package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.query.QueryMinimalGetDTO;
import net.weg.topcare.controller.dto.query.QueryPostDTO;
import net.weg.topcare.entity.Client;
import net.weg.topcare.entity.Scheduling;
import net.weg.topcare.repository.ClientRepository;
import net.weg.topcare.repository.SchedulingRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QueryServiceImpl {

    private SchedulingRepository schedulingRepository;
    private ClientRepository clientRepository;

    public void addQuery(@RequestBody QueryPostDTO dto) {
        Optional<Scheduling> query = schedulingRepository.findById(dto.clientId());
        Optional<Client> client = clientRepository.findById(dto.clientId());

        if (client.isPresent() && query.isPresent()) {
            Client cliente = client.get();
            Scheduling scheduling = query.get();

            cliente.addQuery(scheduling);
            clientRepository.save(cliente);
        }
    }


}

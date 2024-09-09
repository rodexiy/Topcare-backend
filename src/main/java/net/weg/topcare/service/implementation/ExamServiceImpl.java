package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.client.ClientGetIdDTO;
import net.weg.topcare.controller.dto.exam.ExamPostDTO;
import net.weg.topcare.entity.Client;
import net.weg.topcare.entity.Scheduling;
import net.weg.topcare.enums.ServiceArea;
import net.weg.topcare.repository.ClientRepository;
import net.weg.topcare.repository.SchedulingRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Serviço de implementação de consultas.
 *
 * @author Kaue Correa Colling
 */
@Service
@AllArgsConstructor
public class ExamServiceImpl {

    private SchedulingRepository schedulingRepository;
    private ClientRepository clientRepository;

    public void addExam(ExamPostDTO dto) {
        Optional<Client> clientOptional = clientRepository.findById(dto.clientId());

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();

            Scheduling scheduling = new Scheduling();
            scheduling.setSchedulingNumber(dto.schedulingNumber());
            scheduling.setServiceArea(dto.serviceArea());
            scheduling.setSubsidiary(dto.subsidiary());
            scheduling.setScheduledDate(dto.scheduledDate());
            scheduling.setClient(client);
            scheduling.setPets(dto.pets());

            schedulingRepository.save(scheduling);
        }
    }

    public List<Scheduling> getNextExam(ClientGetIdDTO clientGetIdDTO) {
        LocalDateTime now = LocalDateTime.now();
        return schedulingRepository.findByClientIdAndScheduledDateAfter(clientGetIdDTO.id(), now);
    }

    public List<Scheduling> getNextExamByServiceArea(ClientGetIdDTO clientGetIdDTO, ServiceArea serviceArea) {
        LocalDateTime now = LocalDateTime.now();
        return schedulingRepository.findByClientIdAndScheduledDateAfterAndServiceArea(clientGetIdDTO.id(), now, serviceArea);
    }

    public List<Scheduling> getAllExam() {
        return schedulingRepository.findAll();
    }

    public List<Scheduling> getAllExamByServiceArea(ServiceArea serviceArea) {
        return schedulingRepository.findByServiceArea(serviceArea);
    }

    public Scheduling getExamByID(Long id) {
        return schedulingRepository.findById(id).get();
    }

    public List<Scheduling> getExamsByClientId(ClientGetIdDTO clientGetIdDTO) {
        return schedulingRepository.findByClientId(clientGetIdDTO.id());
    }

    public List<Scheduling> getExamsByClientIdAndServiceArea(ClientGetIdDTO clientGetIdDTO, ServiceArea serviceArea) {
        return schedulingRepository.findByClientIdAndServiceArea(clientGetIdDTO.id(), serviceArea);
    }

    public Scheduling getExamBySchedulingNumber(String schedulingNumber) {
        return schedulingRepository.findBySchedulingNumber(schedulingNumber);
    }

    public List<net.weg.topcare.entity.Service> getServicesByPetId(Long petId) {
        return schedulingRepository.findServicesByPetId(petId);
    }

}

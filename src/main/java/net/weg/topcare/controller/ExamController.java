package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.client.ClientGetIdDTO;
import net.weg.topcare.controller.dto.exam.ExamMaximalGetDTO;
import net.weg.topcare.controller.dto.exam.ExamMinimalGetDTO;
import net.weg.topcare.controller.dto.exam.ExamPostDTO;
import net.weg.topcare.controller.dto.service.ServiceGetRequestDTO;
import net.weg.topcare.entity.Scheduling;
import net.weg.topcare.entity.Service;
import net.weg.topcare.enums.ServiceArea;
import net.weg.topcare.service.implementation.ExamServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador responsável por gerenciar as consultas.
 *
 * @author Kaue Correa Colling
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/exam")
@AllArgsConstructor
public class ExamController {

    /**
     * Serviço de implementação de consultas.
     */
    private final ExamServiceImpl queryService;

    /**
     * Adiciona uma nova consulta.
     *
     * @param dto Objeto de transferência de dados da consulta.
     */
    @PostMapping
    public void addExam(@RequestBody ExamPostDTO dto) {
        queryService.addExam(dto);
    }

    @GetMapping("/nextExam/{clientId}")
    public List<ExamMinimalGetDTO> getNextExam(@PathVariable Long clientId) {
        ClientGetIdDTO clientGetIdDTO = new ClientGetIdDTO(clientId);
        List<Scheduling> nextQueries = queryService.getNextExam(clientGetIdDTO);
        return nextQueries.stream()
                .map(Scheduling::convertToQueryMinimalGetDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/nextExam/{clientId}/veterinaria")
    public List<ExamMinimalGetDTO> getNextExamVeterinaria(@PathVariable Long clientId) {
        ClientGetIdDTO clientGetIdDTO = new ClientGetIdDTO(clientId);
        List<Scheduling> nextQueries = queryService.getNextExamByServiceArea(clientGetIdDTO, ServiceArea.VETERINARIA);
        return nextQueries.stream()
                .map(Scheduling::convertToQueryMinimalGetDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/nextExam/{clientId}/servico")
    public List<ExamMinimalGetDTO> getNextExamServico(@PathVariable Long clientId) {
        ClientGetIdDTO clientGetIdDTO = new ClientGetIdDTO(clientId);
        List<Scheduling> nextQueries = queryService.getNextExamByServiceArea(clientGetIdDTO, ServiceArea.SERVICO);
        return nextQueries.stream()
                .map(Scheduling::convertToQueryMinimalGetDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/allExam")
    public List<ExamMinimalGetDTO> getAllExam() {
        List<Scheduling> exams = queryService.getAllExam();
        return exams.stream()
                .map(Scheduling::convertToQueryMinimalGetDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/allExam/veterinaria")
    public List<ExamMinimalGetDTO> getAllExamVeterinaria() {
        List<Scheduling> exams = queryService.getAllExamByServiceArea(ServiceArea.VETERINARIA);
        return exams.stream()
                .map(Scheduling::convertToQueryMinimalGetDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/allExam/servico")
    public List<ExamMinimalGetDTO> getAllExamServico() {
        List<Scheduling> exams = queryService.getAllExamByServiceArea(ServiceArea.SERVICO);
        return exams.stream()
                .map(Scheduling::convertToQueryMinimalGetDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{examID}")
    public ResponseEntity<ExamMaximalGetDTO> getExamByID(@PathVariable Long examID) {
        Scheduling query = queryService.getExamByID(examID);
        return ResponseEntity.ok(query.convertToQueryMaximalGetDTO());
    }

    @GetMapping("/allExam/{clientId}")
    public List<ExamMinimalGetDTO> getExamsByClientId(@PathVariable Long clientId) {
        ClientGetIdDTO clientGetIdDTO = new ClientGetIdDTO(clientId);
        List<Scheduling> exams = queryService.getExamsByClientId(clientGetIdDTO);
        return exams.stream()
                .map(Scheduling::convertToQueryMinimalGetDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/allExam/{clientId}/veterinaria")
    public List<ExamMinimalGetDTO> getExamsByClientIdVeterinaria(@PathVariable Long clientId) {
        ClientGetIdDTO clientGetIdDTO = new ClientGetIdDTO(clientId);
        List<Scheduling> exams = queryService.getExamsByClientIdAndServiceArea(clientGetIdDTO, ServiceArea.VETERINARIA);
        return exams.stream()
                .map(Scheduling::convertToQueryMinimalGetDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/allExam/{clientId}/servico")
    public List<ExamMinimalGetDTO> getExamsByClientIdServico(@PathVariable Long clientId) {
        ClientGetIdDTO clientGetIdDTO = new ClientGetIdDTO(clientId);
        List<Scheduling> exams = queryService.getExamsByClientIdAndServiceArea(clientGetIdDTO, ServiceArea.SERVICO);
        return exams.stream()
                .map(Scheduling::convertToQueryMinimalGetDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/SchedulingNumber/{schedulingNumber}")
    public ResponseEntity<ExamMaximalGetDTO> getExamBySchedulingNumber(@PathVariable String schedulingNumber) {
        Scheduling query = queryService.getExamBySchedulingNumber(schedulingNumber);
        return ResponseEntity.ok(query.convertToQueryMaximalGetDTO());
    }

    @GetMapping("/SchedulingNumberMinimal/{schedulingNumber}")
    public ResponseEntity<ExamMinimalGetDTO> getExamBySchedulingNumberMinimal(@PathVariable String schedulingNumber) {
        Scheduling query = queryService.getExamBySchedulingNumber(schedulingNumber);
        return ResponseEntity.ok(query.convertToQueryMinimalGetDTO());
    }

    @GetMapping("/servicesByPet/{petId}")
    public List<ServiceGetRequestDTO> getServicesByPetId(@PathVariable Long petId) {
        return queryService.getServicesByPetId(petId)
                .stream()
                .map(ServiceGetRequestDTO::new)
                .collect(Collectors.toList());
    }



}
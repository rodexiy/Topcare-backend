package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.client.ClientGetIdDTO;
import net.weg.topcare.controller.dto.exam.ExamMaximalGetDTO;
import net.weg.topcare.controller.dto.exam.ExamMinimalGetDTO;
import net.weg.topcare.controller.dto.exam.ExamPostDTO;
import net.weg.topcare.entity.Scheduling;
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

    /**
     * Retorna as próximas consultas agendadas.
     *
     * @return Lista de objetos de transferência de dados das consultas.
     */
    @GetMapping("/nextExam/{clientId}")
    public List<ExamMinimalGetDTO> getNextExam(@PathVariable Long clientId) {
        ClientGetIdDTO clientGetIdDTO = new ClientGetIdDTO(clientId);
        List<Scheduling> nextQueries = queryService.getNextExam(clientGetIdDTO);
        return nextQueries.stream()
                .map(Scheduling::convertToQueryMinimalGetDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retorna todas as consultas agendadas.
     *
     * @return Lista de objetos de transferência de dados das consultas.
     */
    @GetMapping("/allExam")
    public List<ExamMinimalGetDTO> getAllExam() {
        List<Scheduling> exams = queryService.getAllExam();
        return exams.stream()
                .map(Scheduling::convertToQueryMinimalGetDTO)
                .collect(Collectors.toList());
    }



    /**
     * Retorna todas as consultas agendadas.
     *
     * @return Lista de objetos de transferência de dados das consultas.
     */
    @GetMapping("/{examID}")
    public ResponseEntity<ExamMaximalGetDTO> getExamByID(@PathVariable Long examID) {
        Scheduling query = queryService.getExamByID(examID);
        return ResponseEntity.ok(query.convertToQueryMaximalGetDTO());
    }

    /**
     * Retorna todas as consultas de um cliente específico.
     *
     * @param clientId ID do cliente.
     * @return Lista de objetos de transferência de dados das consultas.
     */
    @GetMapping("/allExam/{clientId}")
    public List<ExamMinimalGetDTO> getExamsByClientId(@PathVariable Long clientId) {
        ClientGetIdDTO clientGetIdDTO = new ClientGetIdDTO(clientId);
        List<Scheduling> exams = queryService.getExamsByClientId(clientGetIdDTO);
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

}
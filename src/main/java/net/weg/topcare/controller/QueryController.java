package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.query.QueryMaximalGetDTO;
import net.weg.topcare.controller.dto.query.QueryMinimalGetDTO;
import net.weg.topcare.controller.dto.query.QueryPostDTO;
import net.weg.topcare.entity.Scheduling;
import net.weg.topcare.service.implementation.QueryServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controlador responsável por gerenciar as consultas.
 *
 * @author Kaue Correa Colling
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/query")
@AllArgsConstructor
public class QueryController {

    /**
     * Serviço de implementação de consultas.
     */
    private QueryServiceImpl queryService;

    /**
     * Adiciona uma nova consulta.
     *
     * @param dto Objeto de transferência de dados da consulta.
     */
    @PostMapping
    public void addQuery(@RequestBody QueryPostDTO dto) {
        queryService.addQuery(dto);
    }

    /**
     * Retorna as próximas consultas agendadas.
     *
     * @return Lista de objetos de transferência de dados das consultas.
     */
    @GetMapping("/next")
    public List<QueryMinimalGetDTO> getNextQueries() {
        List<Scheduling> nextQueries = queryService.getNextQueries();
        return nextQueries.stream()
                .map(this::convertToQueryMinimalGetDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retorna todas as consultas agendadas.
     *
     * @return Lista de objetos de transferência de dados das consultas.
     */
    @GetMapping("/allQuery")
    public List<QueryMinimalGetDTO> getAllQueries() {
        List<Scheduling> allQueries = queryService.getAllQueries();
        return allQueries.stream()
                .map(this::convertToQueryMinimalGetDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retorna todas as consultas agendadas.
     *
     * @return Lista de objetos de transferência de dados das consultas.
     */
    @GetMapping("/{queryID}")
    public List<QueryMaximalGetDTO> getQueryByID(Long queryID) {
        // Fazer proxima aula
    }

    /**
     * Converte um objeto de agendamento em um objeto de transferência de dados de consulta.
     *
     * @param scheduling Objeto de agendamento.
     * @return Objeto de transferência de dados da consulta.
     */
    private QueryMinimalGetDTO convertToQueryMinimalGetDTO(Scheduling scheduling) {
        return new QueryMinimalGetDTO(
                scheduling.getClient().getName(),
                scheduling.getSchedulingNumber(),
                scheduling.getPets(),
                scheduling.getScheduledDate(),
                scheduling.getServiceArea()

        );
    }

    /**
     * Converte um objeto de agendamento em um objeto de transferência de dados de consulta.
     *
     * @param scheduling Objeto de agendamento.
     * @return Objeto de transferência de dados da consulta.
     */
    private QueryMaximalGetDTO convertToQueryMaximalGetDTO(Scheduling scheduling) {
        return new QueryMaximalGetDTO(
                scheduling.getClient().getName(),
                scheduling.getSchedulingNumber(),
                scheduling.getServiceArea(),
                scheduling.getSubsidiary(),
                scheduling.getScheduledDate(),
                scheduling.getPets()
        );
    }
}
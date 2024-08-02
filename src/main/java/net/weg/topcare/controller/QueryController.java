package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.query.QueryMaximalGetDTO;
import net.weg.topcare.controller.dto.query.QueryMinimalGetDTO;
import net.weg.topcare.controller.dto.query.QueryPostDTO;
import net.weg.topcare.entity.Scheduling;
import net.weg.topcare.service.implementation.QueryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
                .map(Scheduling::convertToQueryMinimalGetDTO)
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
                .map(Scheduling::convertToQueryMinimalGetDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retorna todas as consultas agendadas.
     *
     * @return Lista de objetos de transferência de dados das consultas.
     */
    @GetMapping("/{queryID}")
    public ResponseEntity<QueryMaximalGetDTO> getQueryByID(@PathVariable Long queryID) {
        Optional<Scheduling> query = queryService.getQueryByID(queryID);
        if (query.isPresent()) {
            return ResponseEntity.ok(query.get().convertToQueryMaximalGetDTO());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
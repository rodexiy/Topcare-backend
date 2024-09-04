package net.weg.topcare.controller.dto.service;

import net.weg.topcare.entity.Service;

public record ServiceGetRequestDTO(String nome, Double price) {
    public ServiceGetRequestDTO(Service service) {
        this(service.getNome(), service.getPrice());
    }
}

package net.weg.topcare.enums;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public enum ServiceArea {
    VETERINARIA("Veterinária"),
    SERVICO("Serviço");

    private final String nome;
}
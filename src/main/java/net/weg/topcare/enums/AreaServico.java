package net.weg.topcare.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public enum AreaServico {
    VETERINARIA("Veterinária"),
    SERVICO("Serviço");

    private final String nome;
}
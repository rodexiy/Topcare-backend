package net.weg.topcare.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PorteAnimal {
    PEQUENO("Pequeno"),
    MEDIO("Médio"),
    GRANDE("Grande");

    private final String nome;
}

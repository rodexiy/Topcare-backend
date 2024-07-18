package net.weg.topcare.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CargoFuncionario {
    // MEDICOVETERINARIO
    // ALMOXARIFE
    // ADESTRADOR
    // TOSADOR
    // 

    FUNCIONARIO("Funcionário"),
    GERENTE("Gerente");

    private final String nome;
}

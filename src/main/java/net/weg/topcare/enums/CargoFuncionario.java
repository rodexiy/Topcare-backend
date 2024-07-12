package net.weg.topcare.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CargoFuncionario {
    FUNCIONARIO("Funcionário"),
    GERENTE("Gerente");

    private final String nome;
}

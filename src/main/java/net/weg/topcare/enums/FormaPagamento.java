package net.weg.topcare.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormaPagamento {
    PIX("Pix"),
    BOLETO("Boleto"),
    CREDITO("Crédito"),
    DEBITO("Débito");

    private final String nome;
}

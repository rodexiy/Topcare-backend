package net.weg.topcare.enums;

import lombok.Getter;

@Getter
public enum FormaPagamento {
    PIX("Pix"),
    BOLETO("Boleto"),
    CREDITO("Crédito"),
    DEBITO("Débito");

    FormaPagamento(String nome) {
        this.nome = nome;
    }

    private final String nome;
}

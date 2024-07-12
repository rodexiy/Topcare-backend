package net.weg.topcare.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoAtendimento {
    ORCAMENTO("Orçamento"),
    COMPRA("Compra"),
    SERVICO("Serviço"),
    PERGUNTA("Pergunta"),
    SUGESTAO("Sugestão"),
    RECLAMACAO("Reclamação"),
    AGENDAMENTO("Agendamento");

    private final String nome;
}

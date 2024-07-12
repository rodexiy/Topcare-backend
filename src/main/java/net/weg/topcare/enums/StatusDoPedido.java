package net.weg.topcare.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusDoPedido {
    PEDIDO_RECEBIDO("Pedido recebido"),
    ENVIADO_PARA_A_TRANSPORTADORA("Enviado para a transportadora"),
    RECEBIDO_PELA_TRANSPORTADORA("Recebido pela transportadora"),
    EM_TRANSITO("Em trânsito"),
    SAIU_PARA_A_ENTREGA("Saiu para a entrega"),
    PEDIDO_ENTREGUE("Pedido entregue");

    private final String nome;
}

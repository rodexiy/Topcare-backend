package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.weg.topcare.enums.UnidadeFederativa;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false, length = 100)
    private String rua;

    @Column(nullable = false, length = 100)
    private String bairro;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(nullable = false, length = 2)
    @Enumerated(EnumType.ORDINAL)
    private UnidadeFederativa UF;

    @Column(length = 100)
    private String complemento;
}

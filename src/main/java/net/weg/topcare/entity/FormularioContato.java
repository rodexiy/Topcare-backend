package net.weg.topcare.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.weg.topcare.enums.TipoAtendimento;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormularioContato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.ORDINAL)
    private TipoAtendimento tipoAtendimento;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Filial filial;

}

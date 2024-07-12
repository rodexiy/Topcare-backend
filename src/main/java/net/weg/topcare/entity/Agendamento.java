package net.weg.topcare.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.weg.topcare.enums.AreaServico;

import java.time.LocalDateTime;
import java.util.List;

// Entidade para serviço que foi agendado
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String numeroAgendamento;

    @Enumerated(EnumType.ORDINAL)
    private AreaServico areaServico;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Filial filial;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private LocalDateTime dataAgendada;

    @OneToMany
    @JoinColumn(nullable = false)
    private List<PetAgendamento> pets;
}

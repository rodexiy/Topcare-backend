package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.weg.topcare.enums.AreaServico;

import java.time.LocalDateTime;
import java.util.List;

// Entidade para serviço que foi agendado
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String schedulingNumber;

    @Enumerated(EnumType.ORDINAL)
    private AreaServico areaServico;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Filial filial;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    @ToString.Exclude // fazer DTO
    private Cliente cliente;

    @Column(nullable = false)
    private LocalDateTime dataAgendada;

    @OneToMany
    @JoinColumn(nullable = false)
    private List<PetAgendamento> pets;
}

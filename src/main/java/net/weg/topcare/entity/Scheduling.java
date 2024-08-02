package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.weg.topcare.controller.dto.query.QueryMaximalGetDTO;
import net.weg.topcare.controller.dto.query.QueryMinimalGetDTO;
import net.weg.topcare.enums.ServiceArea;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
    private ServiceArea serviceArea;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Subsidiary subsidiary;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    @ToString.Exclude // fazer DTO
    private Client client;

    @Column(nullable = false)
    private LocalDateTime scheduledDate;

    @OneToMany
    @JoinColumn(nullable = false)
    private List<PetScheduling> pets = new ArrayList<>();

    public QueryMinimalGetDTO convertToQueryMinimalGetDTO() {

        return new QueryMinimalGetDTO(
                this.getSchedulingNumber(),
                this.getPets(),
                this.getScheduledDate(),
                this.getServiceArea()
        );
    }

    public QueryMaximalGetDTO convertToQueryMaximalGetDTO() {
        return new QueryMaximalGetDTO(
                this.getClient().getName(),
                this.getSchedulingNumber(),
                this.getServiceArea(),
                this.getSubsidiary(),
                this.getScheduledDate(),
                this.getPets()
        );
    }
}

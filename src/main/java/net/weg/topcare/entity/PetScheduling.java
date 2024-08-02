package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// É utilizada no Agedamento para especificar os servicos selecionado para o pet

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetScheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Pet pet;

    @ManyToMany
    private List<Service> servicesSelected = new ArrayList<>();


}

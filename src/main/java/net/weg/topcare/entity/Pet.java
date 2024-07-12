package net.weg.topcare.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.weg.topcare.enums.GeneroPet;
import net.weg.topcare.enums.PorteAnimal;

import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @OneToOne
    private Imagem foto;

    @Column(nullable = false, length = 75)
    private String nome;

    @Column(nullable = false, length = 30)
    private String raca;

    @Column(nullable = false, length = 75)
    private PorteAnimal porte;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private GeneroPet genero;

    @Column(nullable = false)
    private Double peso;
    private LocalDate dataNascimento;

}

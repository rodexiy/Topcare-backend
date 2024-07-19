package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.weg.topcare.enums.PetGender;
import net.weg.topcare.enums.AnimalSize;

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
    @JsonIgnore
    @ToString.Exclude
    private Client client;

    @OneToOne
    private Image picture;

    @Column(nullable = false, length = 75)
    private String name;

    @Column(nullable = false, length = 30)
    private String breed;

    @Column(nullable = false, length = 75)
    private AnimalSize size;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private PetGender gender;

    @Column(nullable = false)
    private Double peso;
    private LocalDate birthdate;

}

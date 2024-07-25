package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import net.weg.topcare.controller.dto.pet.PetGetRequestDTO;
import net.weg.topcare.controller.dto.pet.PetPostRequestDTO;
import net.weg.topcare.enums.PetGender;
import net.weg.topcare.enums.AnimalSize;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Entity
@Getter
@Setter
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
    private Double weight;
    private LocalDate birthdate;

    public Pet(PetPostRequestDTO dto){
        BeanUtils.copyProperties(dto, this);
    }
    public PetGetRequestDTO toDto(){
        return new PetGetRequestDTO(
                this.name,
                this.breed,
                this.size,
                this.gender,
                this.weight,
                this.birthdate
        );
    }

}

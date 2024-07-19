package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity

public abstract class People {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, length = 14, nullable = false)
    private String cpf;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 14, nullable = false)
    private String phone;

    @Column(nullable = false, length = 30)
    private String password;

    @Column(nullable = false)
    private LocalDate birthdate;

    private Boolean enabled = true;
}

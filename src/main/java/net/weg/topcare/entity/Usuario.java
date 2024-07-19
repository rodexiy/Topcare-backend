package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity

public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(unique = true, length = 14, nullable = false)
    private String cpf;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 14, nullable = false)
    private String telefone;

    @Column(nullable = false, length = 30)
    private String senha;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    private Boolean habilitado = true;
}

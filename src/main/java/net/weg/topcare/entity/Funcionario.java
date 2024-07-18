package net.weg.topcare.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.weg.topcare.enums.CargoFuncionario;
import org.hibernate.annotations.Cascade;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDate;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Funcionario extends Usuario {
    @Column(nullable = false, length = 5)
    private String cadastro;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private CargoFuncionario cargo;

    @OneToOne
    private Imagem fotoPerfil;
}

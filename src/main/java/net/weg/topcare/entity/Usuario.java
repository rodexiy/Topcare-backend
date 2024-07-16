package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class Usuario {
    @Id
    private Long id;

    @Column(length = 100)
    private String nome;

    @Column(unique = true, length = 14)
    private String cpf;

    @Column(unique = true)
    private String email;

    @Column(length = 14)
    private String telefone;

    @Column(nullable = false, length = 30)
    private String senha;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "foto_perfil_id")
    private Imagem fotoPerfil;

}

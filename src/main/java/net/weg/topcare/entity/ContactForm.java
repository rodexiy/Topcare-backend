package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.weg.topcare.enums.ContactType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.ORDINAL)
    private ContactType contactType;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Subsidiary subsidiary;

}

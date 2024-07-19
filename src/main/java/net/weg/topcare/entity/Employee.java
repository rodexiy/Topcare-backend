package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.weg.topcare.enums.EmployeeRole;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Employee extends People {
    @Column(nullable = false, length = 5)
    private String register;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private EmployeeRole role;

    @OneToOne
    private Image profilePicture;
}

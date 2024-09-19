package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.weg.topcare.controller.dto.employee.GetEmployeeDto;
import net.weg.topcare.controller.dto.employee.PostEmployeeDto;
import net.weg.topcare.enums.EmployeeRole;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee extends People {
    @Column(nullable = false, length = 5)
    private String register;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private EmployeeRole role;

    @OneToOne
    private Image profilePicture;

    public Employee(PostEmployeeDto dto){
        BeanUtils.copyProperties(dto, this);
    }

    public GetEmployeeDto toGetDTO(){
        return new GetEmployeeDto(
                this.getId(),
                this.register,
                this.getName(),
                this.getBirthdate(),
                this.getEmail(),
                this.role,
                this.profilePicture
        );
    }
}

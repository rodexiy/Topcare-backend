package net.weg.topcare.controller.dto.employee;

import net.weg.topcare.enums.EmployeeRole;

import java.time.LocalDate;

public record GetEmployeeDto (Long id, String number, String name, LocalDate birthdate, String email, String password, EmployeeRole role){
}

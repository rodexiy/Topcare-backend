package net.weg.topcare.controller.dto.employee;

import net.weg.topcare.enums.EmployeeRole;

import java.time.LocalDate;

public record PostEmployeeDto(String register, String cpf, String phone, String name, LocalDate birthdate,String email,String password, EmployeeRole role) {
}

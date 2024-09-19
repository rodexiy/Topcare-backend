package net.weg.topcare.controller.dto.employee;

import net.weg.topcare.entity.Image;
import net.weg.topcare.enums.EmployeeRole;

import java.time.LocalDate;

public record GetEmployeeDto (Long id, String number, String name, LocalDate birthdate, String email, EmployeeRole role, Image profilePicture){
}

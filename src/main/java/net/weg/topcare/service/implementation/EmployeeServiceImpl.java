package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.entity.Employee;
import net.weg.topcare.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl {
    private final EmployeeRepository repository;

    public EmployeeToGetDTO register(EmployeePostDTO employee) {
        Employee newEmployee = new Employee(employee);
        return new EmployeeToGetDTO(repository.save(newEmployee));
    }
}

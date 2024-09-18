package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.employee.GetEmployeeDto;
import net.weg.topcare.controller.dto.employee.PostEmployeeDto;
import net.weg.topcare.entity.Employee;
import net.weg.topcare.entity.Image;
import net.weg.topcare.repository.EmployeeRepository;
import net.weg.topcare.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl {
    private final EmployeeRepository repository;
    private final ImageRepository imageRepository;

    public GetEmployeeDto register(PostEmployeeDto employee, MultipartFile file) {
        Employee newEmployee = new Employee(employee);
        try {
            Image image = new Image(file);
            newEmployee.setProfilePicture(imageRepository.save(image));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return repository.save(newEmployee).toGetDTO();
    }
}

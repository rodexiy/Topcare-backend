package net.weg.topcare.controller;

import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.employee.GetEmployeeDto;
import net.weg.topcare.controller.dto.employee.PostEmployeeDto;
import net.weg.topcare.service.implementation.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeServiceImpl service;
    @PostMapping
    public ResponseEntity<GetEmployeeDto> postEmployee(@RequestPart PostEmployeeDto dto, @RequestPart MultipartFile file){
        return new ResponseEntity<>(service.register(dto, file), HttpStatus.OK);

    }

}

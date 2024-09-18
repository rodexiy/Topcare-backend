package net.weg.topcare.controller;

import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.employee.GetEmployeeDto;
import net.weg.topcare.controller.dto.employee.PostEmployeeDto;
import net.weg.topcare.service.implementation.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeServiceImpl service;
    @PostMapping
    public ResponseEntity<GetEmployeeDto> postEmployee(@RequestBody PostEmployeeDto dto){
        return new ResponseEntity<>(service.register(dto), HttpStatus.OK);

    }

}

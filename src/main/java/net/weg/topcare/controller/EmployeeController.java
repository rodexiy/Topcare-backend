package net.weg.topcare.controller;

import net.weg.topcare.controller.dto.employee.PostEmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionarios")
public class EmployeeController {
    private final EmployeeServiceImpl service;
    @PostMapping
    public ResponseEntity<PostEmployeeDto> postEmployee(@RequestBody PostEmployeeDto dto){
        return new ResponseEntity<>(service.register(dto), HttpStatus.OK);

    }

}

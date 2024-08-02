package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.cartorder.CartOrderMinimalGetDTO;
import net.weg.topcare.controller.dto.query.QueryMinimalGetDTO;
import net.weg.topcare.controller.dto.query.QueryPostDTO;
import net.weg.topcare.service.implementation.QueryServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/query")
@AllArgsConstructor
public class QueryController {

    private QueryServiceImpl queryService;

    @PostMapping
    public void addQuery(@RequestBody QueryPostDTO dto) {
        queryService.addQuery(dto);
    }


}

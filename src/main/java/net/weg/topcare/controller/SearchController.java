package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.product.ProductMinimalGetDTO;
import net.weg.topcare.service.implementation.SearchServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/search")
@CrossOrigin("*")
@AllArgsConstructor
public class SearchController {
    private final SearchServiceImpl service;
    @GetMapping("/product")
    public ResponseEntity<Page<ProductMinimalGetDTO>> search(@RequestParam String q, @RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(service.searchProduct(q, page, size), HttpStatus.OK);
    }
}

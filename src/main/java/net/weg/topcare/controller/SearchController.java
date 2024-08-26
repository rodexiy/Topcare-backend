package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.product.ProductMinimalGetDTO;
import net.weg.topcare.entity.ProductFilter;
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
    @PostMapping("/product")
    public ResponseEntity<Page<ProductMinimalGetDTO>> search(@RequestParam String q, @RequestParam int page, @RequestParam int size, @RequestParam String sort, @RequestBody ProductFilter productFilter) {
        return new ResponseEntity<>(service.searchProduct(q, page, size, sort, productFilter), HttpStatus.OK);
    }
}

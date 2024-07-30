package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.category.CategoryGetDTO;
import net.weg.topcare.controller.dto.category.CategoryPatchDTO;
import net.weg.topcare.controller.dto.category.CategoryPostDTO;
import net.weg.topcare.controller.dto.category.CategoryPutDTO;
import net.weg.topcare.entity.Category;
import net.weg.topcare.service.implementation.CategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/category")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl service;

    @PostMapping
    private ResponseEntity<Category> postCategory(@RequestBody CategoryPostDTO dto){
        return new ResponseEntity<>(service.postCategory(dto), HttpStatus.OK);
    }

    @PutMapping
    private ResponseEntity<Category> updateCategory(@RequestBody CategoryPutDTO dto){
        return new ResponseEntity<>(service.putCategory(dto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<CategoryGetDTO> getCategory(@PathVariable Long id){
        return new ResponseEntity<>(service.getCategory(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteCategory(@PathVariable Long id){
        return new ResponseEntity<>(service.deleteCategory(id) ,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    private ResponseEntity<Category> patchCategory(@RequestBody CategoryPatchDTO dto, @PathVariable Long id){
        return new ResponseEntity<>(service.patchCategory(dto, id), HttpStatus.OK);
    }
}

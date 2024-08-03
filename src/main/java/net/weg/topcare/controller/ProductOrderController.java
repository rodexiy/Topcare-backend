package net.weg.topcare.controller;

import lombok.RequiredArgsConstructor;
import net.weg.topcare.service.implementation.ProductOrderServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/productorder")
@RequiredArgsConstructor
public class ProductOrderController {
    private final ProductOrderServiceImpl service;
}

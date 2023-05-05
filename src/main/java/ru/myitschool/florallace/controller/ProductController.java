package ru.myitschool.florallace.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.service.product.ProductServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl service;

    @PostMapping("/product")
    public Product add(@RequestBody Product product){
        return service.add(product);
    }

    @GetMapping("/product")
    public List<Product> getAll(){
        return service.getAll();
    }

    @GetMapping("/product/{id}")
    public Product getById(@PathVariable long id){
        return service.getById(id);
    }

    @PutMapping("/product")
    public Product update(@RequestBody Product product){
        return service.update(product);
    }

    @DeleteMapping("/product/{id}")
    public void deleteById(@PathVariable long id){
        service.deleteById(id);
    }
}

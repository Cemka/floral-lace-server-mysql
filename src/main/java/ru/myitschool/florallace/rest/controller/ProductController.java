package ru.myitschool.florallace.rest.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.rest.dto.ProductDto;
import ru.myitschool.florallace.service.product.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product")
    public List<ProductDto> getAllProducts() {

        return productService
                .getAll()
                .stream()
                .map(ProductDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/product/color/{color}")
    public List<ProductDto> getAllProductsByColor(@PathVariable String color) {

        return productService
                .findAllByColor(color)
                .stream()
                .map(ProductDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/product/name/{name}")
    public ProductDto getProductByName(@PathVariable String name){

        Product product = productService.getByName(name);
        return ProductDto.toDto(product);
    }



    @PostMapping("/product")
    public ProductDto insertProduct(@RequestBody ProductDto productDto) {

        Product product = productService.insert(
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getCountLast(),
                productDto.getCountStart(),
                productDto.getColor(),
                productDto.getPhotoUrl()
        );

        return ProductDto.toDto(product);
    }

    @PutMapping("/product/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {

        Product product = productService.update(
                id,
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getCountLast(),
                productDto.getCountStart(),
                productDto.getColor(),
                productDto.getPhotoUrl());

        return ProductDto.toDto(product);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteById(id);
    }

    @GetMapping("/product/{id}")
    public ProductDto getProductById(@PathVariable Long id){

        Product product = productService.getById(id);
        return ProductDto.toDto(product);
    }
}

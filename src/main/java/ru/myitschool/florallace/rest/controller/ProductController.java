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
    public List<ProductDto> getAllProducts(){

        return productService
                .getAll()
                .stream()
                .map(ProductDto::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/product")
    public ProductDto insertProduct(@RequestBody ProductDto productDto){

        Product product = productService.insert(
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getCount_last(),
                productDto.getCount_start(),
                productDto.getColor(),
                productDto.getPhotoUrl()
        );

        return ProductDto.toDto(product);
    }

    @PutMapping("/product/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductRequest request){
        System.out.println(id + " " + request.getName());

        return null;
    }

    @Data
    public static class ProductRequest {

        private String name;
        private String description;
        private Integer price;
        private Integer countLast;
        private Integer countStart;
        private String color;
        private String photoUrl;
    }

    //todo переделать эту вещь
}

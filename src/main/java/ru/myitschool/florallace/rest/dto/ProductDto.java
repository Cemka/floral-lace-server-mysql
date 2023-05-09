package ru.myitschool.florallace.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.myitschool.florallace.domain.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private String photoUrl;

    private Integer price;

    private Integer count_last;

    private Integer count_start;

    private String color;

    public static ProductDto toDto(Product product) {

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPhotoUrl(),
                product.getPrice(),
                product.getCount_last(),
                product.getCount_start(),
                product.getColor()
        );

    }

    public static Product toDomainObject(ProductDto productDto) {

        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPhotoUrl(),
                productDto.getPrice(),
                productDto.getCount_last(),
                productDto.getCount_last(),
                productDto.getColor()
        );

    }
}

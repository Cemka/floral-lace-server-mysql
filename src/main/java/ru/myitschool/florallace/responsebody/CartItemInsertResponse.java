package ru.myitschool.florallace.responsebody;

import lombok.Data;

@Data
public class CartItemInsertResponse {
    private Long productId;
    private Integer quantity;
}

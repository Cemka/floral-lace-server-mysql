package ru.myitschool.florallace.rest.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.myitschool.florallace.rest.dto.CartItemDto;
import ru.myitschool.florallace.service.cartitem.CartItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @GetMapping("/cart_item")
    public List<CartItemDto> getAll(){
        return cartItemService
                .getAll()
                .stream()
                .map(CartItemDto::toDto)
                .toList();
    }

    @GetMapping("/cart_item/{id}")
    public CartItemDto getById(@PathVariable Long id){
        return CartItemDto.toDto(cartItemService.getById(id));
    }

    /*@PostMapping("/cart_item/{user_id}/{product_id}/{quantity}")
    public CartItemDto insert(@PathVariable("user_id") Long userId,
                              @PathVariable("product_id") Long productId,
                              @PathVariable("quantity") Integer quantity){

        return CartItemDto.toDto(cartItemService.insert(userId,
                productId,
                quantity
                ));

    }*/

    @PostMapping("/cart_item/{user_id}")
    public CartItemDto insert(@PathVariable("user_id") Long userId,
                              @RequestParam("product_id") Long productId,
                              @RequestParam("quantity") Integer quantity){

        return CartItemDto.toDto(cartItemService.insert(userId,
                productId,
                quantity
        ));

    }

    @GetMapping("/cart_item/user/{user_id}/{product_id}")
    public CartItemDto getByUserAndProduct(@PathVariable("user_id") Long userId,
                                           @PathVariable("product_id") Long productId){
        return CartItemDto.toDto(cartItemService.getByUserAndProduct(userId, productId));
    }

    @DeleteMapping("/cart_item/{id}")
    public void deleteById(@PathVariable Long id){
        cartItemService.deleteById(id);
    }

    @DeleteMapping("/cart_item")
    public void deleteAllById(@RequestBody DelReqBody body){
        cartItemService.deleteAllByIds(body.getIds());
    }

    @Data
    private static class DelReqBody{
        List<Long> ids;
    }
}

package ru.myitschool.florallace.rest.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.myitschool.florallace.domain.OrderItem;
import ru.myitschool.florallace.rest.dto.OrderItemDto;
import ru.myitschool.florallace.service.ordetitem.OrderItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping("/order_item")
    public List<OrderItemDto> getAll(){
        return orderItemService
                .getAll()
                .stream()
                .map(OrderItemDto::toDto)
                .toList();
    }

    @GetMapping("/order_item/{id}")
    public OrderItemDto getById(@PathVariable Long id){
        return OrderItemDto.toDto(orderItemService.getById(id));
    }


    @PostMapping("/order_item/{order_id}")
    public OrderItemDto insert(@PathVariable("order_id") Long userId,
                              @RequestParam("product_id") Long productId,
                              @RequestParam("quantity") Integer quantity){

        return OrderItemDto.toDto(orderItemService.insert(userId,
                productId,
                quantity
        ));
    }

    @PostMapping("/order_item/product/{order_id}")
    public List<OrderItemDto> insertByListCartItems(@PathVariable("order_id") Long orderId,
                              @RequestBody InsertReqBody insertReqBody){

        return orderItemService.insertByListCartItems(
                orderId,
                insertReqBody.getCartItemsId())
                .stream()
                .map(OrderItemDto::toDto)
                .toList();

    }

    @GetMapping("/order_item/user/{user_id}")
    public List<OrderItemDto> getByUserId(@PathVariable("user_id") Long userId){
        return orderItemService.getByUserId(userId).stream().map(OrderItemDto::toDto).toList();
    }

    @GetMapping("/order_item/order/{order_id}/{product_id}")
    public OrderItemDto getByUserAndProduct(@PathVariable("order_id") Long orderId,
                                           @PathVariable("product_id") Long productId){
        return OrderItemDto.toDto(orderItemService.getByOrderAndProduct(orderId, productId));
    }

    @DeleteMapping("/order_item/{id}")
    public void deleteById(@PathVariable Long id){
        orderItemService.deleteById(id);
    }

    @Data
    private static class InsertReqBody{
        List<Long> cartItemsId;
    }
}

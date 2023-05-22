package ru.myitschool.florallace.rest.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.myitschool.florallace.rest.dto.FavItemDto;
import ru.myitschool.florallace.service.favitem.FavItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FavItemController {
    private final FavItemService favItemService;

    @GetMapping("/fav_item")
    public List<FavItemDto> getAll() {
        return favItemService
                .getAll()
                .stream()
                .map(FavItemDto::toDto)
                .toList();
    }

    @GetMapping("/fav_item/{id}")
    public FavItemDto getById(@PathVariable Long id) {
        return FavItemDto.toDto(favItemService.getById(id));
    }


    @PostMapping("/fav_item/{user_id}")
    public FavItemDto insert(@PathVariable("user_id") Long userId,
                             @RequestParam("product_id") Long productId
    ) {

        return FavItemDto.toDto(favItemService.insert(userId,
                productId
        ));

    }

    @GetMapping("/fav_item/user/{user_id}/{product_id}")
    public FavItemDto getByUserAndProduct(@PathVariable("user_id") Long userId,
                                           @PathVariable("product_id") Long productId) {
        return FavItemDto.toDto(favItemService.getByUserAndProduct(userId, productId));
    }

    @DeleteMapping("/fav_item/{id}")
    public void deleteById(@PathVariable Long id) {
        favItemService.deleteById(id);
    }
}

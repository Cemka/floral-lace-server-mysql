package ru.myitschool.florallace.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.myitschool.florallace.domain.FavItem;
import ru.myitschool.florallace.service.user.UserService;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavItemDto {

    private Long id;

    private ProductDto product;

    private Long userId;

    public static FavItemDto toDto(FavItem favItem) {

        return new FavItemDto(
                favItem.getId(),
                ProductDto.toDto(favItem.getProduct()),
                favItem.getUser().getId()
        );
    }

    public static FavItem toDomainObject(FavItemDto favItemDto, UserService userService) {

        return new FavItem(
                favItemDto.getId(),
                userService.getById(favItemDto.getUserId()),
                ProductDto.toDomainObject(favItemDto.getProduct())
        );
    }

}

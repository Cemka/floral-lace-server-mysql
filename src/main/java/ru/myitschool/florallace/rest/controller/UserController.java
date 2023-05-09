package ru.myitschool.florallace.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.rest.dto.OrderDto;
import ru.myitschool.florallace.rest.dto.ProductDto;
import ru.myitschool.florallace.rest.dto.UserDto;
import ru.myitschool.florallace.service.user.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto> getAll() {
        return userService
                .getAll()
                .stream()
                .map(UserDto::toDto)
                .toList();
    }

    @GetMapping("/users/{id}")
    public UserDto getById(@PathVariable Long id) {
        return UserDto.toDto(userService.getById(id));
    }

    @GetMapping("/users/phoneNumb/{phoneNumb}")
    public UserDto getByPhoneNumb(@PathVariable String phoneNumb) {
        return UserDto.toDto(userService.getByPhone(phoneNumb));
    }

    @PostMapping("/users")
    public UserDto insertUser(@RequestBody UserDto userDto) {

        User user = userService.insert(userDto.getPhoneNumb(),
                userDto.getFirstName(),
                userDto.getSecondName(),
                userDto.getCountOfBonus(),
                userDto.getFavouriteProductsId().stream().map(ProductDto::toDomainObject).toList(),
                userDto.getProductsInCartId().stream().map(ProductDto::toDomainObject).toList());

        return UserDto.toDto(user);
    }

    @PutMapping("/users/{id}")
    public UserDto updateUserById(@PathVariable Long id, @RequestBody UserDto userDto) {

        User user = userService.update(id,
                userDto.getPhoneNumb(),
                userDto.getFirstName(),
                userDto.getSecondName(),
                userDto.getCountOfBonus(),
                userDto.getFavouriteProductsId().stream().map(ProductDto::toDomainObject).toList(),
                userDto.getProductsInCartId().stream().map(ProductDto::toDomainObject).toList());

        return UserDto.toDto(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
    }

}

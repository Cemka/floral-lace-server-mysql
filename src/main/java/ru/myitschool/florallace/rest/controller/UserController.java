package ru.myitschool.florallace.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.repository.ProductRepository;
import ru.myitschool.florallace.rest.dto.ProductDto;
import ru.myitschool.florallace.rest.dto.UserDto;
import ru.myitschool.florallace.service.user.UserService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProductRepository productRepository;

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

//        User user = UserDto.toDomainObject(userDto, userService, productRepository);

        User user = userService.insert(userDto.getPhoneNumb(),
                userDto.getFirstName(),
                userDto.getSecondName(),
                userDto.getCountOfBonus(),
                userDto.getFavouriteProducts().stream().map(ProductDto::toDomainObject).toList(),
                userDto.getProductsInCart()
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(entry -> ProductDto.toDomainObject(entry.getKey()), Map.Entry::getValue)));

        return UserDto.toDto(user);
    }

    @PutMapping("/users/{id}")
    public UserDto updateUserById(@PathVariable Long id, @RequestBody UserDto userDto) {

//        User user = UserDto.toDomainObject(userDto, userService, productRepository);

        User user = userService.update(id,
                userDto.getPhoneNumb(),
                userDto.getFirstName(),
                userDto.getSecondName(),
                userDto.getCountOfBonus(),
                userDto.getFavouriteProducts().stream().map(ProductDto::toDomainObject).toList(),
                userDto.getProductsInCart()
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(entry -> ProductDto.toDomainObject(entry.getKey()), Map.Entry::getValue)));

        return UserDto.toDto(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
    }

}

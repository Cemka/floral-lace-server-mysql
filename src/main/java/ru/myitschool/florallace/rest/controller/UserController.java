package ru.myitschool.florallace.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.rest.dto.CartItemDto;
import ru.myitschool.florallace.rest.dto.FavItemDto;
import ru.myitschool.florallace.rest.dto.ProductDto;
import ru.myitschool.florallace.rest.dto.UserDto;
import ru.myitschool.florallace.service.user.UserService;

import java.util.ArrayList;
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

    /*@PostMapping("/users")
    public UserDto insertUser(@RequestBody UserDto userDto) {

        User user = userService.insert(userDto.getPhoneNumb(),
                userDto.getFirstName(),
                userDto.getSecondName(),
                userDto.getCountOfBonus(),
                userDto.getFavouriteProducts()
                        .stream()
                        .map(s -> FavItemDto.toDomainObject(s, userService))
                        .toList(),
                userDto.getCartItems()
                        .stream()
                        .map(s -> CartItemDto.toDomainObject(s, userService.getById(userDto.getId())))
                        .toList(),
                userDto.getPassword());

        return UserDto.toDto(user);
    }*/

    @PostMapping("/users")
    public User insertUser(@RequestParam("phone_numb") String phoneNumb,
                              @RequestParam("first_name") String firstName,
                              @RequestParam("second_name") String secondName,
                              @RequestParam("count_of_bonus") String countOfBonus,
                              @RequestParam("password") String password){
        return userService.insert(phoneNumb, firstName, secondName, Integer.parseInt(countOfBonus),
                new ArrayList<>(),
                new ArrayList<>(), password);
    }

    @GetMapping("/users/password")
    public UserDto getByPhoneNumbAndPassword(@RequestParam("password") String password,
                                             @RequestParam("phone_numb") String phoneNumb){
        return UserDto.toDto(userService.getByPhoneNumbAndPassword(phoneNumb, password));
    }

    @PutMapping("/users/{id}")
    public UserDto updateUserById(@PathVariable Long id, @RequestBody UserDto userDto) {

        User user = userService.update(id,
                userDto.getPhoneNumb(),
                userDto.getFirstName(),
                userDto.getSecondName(),
                userDto.getCountOfBonus(),
                userDto.getFavouriteProducts()
                        .stream()
                        .map(s -> FavItemDto.toDomainObject(s, userService))
                        .toList(),
                userDto.getCartItems()
                        .stream()
                        .map(s -> CartItemDto.toDomainObject(s, userService.getById(userDto.getId())))
                        .toList(),
                userDto.getPassword());

        return UserDto.toDto(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
    }

}

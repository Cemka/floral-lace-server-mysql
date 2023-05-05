package ru.myitschool.florallace.controller;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.service.user.UserService;
import ru.myitschool.florallace.service.user.UserServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl service;

    @PostMapping("/user")
    public User add(@RequestBody User user){
        return service.add(user);
    }

    @GetMapping("/user")
    public List<User> getAll(){
        return service.getAll();
    }

    @GetMapping("/user/{id}")
    public User getById(@PathVariable long id){
        return service.getById(id);
    }

    @PutMapping("/user")
    public User update(@RequestBody User user){
        return service.update(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteById(@PathVariable long id){
        service.deleteById(id);
    }
}

package ru.myitschool.florallace.service.user;

import ru.myitschool.florallace.domain.User;

import java.util.List;

public interface UserService {

    User add(User user);

    User getById(long id);

    User update(User user);

    List<User> getAll();

    void deleteById(long id);

}

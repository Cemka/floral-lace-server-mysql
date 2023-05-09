package ru.myitschool.florallace.service.user;

import ru.myitschool.florallace.domain.User;

import java.util.List;

public interface UserService {

    User insert(String phoneNumb,
                String firstName,
                String secondName,
                Integer countOfBonus);

    List<User> getAll();

    User getById(Long id);

    User update(Long id,
                String phoneNumb,
                String firstName,
                String secondName,
                Integer countOfBonus);
    void deleteById(Long id);

}

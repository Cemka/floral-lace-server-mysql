package ru.myitschool.florallace.service.user;

import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.domain.User;

import java.util.List;

public interface UserService {

    User insert(String phoneNumb,
                String firstName,
                String secondName,
                Integer countOfBonus,
                List<Long> favouriteProducts,
                List<Long> productsInCart);

    List<User> getAll();

    User getById(Long id);

    User getByPhone(String phoneNumb);

    User update(Long id,
                String phoneNumb,
                String firstName,
                String secondName,
                Integer countOfBonus,
                List<Long> favouriteProducts,
                List<Long> productsInCart);
    void deleteById(Long id);

}

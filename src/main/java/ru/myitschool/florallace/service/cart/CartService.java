package ru.myitschool.florallace.service.cart;

import ru.myitschool.florallace.domain.Cart;

import java.util.List;

public interface CartService {

    Cart add(Cart cart);

    Cart getById(long id);

    Cart update(Cart cart);

    List<Cart> getAll();

    void deleteById(long id);
}

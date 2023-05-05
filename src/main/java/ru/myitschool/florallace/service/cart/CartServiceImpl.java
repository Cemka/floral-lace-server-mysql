package ru.myitschool.florallace.service.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.myitschool.florallace.domain.Cart;
import ru.myitschool.florallace.repository.CartRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private CartRepository repository;

    @Override
    public Cart add(Cart cart) {
        return repository.save(cart);
    }

    @Override
    public Cart getById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public Cart update(Cart cart) {
        return repository.save(cart);
    }

    @Override
    public List<Cart> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}

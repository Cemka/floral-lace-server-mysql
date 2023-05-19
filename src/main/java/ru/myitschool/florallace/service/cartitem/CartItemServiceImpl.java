package ru.myitschool.florallace.service.cartitem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.myitschool.florallace.domain.CartItem;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.repository.CartItemRepository;
import ru.myitschool.florallace.service.product.ProductService;
import ru.myitschool.florallace.service.user.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final UserService userService;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem insert(Long userId,
                           Long productId,
                           Integer quantity) {
        User user = userService.getById(userId);
        Product product = productService.getById(productId);

        return cartItemRepository.save(CartItem.builder()
                .user(user)
                .product(product)
                .quantity(quantity)
                .build());
    }

    @Override
    public List<CartItem> getAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem getById(Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CartItem not found: " + id));
    }

    @Override
    public CartItem update(Long id, Long userId, Long productId, Integer quantity) {
        User user = userService.getById(userId);
        Product product = productService.getById(productId);

        return cartItemRepository.save(CartItem.builder()
                .id(id)
                .user(user)
                .product(product)
                .quantity(quantity)
                .build());
    }

    @Override
    public CartItem getByUserAndProduct(Long userId, Long productId) {
        User user = userService.getById(userId);
        Product product = productService.getById(productId);

        return cartItemRepository.getByUserAndProduct(user, product);
    }

    @Override
    public void deleteById(Long id) {

        Optional<CartItem> existingCartITem = cartItemRepository.findById(id);
        if (existingCartITem.isEmpty()) {
            throw new RuntimeException("Cart item not found by id: " + id);
        }
        cartItemRepository.deleteById(id);
    }
}

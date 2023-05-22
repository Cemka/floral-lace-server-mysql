package ru.myitschool.florallace.service.favitem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.myitschool.florallace.domain.FavItem;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.repository.FavItemRepository;
import ru.myitschool.florallace.service.product.ProductService;
import ru.myitschool.florallace.service.user.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavItemServiceImpl implements FavItemService {

    private final UserService userService;
    private final ProductService productService;
    private final FavItemRepository favItemRepository;

    @Override
    public FavItem insert(Long userId, Long productId) {
        User user = userService.getById(userId);
        Product product = productService.getById(productId);

        return favItemRepository.save(FavItem.builder()
                .product(product)
                .user(user)
                .build()

        );
    }

    @Override
    public List<FavItem> getAll() {
        return favItemRepository.findAll();
    }

    @Override
    public FavItem getById(Long id) {
        return favItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CartItem not found: " + id));
    }

    @Override
    public FavItem update(Long id, Long userId, Long productId) {
        User user = userService.getById(userId);
        Product product = productService.getById(productId);

        return favItemRepository.save(FavItem.builder()
                .product(product)
                .user(user)
                .build()

        );
    }

    @Override
    public FavItem getByUserAndProduct(Long userId, Long productId) {
        User user = userService.getById(userId);
        Product product = productService.getById(productId);

        return favItemRepository.getByUserAndProduct(user, product);
    }

    @Override
    public void deleteById(Long id) {

        Optional<FavItem> existingFavItem = favItemRepository.findById(id);
        if (existingFavItem.isEmpty()) {
            throw new RuntimeException("Favourite item not found by id: " + id);
        }

        favItemRepository.deleteById(id);
    }
}

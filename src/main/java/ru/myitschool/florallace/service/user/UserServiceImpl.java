package ru.myitschool.florallace.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.myitschool.florallace.domain.CartItem;
import ru.myitschool.florallace.domain.FavItem;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.repository.ProductRepository;
import ru.myitschool.florallace.repository.UserRepository;
import ru.myitschool.florallace.security.PasswordHasher;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    @Override
    public User insert(String phoneNumb,
                       String firstName,
                       String secondName,
                       Integer countOfBonus,
                       List<FavItem> favouriteProducts,
                       List<CartItem> cartItems,
                       String password) {
        // todo сделать проверку номера телефона

        String hashPas = PasswordHasher.hashPassword(password);

        return userRepository.save(User
                .builder()
                .phoneNumb(phoneNumb)
                .firstName(firstName)
                .secondName(secondName)
                .countOfBonus(countOfBonus)
                .favouriteProducts(favouriteProducts)
                .cartItems(cartItems)
                .password(hashPas)
                .build());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByPhoneNumbAndPassword(String phoneNumb, String password) {
        String hasPas = PasswordHasher.hashPassword(password);
        User user = userRepository.getByPhoneNumbAndPassword(phoneNumb, hasPas);
        if(user == null){
            throw new RuntimeException("User not found" + phoneNumb);
        }
        return user;
    }

    @Override
    public User getById(Long id) {
        // проверяем существует ли пользователь
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    @Override
    public User getByPhone(String phoneNumb) {
        User user = userRepository.findByPhoneNumb(phoneNumb);
        if (user == null) {
            throw new RuntimeException("User with this phone not found: " + phoneNumb);
        }
        return user;
    }

    @Override
    public User update(Long id,
                       String phoneNumb,
                       String firstName,
                       String secondName,
                       Integer countOfBonus,
                       List<FavItem> favouriteProducts,
                       List<CartItem> cartItems,
                       String password) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new RuntimeException("User not found: " + id);
        }

        String hashPas = PasswordHasher.hashPassword(password);

        User user = User
                .builder()
                .id(id)
                .phoneNumb(phoneNumb)
                .firstName(firstName)
                .secondName(secondName)
                .countOfBonus(countOfBonus)
                .favouriteProducts(favouriteProducts)
                .cartItems(cartItems)
                .password(hashPas)
                .build();

        return userRepository.save(user);

    }

    @Override
    public void deleteById(Long id) {
        // проверяем существует ли такой юзер
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new RuntimeException("User not found: " + id);
        }
        userRepository.deleteById(id);
    }
}

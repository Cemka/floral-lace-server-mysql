package ru.myitschool.florallace.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User insert(String phoneNumb,
                       String firstName,
                       String secondName,
                       Integer countOfBonus) {
        // todo сделать проверку номера телефона
        return userRepository.save(User
                .builder()
                .phoneNumb(phoneNumb)
                .firstName(firstName)
                .secondName(secondName)
                .countOfBonus(countOfBonus)
                .build());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        // проверяем существует ли пользователь
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    @Override
    public User update(Long id,
                       String phoneNumb,
                       String firstName,
                       String secondName,
                       Integer countOfBonus) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new RuntimeException("User not found: " + id);
        }

        User user = User
                .builder()
                .id(id)
                .phoneNumb(phoneNumb)
                .firstName(firstName)
                .secondName(secondName)
                .countOfBonus(countOfBonus)
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

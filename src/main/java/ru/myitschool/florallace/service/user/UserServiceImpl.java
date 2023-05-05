package ru.myitschool.florallace.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository repository;

    @Override
    public User add(User user) {
        return repository.save(user);
    }

    @Override
    public User getById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public User update(User user) {

        return repository.save(user);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}

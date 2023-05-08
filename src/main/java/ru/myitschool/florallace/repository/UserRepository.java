package ru.myitschool.florallace.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.myitschool.florallace.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


}

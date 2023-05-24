package ru.myitschool.florallace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myitschool.florallace.domain.Order;
import ru.myitschool.florallace.domain.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByLocation(String location);

    List<Order> findAllByTime(String time);

    Order getByUserId(User user);

}


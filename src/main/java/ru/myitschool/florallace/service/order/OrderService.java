package ru.myitschool.florallace.service.order;

import jakarta.annotation.Nullable;
import ru.myitschool.florallace.domain.OrderItem;
import ru.myitschool.florallace.domain.Order;

import java.util.List;

public interface OrderService {

    Order insert(Long userId,
                 List<OrderItem> ordersItemsList,
                 Integer price,
                 String location,
                 String time);

    List<Order> getAll();

    Order getById(Long id);

    Order getByUser(Long userId);

    List<Order> findAllByLocation(String location);

    List<Order> findAllByTime(String time);

    Order update(Long id,
                 Long userId,
                 List<OrderItem> favItems,
                 Integer price,
                 String location,
                 String time);

    void deleteById(Long id);
}

package ru.myitschool.florallace.service.order;

import ru.myitschool.florallace.domain.Order;
import ru.myitschool.florallace.domain.Product;

import java.util.List;

public interface OrderService {

    Order insert(Long userId,
                 List<Long> productListId,
                 Integer price,
                 String location,
                 String time);

    List<Order> getAll();

    Order getById(Long id);

    List<Order> findAllByLocation(String location);

    List<Order> findAllByTime(String time);

    Order update(Long id,
                 Long userId,
                 List<Long> productListId,
                 Integer price,
                 String location,
                 String time);

    void deleteById(Long id);
}

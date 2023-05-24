package ru.myitschool.florallace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myitschool.florallace.domain.Order;
import ru.myitschool.florallace.domain.OrderItem;
import ru.myitschool.florallace.domain.Product;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    OrderItem getByOrderAndProduct(Order order,
                                   Product product);
    List<OrderItem> getByOrder(Order order);
}

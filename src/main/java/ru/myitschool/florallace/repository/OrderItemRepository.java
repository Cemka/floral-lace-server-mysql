package ru.myitschool.florallace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myitschool.florallace.domain.Order;
import ru.myitschool.florallace.domain.OrderItem;
import ru.myitschool.florallace.domain.Product;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    OrderItem getByOrderAndProduct(Order order,
                                   Product product);

}

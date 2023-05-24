package ru.myitschool.florallace.service.ordetitem;

import ru.myitschool.florallace.domain.CartItem;
import ru.myitschool.florallace.domain.Order;
import ru.myitschool.florallace.domain.OrderItem;

import java.util.List;

public interface OrderItemService {

    OrderItem insert(Long orderId,
                     Long productId,
                     Integer quantity
    );

    List<OrderItem> insertByListCartItems(Long orderId,
                                          List<Long> cartItemsId);

    List<OrderItem> getAll();

    List<OrderItem> getByUserId(Long userId);

    OrderItem getById(Long id);

    OrderItem update(Long id,
                     Long userId,
                     Long productId,
                     Integer quantity
    );

    OrderItem getByOrderAndProduct(Long orderId,
                                   Long productId);

    void deleteById(Long id);
}

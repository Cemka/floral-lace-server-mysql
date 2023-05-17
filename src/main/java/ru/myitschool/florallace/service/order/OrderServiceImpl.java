package ru.myitschool.florallace.service.order;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.myitschool.florallace.domain.OrderItem;
import ru.myitschool.florallace.domain.Order;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.repository.OrderRepository;
import ru.myitschool.florallace.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public Order insert(Long userId,
                        @Nullable List<OrderItem> favItems,
                        Integer price,
                        String location,
                        String time) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        return orderRepository.save(Order
                .builder()
                .userId(user)
                .favItems(favItems)
                .price(price)
                .location(location)
                .time(time)
                .build());
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Long id) {
        // проверяем существует ли заказ
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }

    @Override
    public List<Order> findAllByLocation(String location) {
        // проверяем существует ли локация
        List<Order> orderList = orderRepository.findAllByLocation(location);
        if (orderList == null) {
            throw new RuntimeException("This location isn't present: " + location);
        }
        return orderList;
    }

    @Override
    public List<Order> findAllByTime(String time) {
        // проверяем существует ли такое время
        List<Order> orderList = orderRepository.findAllByTime(time);
        if (orderList == null) {
            throw new RuntimeException("This location isn't present: " + time);
        }
        return orderList;
    }

    @Override
    public Order update(Long id,
                        Long userId,
                        List<OrderItem> favItems,
                        Integer price,
                        String location,
                        String time) {

        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isEmpty()) {
            throw new RuntimeException("Order not found: " + id);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + userId));

        return orderRepository.save(Order
                .builder()
                .id(id)
                .userId(user)
                .favItems(favItems)
                .price(price)
                .location(location)
                .time(time)
                .build());
    }

    @Override
    public void deleteById(Long id) {
        // проверяем существует ли заказ
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isEmpty()) {
            throw new RuntimeException("Order not found by id: " + id);
        }
        orderRepository.deleteById(id);
    }
}

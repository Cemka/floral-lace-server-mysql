package ru.myitschool.florallace.service.ordetitem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.myitschool.florallace.domain.*;
import ru.myitschool.florallace.repository.OrderItemRepository;
import ru.myitschool.florallace.service.cartitem.CartItemService;
import ru.myitschool.florallace.service.order.OrderService;
import ru.myitschool.florallace.service.product.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderService orderService;
    private final ProductService productService;
    private final CartItemService cartItemService;

    @Override
    public OrderItem insert(Long orderId,
                            Long productId,
                            Integer quantity) {
        Order order = orderService.getById(orderId);
        Product product = productService.getById(productId);


        return orderItemRepository.save(OrderItem.builder()
                .product(product)
                .order(order)
                .quantity(quantity)
                .build()
        );
    }

    @Override
    public List<OrderItem> insertByListCartItems(Long orderId,
                                                 List<Long> cartItemsId) {
        List<OrderItem> orderItems = new ArrayList<>();
        Order order = orderService.getById(orderId);
        for (Long id : cartItemsId) {
            CartItem cartItem = cartItemService.getById(id);
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(cartItem.getProduct())
                    .quantity(cartItem.getQuantity())
                    .build();

            orderItems.add(orderItem);
            orderItemRepository.save(orderItem);
        }

        return orderItems;
    }

    @Override
    public List<OrderItem> getAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public List<OrderItem> getByUserId(Long userId) {
        Order order = orderService.getByUser(userId);
        if(order == null){
            throw new RuntimeException("User not found" + userId);
        }
        return orderItemRepository.getByOrder(order);
    }

    @Override
    public OrderItem getById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderITem not found: " + id));
    }

    @Override
    public OrderItem update(Long id,
                            Long orderId,
                            Long productId,
                            Integer quantity) {

        Order order = orderService.getById(orderId);
        Product product = productService.getById(productId);


        return orderItemRepository.save(OrderItem.builder()
                .id(id)
                .product(product)
                .order(order)
                .quantity(quantity)
                .build()
        );
    }

    @Override
    public OrderItem getByOrderAndProduct(Long orderId,
                                          Long productId) {
        Order order = orderService.getById(orderId);
        Product product = productService.getById(productId);

        return orderItemRepository.getByOrderAndProduct(order,
                product);
    }

    @Override
    public void deleteById(Long id) {

        Optional<OrderItem> existingOrderItem = orderItemRepository.findById(id);
        if (existingOrderItem.isEmpty()) {
            throw new RuntimeException("Favourite item not found by id: " + id);
        }

        orderItemRepository.deleteById(id);
    }
}

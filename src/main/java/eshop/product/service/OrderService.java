package eshop.product.service;

import eshop.product.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public interface OrderService {

    List<Order> getAllOrders();

    String createOrder(Order order);

    Order getOrderById(long id);

    String deleteOrder(long id);

    String updateOrder(Order order, long id);
}
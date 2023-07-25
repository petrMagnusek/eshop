package eshop.product.service.impl;

import eshop.product.entity.Order;
import eshop.product.exception.NoSuchProductExistsException;
import eshop.product.repository.OrderJpaRepository;
import eshop.product.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderJpaRepository orderJpaRepository;

    @Autowired
    public OrderServiceImpl(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    public List<Order> getAllOrders() {
        return orderJpaRepository.findAll();
    }


    public String createOrder(Order order) {
        if (order.getFirstName() == null || order.getFirstName().isEmpty() ||
                order.getLastName().isEmpty() || order.getLastName() == null || order.getProducts().isEmpty()) {
            return("BAD");
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Body has to contain name attribute!");
        }
        try {
            orderJpaRepository.save(order);
            //products.add(new Product(getHighestID() +1, product.getName(), product.getPrice(), product.getDescription()));
            //return ResponseEntity.status(HttpStatus.CREATED).body("Product Created Successfully!");
            return("OK");
        } catch (Exception e) {
            return(e.getMessage());
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public String updateOrder(Order order, long id) {
        if (order.getFirstName() == null || order.getFirstName().isEmpty() ||
                order.getLastName().isEmpty() || order.getLastName() == null || order.getProducts().isEmpty()) {
            return "BAD";
        }

        Optional<Order> ord = orderJpaRepository.findById(id);
        if (!ord.isPresent()) {
            throw(new NoSuchProductExistsException("NO PRODUCT PRESENT WITH ID = " + id));
        } else {
            try {
                ord.get().setLastName(order.getLastName());
                ord.get().setFirstName(order.getFirstName());
                ord.get().setProducts(ord.get().getProducts());
                orderJpaRepository.save(ord.get());
                return ("OK");
            } catch (Exception e) {
                return e.getMessage();
            }
        }

    }

    @Override
    public Order getOrderById(long id) {
        Optional<Order> order = orderJpaRepository.findById(id);
        if (!order.isPresent()) {
            throw(new NoSuchProductExistsException("NO PRODUCT PRESENT WITH ID = " + id));
        } else {
            return order.get();
        }
    }

    @Override
    public String deleteOrder(long id) {
        Optional<Order> order = orderJpaRepository.findById(id);
        //Optional<Product> product =  products.stream().filter(prod -> prod.getId() == id).findFirst();
        if (!order.isPresent()) {
            throw(new NoSuchProductExistsException("NO PRODUCT PRESENT WITH ID = " + id));
        } else {
            try {
                orderJpaRepository.delete(order.get());
                //products.remove(product.get());
                return("OK");
            } catch (Exception e) {
                return e.getMessage();
            }
        }
    }
}

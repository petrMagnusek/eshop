package eshop.product.controller;

import eshop.product.dtos.Order;
import eshop.product.exception.NoSuchOrderExistsException;
import eshop.product.exception.NoSuchProductExistsException;
import eshop.product.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }



    @GetMapping("/orders")
    public List<Order> getOrders() {
        List<Order> orders = orderService.getAllOrders();
        return orders;
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/orders")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        String response = orderService.createOrder(order);
        return createResponse(response);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable long id) {
        String response = orderService.deleteOrder(id);
        return createResponse(response);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<String> updateOrder(@RequestBody Order order, @PathVariable long id) {
        String response = orderService.updateOrder(order, id);
        return createResponse(response);
    }

    @ExceptionHandler(NoSuchOrderExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoSuchElementFoundException(
            NoSuchProductExistsException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    private ResponseEntity<String> createResponse(String response) {
        if (response.equals("OK")) {
            return ResponseEntity.status(HttpStatus.OK).body("Order Updated Successfully!");
        } else if (response.equals("BAD")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Body has to contain lastName, firstName and products attributes!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}
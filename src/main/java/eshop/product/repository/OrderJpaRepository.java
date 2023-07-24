package eshop.product.repository;

import eshop.product.dtos.Order;
import eshop.product.dtos.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}

package eshop.product.repository;

import eshop.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductJpaRepository extends JpaRepository<Product, Long> {
}

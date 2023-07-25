package eshop.product.repository;

import eshop.product.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductRepository {

    List<Product> getAllProducts();

    String createProduct(Product product);

    Product getProductById(long id);

    String deleteProduct(long id);

    String updateProduct(Product product, long id);
}

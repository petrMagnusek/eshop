package eshop.product.service;

import eshop.product.dtos.Product;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public interface ProductService {

    List<Product> getAllProducts();

    String createProduct(Product product);

    Product getProductById(long id);

    String deleteProduct(long id);

    String updateProduct(Product product, long id);
}
package eshop.product.controller;

import eshop.product.entity.Product;
import eshop.product.exception.NoSuchProductExistsException;
import eshop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        List<Product> products = productService.getAllProducts();
        return products;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        String response = productService.createProduct(product);
        return createResponse(response);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        String response = productService.deleteProduct(id);
        return createResponse(response);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable long id) {
        String response = productService.updateProduct(product, id);
        return createResponse(response);
    }

    @ExceptionHandler(NoSuchProductExistsException.class)
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
            return ResponseEntity.status(HttpStatus.OK).body("Product Updated Successfully!");
        } else if (response.equals("BAD")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Body has to contain name attribute!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}
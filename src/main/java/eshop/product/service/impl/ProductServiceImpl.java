package eshop.product.service.impl;

import eshop.product.entity.Product;
import eshop.product.exception.NoSuchProductExistsException;
import eshop.product.repository.ProductJpaRepository;
import eshop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductJpaRepository productJpaRepository;

    @Autowired
    public ProductServiceImpl(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    public List<Product> getAllProducts() {
        return productJpaRepository.findAll();
    }


    public String createProduct(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            return("BAD");
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Body has to contain name attribute!");
        }
        try {
            productJpaRepository.save(product);
            //products.add(new Product(getHighestID() +1, product.getName(), product.getPrice(), product.getDescription()));
            //return ResponseEntity.status(HttpStatus.CREATED).body("Product Created Successfully!");
            return("OK");
        } catch (Exception e) {
            return(e.getMessage());
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public String updateProduct(Product product, long id) {
        if (product.getName() == null || product.getName().isEmpty()) {
            return "BAD";
        }

        Optional<Product> prod = productJpaRepository.findById(id);
        //Optional<Product> prod =  products.stream().filter(prd -> prd.getId() == id).findFirst();
        if (!prod.isPresent()) {
            throw(new NoSuchProductExistsException("NO PRODUCT PRESENT WITH ID = " + id));
        } else {
            try {
                prod.get().setPrice(product.getPrice());
                prod.get().setName(product.getName());
                prod.get().setDescription(product.getDescription());
                productJpaRepository.save(prod.get());
                return ("OK");
            } catch (Exception e) {
                return e.getMessage();
            }
        }

    }

    @Override
    public Product getProductById(long id) {
        Optional<Product> product = productJpaRepository.findById(id);
        if (!product.isPresent()) {
            throw(new NoSuchProductExistsException("NO PRODUCT PRESENT WITH ID = " + id));
        } else {
            return product.get();
        }
    }

    @Override
    public String deleteProduct(long id) {
        Optional<Product> product = productJpaRepository.findById(id);
        //Optional<Product> product =  products.stream().filter(prod -> prod.getId() == id).findFirst();
        if (!product.isPresent()) {
            throw(new NoSuchProductExistsException("NO PRODUCT PRESENT WITH ID = " + id));
        } else {
            try {
                productJpaRepository.delete(product.get());
                //products.remove(product.get());
                return("OK");
            } catch (Exception e) {
                return e.getMessage();
            }
        }
    }
}

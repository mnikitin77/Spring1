package com.mvnikitin.boot.rest;

import com.mvnikitin.boot.entities.Product;
import com.mvnikitin.boot.rest.exceptions.ProductConflictException;
import com.mvnikitin.boot.rest.exceptions.ProductNotFoundException;
import com.mvnikitin.boot.rest.exceptions.ProductRESTException;
import com.mvnikitin.boot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/products")
@RestController
public class ProductRESTController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProucts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProuctById(@PathVariable Long id) {
        Product result = productService.findById(id).orElseThrow(() ->
                new ProductNotFoundException("A product with ID=" +
                    id + " does not exist"));
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        if(productService.findById(product.getId()).isPresent()) {
            throw new ProductConflictException("The product with ID=" +
                    product.getId() + " already exists.");
        }
        product.setId(0L);
        Product newProduct = productService.save(product);
        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .build(newProduct.getId()))
                .build();
    }

    @PutMapping
    public ResponseEntity<Void> updateProduct(@RequestBody Product product) {
        productService.findById(product.getId()).orElseThrow(() ->
                new ProductNotFoundException("A product with ID=" +
                        product.getId() + " does not exist"));
        productService.save(product);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Product deletedProduct = productService.findById(id).orElseThrow(() ->
                new ProductNotFoundException("A product with ID=" +
                        id + " does not exist"));
        productService.remove(id);
        return ResponseEntity.ok(deletedProduct);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(Exception e) {
        ProductErrorResponse response = new ProductErrorResponse();
        HttpStatus httpStatus = (e instanceof ProductRESTException) ?
                ((ProductRESTException)e).getHTTPStatus() :
                HttpStatus.INTERNAL_SERVER_ERROR;
        response.setMessage(e.getMessage());
        response.setStatus(httpStatus.value());
        response.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<ProductErrorResponse>(response, httpStatus);
    }
}

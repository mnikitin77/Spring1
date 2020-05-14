package com.mvnikitin.boot.rest;

import com.mvnikitin.boot.entity.Product;
import com.mvnikitin.boot.rest.exception.ResourceConflictException;
import com.mvnikitin.boot.rest.exception.ResourceNotFoundException;
import com.mvnikitin.boot.rest.exception.ResourceRESTException;
import com.mvnikitin.boot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
                new ResourceNotFoundException("A product with ID=" +
                    id + " does not exist"));
        return ResponseEntity.ok(result);
    }

    @Secured("ROLE_API_PRODUCT_ALL")
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        if(productService.findById(product.getId()).isPresent()) {
            throw new ResourceConflictException("The product with ID=" +
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

    @Secured("ROLE_API_PRODUCT_ALL")
    @PutMapping
    public ResponseEntity<Void> updateProduct(@RequestBody Product product) {
        productService.findById(product.getId()).orElseThrow(() ->
                new ResourceNotFoundException("A product with ID=" +
                        product.getId() + " does not exist"));
        productService.save(product);
        return ResponseEntity.noContent().build();
    }

    @Secured("ROLE_API_PRODUCT_ALL")
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Product deletedProduct = productService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("A product with ID=" +
                        id + " does not exist"));
        productService.remove(id);
        return ResponseEntity.ok(deletedProduct);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(Exception e) {
        ProductErrorResponse response = new ProductErrorResponse();
        HttpStatus httpStatus;
        if (e instanceof AccessDeniedException) {
            httpStatus = HttpStatus.UNAUTHORIZED;
        } else if (e instanceof ResourceRESTException) {
            httpStatus = ((ResourceRESTException)e).getHTTPStatus();
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        response.setMessage(e.getMessage());
        response.setStatus(httpStatus.value());
        response.setTimestamp(LocalDateTime.now());
        response.setPath(ServletUriComponentsBuilder.fromCurrentRequest().build().getPath());

        return new ResponseEntity<ProductErrorResponse>(response, httpStatus);
    }
}

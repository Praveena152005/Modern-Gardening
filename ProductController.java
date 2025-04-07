package com.examly.springapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Product;
import com.examly.springapp.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("post")
    public ResponseEntity<List<Product>> addProducts(@RequestBody List<Product> product) {
        List<Product> createdProduct = productService.addProducts(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("put/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.editProduct(id, product.getProductname(), product.getPrice());
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Product> post(@RequestBody Product product) {
        return ResponseEntity.status(201).body(productService.post(product));
    }

    @GetMapping("product/{field}")
    public ResponseEntity<List<Product>> sort(@PathVariable String field) {
        List<Product> sortedProducts = productService.sort(field);
        return new ResponseEntity<>(sortedProducts, HttpStatus.OK);
    }

    @GetMapping("product/{offset}/{pagesize}")
    public ResponseEntity<List<Product>> paginate(@PathVariable int offset, @PathVariable int pagesize) {
        List<Product> paginatedProducts = productService.page(pagesize, offset);
        return new ResponseEntity<>(paginatedProducts, HttpStatus.OK);
    }

    @GetMapping("product/{offset}/{pagesize}/{field}")
    public ResponseEntity<List<Product>> paginateAndSort(@PathVariable int offset, @PathVariable int pagesize, @PathVariable String field) {
        List<Product> paginatedSortedProducts = productService.pagesort(pagesize, offset, field);
        return new ResponseEntity<>(paginatedSortedProducts, HttpStatus.OK);
    }
}

package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Product;
import com.examly.springapp.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product>getAllProducts(int page, int size, String sortBy, String direction){
            Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending(): Sort.by(sortBy).ascending();
            Pageable pageable=PageRequest.of(page,size,sort);
            return (Page<Product>) productRepository.findAll(pageable);
    }
    public List<Product> addProducts(List<Product> product) {
        return productRepository.saveAll(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product editProduct(Long id, String productname, double price) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setProductname(productname);
            existingProduct.setPrice(price);
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Product post(Product product) {
        return productRepository.save(product);
    }
    public Page<Product> getPaginatedProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }
    public List<Product> sort(String field) {
        return productRepository.findAll(Sort.by(field));
    }

    public List<Product> page(int pageSize, int offset) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize);
        return productRepository.findAll(pageRequest).getContent();
    }

    public List<Product> pagesort(int pageSize, int offset, String field) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize, Sort.by(field));
        return productRepository.findAll(pageRequest).getContent();
    }
    public List<Product> findByName(String productname){
        return productRepository.findByName(productname);
    }

}

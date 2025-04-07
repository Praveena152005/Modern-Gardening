package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.examly.springapp.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    
    Page<Product>findAll(Pageable pageable);
    @Query("select p from Product p where p.productname=?1")
    List<Product> findByName(String productname);
    @Modifying
    @Query(value = "INSERT INTO Product (productid,productname,price) VALUES(?,?,?)", nativeQuery = true)
    void addProduct(Long productid,String productname,int price);
}

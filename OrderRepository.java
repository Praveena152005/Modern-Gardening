package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  
    Page<Order>findAll(Pageable pageable);
    
    @Query("select o from Order o where o.quantity=?1")
    List<Order> findByQuantity(int quantity);
    @Modifying
    @Query(value = "INSERT INTO Order (orderid,productname,quatity) VALUES(?,?,?)", nativeQuery = true)
    void addOrder(Long orderid,String productname,String quantity);

}

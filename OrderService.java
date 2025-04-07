package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Order;
import com.examly.springapp.model.Product;
import com.examly.springapp.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveGardener(Order order) {
        for (Product product : order.getProduct()) {
            product.setOrder(order); 
        }
        return orderRepository.save(order);
    }

    public Optional<Order> getGardernerById(Long orderid) {
        return orderRepository.findById(orderid);
    }

    public Page<Order>getAllOrders(int page, int size, String sortBy, String direction){
            Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending(): Sort.by(sortBy).ascending();
            Pageable pageable=PageRequest.of(page,size,sort);
            return (Page<Order>) orderRepository.findAll(pageable);
    }
    public List<Order> addOrder(List<Order> order) {
        return orderRepository.saveAll(order);
    }

    
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    public Order editOrder(Long id, String productname, int quantity) {
        Order existingOrder = orderRepository.findById(id).orElse(null);
        if (existingOrder != null) {
            existingOrder.setProductname(productname);
            existingOrder.setQuantity(quantity);
            return orderRepository.save(existingOrder);
        } else {
            return null;
        }
    }


    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Order post(Order order) {
        return orderRepository.save(order);
    }
    public Page<Order> getPaginatedOrders(int page, int size) {
        return orderRepository.findAll(PageRequest.of(page, size));
    }

    public List<Order> page(int pageSize, int offset) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize);
        return orderRepository.findAll(pageRequest).getContent();
    }

    public List<Order> pagesort(int pageSize, int offset, String field) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize, Sort.by(field));
        return orderRepository.findAll(pageRequest).getContent();
    }

    public List<Order> sort(String field) {
        return orderRepository.findAll(Sort.by(field));
    }
    public List<Order> findByQuantity(int quantity){
        return orderRepository.findByQuantity(quantity);
    }
}

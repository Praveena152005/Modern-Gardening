package com.examly.springapp.model;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;
    private String username;
    private String email;
    private int orders;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference("user-order")
    List<Order> order=new ArrayList<>();

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> a)
    {
        for(Order add:a)
        {
            add.setUser(this); 
        }
        this.order.addAll(a);
    }
    
    public void addorder(Order order)
    {
        order.setUser(this); 
        this.order.add(order);
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference("user-booking")
    List<Booking> booking=new ArrayList<>();

    public List<Booking> getBooking() {
        return booking;
    }

    public void setBooking(List<Booking> a)
    {
        for(Booking add:a)
        {
            add.setUser(this); 
        }
        this.booking.addAll(a);
    }
    
    public void addbooking(Booking booking)
    {
        booking.setUser(this); 
        this.booking.add(booking);
    }

    
}

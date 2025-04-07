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
import lombok.NoArgsConstructor;
import lombok.Data;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gardener {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gardenerid;
    private String gardenername;
    private String experiance;

    
    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference("gardener-order")
    List<Order> order=new ArrayList<>();

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> a)
    {
        for(Order add:a)
        {
            add.setGardener(this); 
        }
        this.order.addAll(a);
    }
    
    public void addorder(Order order)
    {
        order.setGardener(this); 
        this.order.add(order);
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference("gardener-booking")
    List<Booking> booking=new ArrayList<>();

    public List<Booking> getBooking() {
        return booking;
    }

    public void setBooking(List<Booking> a)
    {
        for(Booking add:a)
        {
            add.setGardener(this); 
        }
        this.booking.addAll(a);
    }
    
    public void addbooking(Booking booking)
    {
        booking.setGardener(this); 
        this.booking.add(booking);
    }
    
    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference("gardener-diy")
    List<DIYTutorial> diy=new ArrayList<>();

    public List<DIYTutorial> getDiyTutorials() {
        return diy;
    }

    public void setDiyTutorials(List<DIYTutorial> a)
    {
        for(DIYTutorial add:a)
        {
            add.setGardener(this); 
        }
        this.diy.addAll(a);
    }
    
    public void adddiy(DIYTutorial diy)
    {
        diy.setGardener(this); 
        this.diy.add(diy);
    }
    
}

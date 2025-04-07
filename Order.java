package com.examly.springapp.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderid;
    private String productname;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "gardener_id")
    @JsonBackReference("gardener-order")
    private Gardener gardener;

    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-order")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Product> product=new ArrayList<>();

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> a)
    {
        for(Product add:a)
        {
            add.setOrder(this); 
        }
        this.product.addAll(a);
    }
    
    public void addproduct(Product product)
    {
        product.setOrder(this); 
        this.product.add(product);
    }
    
}


package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Page<User>findAll(Pageable pageable);
    
    @Query("select u from User u where u.username=?1")
    List<User> findByName(String Username);
    @Modifying
    @Query(value = "INSERT INTO User (userid,username,email,orders) VALUES(?,?,?,?)", nativeQuery = true)
    void addUser(Long userid,String username,String email,int orders);
}

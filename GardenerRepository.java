package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.examly.springapp.model.Gardener;

@Repository
public interface GardenerRepository extends JpaRepository<Gardener,Long>
{
    
    Page<Gardener>findAll(Pageable pageable);
    
    @Query("select g from Gardener g where g.gardenername=?1")
    List<Gardener> findByName(String gardenername);
    @Modifying
    @Query(value = "INSERT INTO Gardener (gardenerid,gardenername,specialization,orders) VALUES(?,?,?,?)", nativeQuery = true)
    void addGardener(Long gardenerid,String gardenername,String specialization,int orders);

}
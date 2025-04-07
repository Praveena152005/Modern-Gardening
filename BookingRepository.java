package com.examly.springapp.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    @Query("select b from Booking b where b.status=?1")
    List<Booking>findByStatus(String status);
    @Modifying
    @Query(value = "INSERT INTO Booking (bookingid, userid, gardenerid, date, time, status) VALUES(?,?,?,?,?,?)", nativeQuery = true)
    void addBooking(Long bookingid, Long userid,Long gardenerid,Date date,Date time,String status);
    Booking save(List<Booking> booking);

}

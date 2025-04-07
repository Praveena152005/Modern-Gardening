package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Booking;
import com.examly.springapp.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("post")
    public ResponseEntity<List<Booking>> addBookings(@RequestBody List<Booking> bookings) {
        List<Booking> createdBookings = bookingService.addBookings(bookings);
        return new ResponseEntity<>(createdBookings, HttpStatus.CREATED);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        if (booking != null) {
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @PutMapping("put/{id}")
    public ResponseEntity<Booking> editBooking(@PathVariable Long id, @RequestBody Booking booking) {
        Booking updatedBooking = bookingService.editBooking(id, booking);
        if (updatedBooking != null) {
            return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        boolean isDeleted = bookingService.deleteBooking(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody Booking booking) {
        return ResponseEntity.status(201).body(bookingService.post(booking));
    }

    @GetMapping("book/{field}")
    public ResponseEntity<List<Booking>> sort(@PathVariable String field) {
        List<Booking> sortedBookings = bookingService.sort(field);
        return new ResponseEntity<>(sortedBookings, HttpStatus.OK);
    }

    @GetMapping("book/{offset}/{pagesize}")
    public ResponseEntity<List<Booking>> paginate(@PathVariable int offset, @PathVariable int pagesize) {
        List<Booking> paginatedBookings = bookingService.page(pagesize, offset);
        return new ResponseEntity<>(paginatedBookings, HttpStatus.OK);
    }

    @GetMapping("book/{offset}/{pagesize}/{field}")
    public ResponseEntity<List<Booking>> paginateAndSort(@PathVariable int offset, @PathVariable int pagesize, @PathVariable String field) {
        List<Booking> paginatedSortedBookings = bookingService.pagesort(pagesize, offset, field);
        return new ResponseEntity<>(paginatedSortedBookings, HttpStatus.OK);
    }
}

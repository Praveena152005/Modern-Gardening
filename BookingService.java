package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Booking;
import com.examly.springapp.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking post(Booking booking) {
        return bookingRepository.save(booking); 
    }
    
    public Page<Booking> getAllBookings(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return bookingRepository.findAll(pageable);
    }

    public List<Booking> addBookings(List<Booking> bookings) {
        return bookingRepository.saveAll(bookings);
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking editBooking(Long id, Booking updatedBooking) {
        Booking existingBooking = bookingRepository.findById(id).orElse(null);
        if (existingBooking != null) {
            existingBooking.setUserid(updatedBooking.getUserid());
            existingBooking.setGardenerid(updatedBooking.getGardenerid());
            existingBooking.setDate(updatedBooking.getDate());
            existingBooking.setTime(updatedBooking.getTime());
            existingBooking.setStatus(updatedBooking.getStatus());
            return bookingRepository.save(existingBooking);
        }
        return null;
    }

    
    public boolean deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<Booking> getPaginatedBookings(int page, int size) {
        return bookingRepository.findAll(PageRequest.of(page, size));
    }

    
    public List<Booking> sort(String field) {
        return bookingRepository.findAll(Sort.by(field));
    }

    public List<Booking> page(int pageSize, int offset) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize);
        return bookingRepository.findAll(pageRequest).getContent();
    }

    public List<Booking> pagesort(int pageSize, int offset, String field) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize, Sort.by(field));
        return bookingRepository.findAll(pageRequest).getContent();
    }

    public List<Booking> findByStatus(String status) {
        return bookingRepository.findByStatus(status);
    }




}

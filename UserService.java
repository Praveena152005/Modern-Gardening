package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public User createNewUser(User user) {
        user.getBooking().forEach(booking -> booking.setUser(user));
        user.getOrder().forEach(order -> order.setUser(user));
        return userRepository.save(user);
    }

    public Page<User>getAllUsers(int page, int size, String sortBy, String direction){
            Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending(): Sort.by(sortBy).ascending();
            Pageable pageable=PageRequest.of(page,size,sort);
            return (Page<User>) userRepository.findAll(pageable);
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }

    
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    
    public User editUser(Long id, String username, String email, int orders) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(username);
            existingUser.setEmail(email);
            existingUser.setOrders(orders);
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public User post(User user) {
        return userRepository.save(user);
    }
    public Page<User> getPaginatedUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    
    public List<User> sort(String field) {
        return userRepository.findAll(Sort.by(field));
    }

    public List<User> page(int pageSize, int offset) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize);
        return userRepository.findAll(pageRequest).getContent();
    }

    public List<User> pagesort(int pageSize, int offset, String field) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize, Sort.by(field));
        return userRepository.findAll(pageRequest).getContent();
    }

    public List<User> findByName(String username){
        return userRepository.findByName(username);
    }

}

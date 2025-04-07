package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Gardener;
import com.examly.springapp.repository.GardenerRepository;

@Service
public class GardenerService {

    @Autowired
    private GardenerRepository gardenerRepository;
    

    public Gardener createGardener(Gardener gardener) {
        gardener.getOrder().forEach(order -> order.setGardener(gardener));
        gardener.getBooking().forEach(booking -> booking.setGardener(gardener));
        gardener.getDiyTutorials().forEach(diy -> diy.setGardener(gardener));
        return gardenerRepository.save(gardener);
    }

    public Optional<Gardener> getGardernerById(Long gardenerid) {
        return gardenerRepository.findById(gardenerid);
    }

    
    public Page<Gardener>getAllGardeners(int page, int size, String sortBy, String direction){
            Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending(): Sort.by(sortBy).ascending();
            Pageable pageable=PageRequest.of(page,size,sort);
            return (Page<Gardener>) gardenerRepository.findAll(pageable);
    }
    public List<Gardener> addGardeners(List<Gardener> gardeners) {
        return gardenerRepository.saveAll(gardeners);
    }

    
    public Gardener getGardenerById(Long id) {
        return gardenerRepository.findById(id).orElse(null);
    }

    public List<Gardener> getAllGardeners() {
        return gardenerRepository.findAll();
    }

    public Gardener editGardeners(Long id, String gardenername, String experiance) {
        Gardener existingGardener = gardenerRepository.findById(id).orElse(null);
        if (existingGardener != null) {
            existingGardener.setGardenername(gardenername);
            existingGardener.setExperiance(experiance);
            return gardenerRepository.save(existingGardener);
        } else {
            return null;
        }
    }

    public boolean deleteGardener(Long id) {
        if (gardenerRepository.existsById(id)) {
            gardenerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Gardener> post(List<Gardener> gardeners) {
        return gardenerRepository.saveAll(gardeners);
    }
    public Page<Gardener> getPaginatedGardeners(int page, int size) {
        return gardenerRepository.findAll(PageRequest.of(page, size));
    }

    public List<Gardener> sort(String field) {
        return gardenerRepository.findAll(Sort.by(field));
    }


    public List<Gardener> page(int pageSize, int offset) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize);
        return gardenerRepository.findAll(pageRequest).getContent();
    }

    public List<Gardener> pagesort(int pageSize, int offset, String field) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize, Sort.by(field));
        return gardenerRepository.findAll(pageRequest).getContent();
    }

    public List<Gardener> findByName(String gardenername){
        return gardenerRepository.findByName(gardenername);
    }
    
}




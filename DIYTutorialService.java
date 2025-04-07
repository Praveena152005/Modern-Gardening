package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.DIYTutorial;
import com.examly.springapp.model.Gardener;
import com.examly.springapp.repository.DIYTutorialRepository;

@Service
public class DIYTutorialService {

    @Autowired
    private  DIYTutorialRepository diyTutorialRepository;
   
    public DIYTutorial post(DIYTutorial tutorial) {
        return diyTutorialRepository.save(tutorial); 
    }

    public void deleteDIYTutorials(Long tutorialid) {
        diyTutorialRepository.deleteById(tutorialid);
    }

    public Page<DIYTutorial> getAllDIYTutorials(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return diyTutorialRepository.findAll(pageable);
    }

    public DIYTutorial addDIYTutorial(DIYTutorial tutorial) {
        return diyTutorialRepository.save(tutorial);
    }

    public DIYTutorial getDIYTutorialById(Long id) {
        return diyTutorialRepository.findById(id).orElse(null);
    }

    public List<DIYTutorial> getAllDIYTutorials() {
        return diyTutorialRepository.findAll();
    }

    public DIYTutorial editDIYTutorial(Long id, DIYTutorial tutorial) {
        DIYTutorial existingTutorial = diyTutorialRepository.findById(id).orElse(null);
        if (existingTutorial != null) {
            existingTutorial.setTitle(tutorial.getTitle());
            existingTutorial.setDescription(tutorial.getDescription());
            existingTutorial.setVediourl(tutorial.getVediourl());
            return diyTutorialRepository.save(existingTutorial);
        } else {
            return null;
        }
    }

    public boolean deleteDIYTutorial(Long id) {
        if (diyTutorialRepository.existsById(id)) {
            diyTutorialRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<DIYTutorial> getPaginatedDIYTutorials(int page, int size) {
        return diyTutorialRepository.findAll(PageRequest.of(page, size));
    }

    public List<DIYTutorial> sort(String field) {
        return diyTutorialRepository.findAll(Sort.by(field));
    }

    public List<DIYTutorial> page(int pageSize, int offset) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize);
        return diyTutorialRepository.findAll(pageRequest).getContent();
    }

    public List<DIYTutorial> pagesort(int pageSize, int offset, String field) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize, Sort.by(field));
        return diyTutorialRepository.findAll(pageRequest).getContent();
    }

    public List<Gardener> findByTitle(String title) {
        return diyTutorialRepository.findByTitle(title);
    }

}

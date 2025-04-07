package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.DIYTutorial;
import com.examly.springapp.service.DIYTutorialService;

@RestController
@RequestMapping("/diytutorials")
public class DIYTutorialController {

    @Autowired
    private DIYTutorialService diyTutorialService;

    @PostMapping("post")
    public ResponseEntity<DIYTutorial> addDIYTutorial(@RequestBody DIYTutorial tutorial) {
        DIYTutorial createdTutorial = diyTutorialService.addDIYTutorial(tutorial);
        return new ResponseEntity<>(createdTutorial, HttpStatus.CREATED);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<DIYTutorial> getDIYTutorialById(@PathVariable Long id) {
        DIYTutorial tutorial = diyTutorialService.getDIYTutorialById(id);
        if (tutorial != null) {
            return new ResponseEntity<>(tutorial, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<DIYTutorial>> getAllDIYTutorials() {
        List<DIYTutorial> tutorials = diyTutorialService.getAllDIYTutorials();
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    @PutMapping("put/{id}")
    public ResponseEntity<DIYTutorial> editDIYTutorial(@PathVariable Long id, @RequestBody DIYTutorial tutorial) {
        DIYTutorial updatedTutorial = diyTutorialService.editDIYTutorial(id, tutorial);
        if (updatedTutorial != null) {
            return new ResponseEntity<>(updatedTutorial, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteDIYTutorial(@PathVariable Long id) {
        boolean isDeleted = diyTutorialService.deleteDIYTutorial(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody DIYTutorial tutorial) {
        return ResponseEntity.status(201).body(diyTutorialService.post(tutorial));
    }

    @GetMapping("diy/{field}")
    public ResponseEntity<List<DIYTutorial>> sort(@PathVariable String field) {
        List<DIYTutorial> sortedTutorials = diyTutorialService.sort(field);
        return new ResponseEntity<>(sortedTutorials, HttpStatus.OK);
    }

    @GetMapping("diy/{offset}/{pagesize}")
    public ResponseEntity<List<DIYTutorial>> paginate(@PathVariable int offset, @PathVariable int pagesize) {
        List<DIYTutorial> paginatedTutorials = diyTutorialService.page(pagesize, offset);
        return new ResponseEntity<>(paginatedTutorials, HttpStatus.OK);
    }

    @GetMapping("diy/{offset}/{pagesize}/{field}")
    public ResponseEntity<List<DIYTutorial>> paginateAndSort(@PathVariable int offset, @PathVariable int pagesize,  @PathVariable String field) {
        List<DIYTutorial> paginatedSortedTutorials = diyTutorialService.pagesort(pagesize, offset, field);
        return new ResponseEntity<>(paginatedSortedTutorials, HttpStatus.OK);
    }
}

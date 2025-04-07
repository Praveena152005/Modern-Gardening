package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Gardener;
import com.examly.springapp.service.GardenerService;

@RestController
@RequestMapping("/gardeners")
public class GardenerController {

    @Autowired
    private GardenerService gardenerService;

    @PostMapping("post")
    public ResponseEntity<List<Gardener>> addGardeners(@RequestBody List<Gardener> gardeners) {
        List<Gardener> createdGardeners = gardenerService.addGardeners(gardeners);
        return new ResponseEntity<>(createdGardeners, HttpStatus.CREATED);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Gardener> getGardenerById(@PathVariable Long id) {
        Gardener gardener = gardenerService.getGardenerById(id);
        if (gardener != null) {
            return new ResponseEntity<>(gardener, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Gardener>> getAllGardeners() {
        List<Gardener> gardeners = gardenerService.getAllGardeners();
        return new ResponseEntity<>(gardeners, HttpStatus.OK);
    }

    @PutMapping("put")
    public ResponseEntity<List<Gardener>> editGardeners(@RequestBody List<Gardener> gardeners) {
        List<Gardener> updatedGardeners = gardenerService.addGardeners(gardeners);
        return new ResponseEntity<>(updatedGardeners, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteGardener(@PathVariable Long id) {
        boolean isDeleted = gardenerService.deleteGardener(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody List<Gardener> gardeners) {
        return ResponseEntity.status(201).body(gardenerService.post(gardeners));
    }

    @GetMapping("gard/{field}")
    public List<Gardener> sort(@PathVariable String field) {
        return gardenerService.sort(field);
    }

    @GetMapping("gard/{offset}/{pagesize}")
    public List<Gardener> paginate(@PathVariable int offset, @PathVariable int pagesize) {
        return gardenerService.page(pagesize, offset);
    }

    @GetMapping("gard/{offset}/{pagesize}/{field}")
    public List<Gardener> paginateAndSort(@PathVariable int offset, @PathVariable int pagesize, @PathVariable String field) {
        return gardenerService.pagesort(pagesize, offset, field);
    }

}

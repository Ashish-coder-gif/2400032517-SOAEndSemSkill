package soa.controller;

import soa.model.Tourism;
import soa.service.TourismService;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/tourism")
public class TourismController {

    @Autowired
    TourismService service;

    @PostMapping
    public Tourism add(@RequestBody Tourism t) {
        return service.save(t);
    }

    @GetMapping
    public List<Tourism> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Tourism getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Tourism update(@PathVariable Long id,
                          @RequestBody Tourism t) {
        return service.update(id, t);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
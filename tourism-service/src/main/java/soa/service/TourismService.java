package soa.service;

import soa.model.Tourism;
import soa.repository.TourismRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class TourismService {

    @Autowired
    TourismRepository repo;

    public Tourism save(Tourism t) {
        return repo.save(t);
    }

    public List<Tourism> getAll() {
        return repo.findAll();
    }

    public Tourism getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Tourism update(Long id, Tourism t) {

        Tourism old = repo.findById(id).orElse(null);

        if (old != null) {
            old.setName(t.getName());
            old.setLocation(t.getLocation());
            old.setDescription(t.getDescription());

            return repo.save(old);
        }

        return null;
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
package com.evalDanh.evalDanh.controllers;

import com.evalDanh.evalDanh.dao.SalarieDao;
import com.evalDanh.evalDanh.models.Salarie;
import com.evalDanh.evalDanh.views.SalarieView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/salarie")
@RequiredArgsConstructor
@CrossOrigin
public class SalarieController {

    @Autowired
    protected SalarieDao salarieDao;

    @GetMapping("/list")
    @JsonView(SalarieView.class)
    public List<Salarie> show() {
        return salarieDao.findAll();
    }

    @GetMapping("/get/{id}")
    @JsonView(SalarieView.class)
    public ResponseEntity<Salarie> get(@PathVariable Integer id) {
        return salarieDao.findById(id)
                .map(salarie -> ResponseEntity.ok(salarie))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @JsonView(SalarieView.class)
    public ResponseEntity<Salarie> add(@RequestBody Salarie salarie) {
        Salarie saved = salarieDao.save(salarie);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/edit/{id}")
    @JsonView(SalarieView.class)
    public ResponseEntity<Salarie> edit(@PathVariable Integer id, @RequestBody Salarie salarie) {
        return salarieDao.findById(id)
                .map(existing -> {
                    existing.setNom(salarie.getNom());
                    existing.setPrenom(salarie.getPrenom());
                    Salarie updated = salarieDao.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (salarieDao.existsById(id)) {
            salarieDao.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

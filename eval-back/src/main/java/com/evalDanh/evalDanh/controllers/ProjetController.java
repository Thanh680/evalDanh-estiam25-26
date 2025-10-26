package com.evalDanh.evalDanh.controllers;

import com.evalDanh.evalDanh.dao.ProjetDao;
import com.evalDanh.evalDanh.models.Projet;
import com.evalDanh.evalDanh.views.ProjetView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/projet")
@RequiredArgsConstructor
@CrossOrigin
public class ProjetController {

    @Autowired
    protected ProjetDao projetDao;

    @GetMapping("/list")
    @JsonView(ProjetView.class)
    public List<Projet> show() {
        return projetDao.findAll();
    }

    @GetMapping("/get/{id}")
    @JsonView(ProjetView.class)
    public ResponseEntity<Projet> get(@PathVariable Integer id) {
        return projetDao.findById(id)
                .map(projet -> ResponseEntity.ok(projet))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @JsonView(ProjetView.class)
    public ResponseEntity<Projet> addP(@RequestBody Projet projet) {
        Projet saved = projetDao.save(projet);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/edit/{id}")
    @JsonView(ProjetView.class)
    public ResponseEntity<Projet> edit(@PathVariable Integer id, @RequestBody Projet projet) {
        return projetDao.findById(id)
                .map(existing -> {
                    existing.setNom(projet.getNom());
                    Projet updated = projetDao.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (projetDao.existsById(id)) {
            projetDao.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

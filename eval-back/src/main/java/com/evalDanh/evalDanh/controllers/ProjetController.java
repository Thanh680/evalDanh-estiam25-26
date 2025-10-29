package com.evalDanh.evalDanh.controllers;

import com.evalDanh.evalDanh.dao.ProjetDao;
import com.evalDanh.evalDanh.models.Projet;
import com.evalDanh.evalDanh.security.IsUser;
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
    @IsUser
    @JsonView(ProjetView.class)
    public List<Projet> show() {
        return projetDao.findAll();
    }

    @GetMapping("/totaleDuree/{id}")
    @JsonView(ProjetView.class)
    public ResponseEntity<Integer> getDureeTotale(@PathVariable Integer id) {
        return projetDao.findById(id)
                .map(projet -> {
                    int total = projet.getInterventions().stream()
                            .mapToInt(intervention -> intervention.getDuree() != null ? intervention.getDuree() : 0)
                            .sum();
                    return ResponseEntity.ok(total);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/get/{id}")
    @IsUser
    @JsonView(ProjetView.class)
    public ResponseEntity<Projet> get(@PathVariable Integer id) {
        return projetDao.findById(id)
                .map(projet -> ResponseEntity.ok(projet))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @IsUser
    @JsonView(ProjetView.class)
    public ResponseEntity<Projet> add(@RequestBody Projet projet) {
        Projet saved = projetDao.save(projet);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/edit/{id}")
    @IsUser
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
    @IsUser
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (projetDao.existsById(id)) {
            projetDao.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

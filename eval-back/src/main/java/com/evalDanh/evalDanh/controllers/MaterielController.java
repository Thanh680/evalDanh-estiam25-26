package com.evalDanh.evalDanh.controllers;

import com.evalDanh.evalDanh.dao.MaterielDao;
import com.evalDanh.evalDanh.models.Materiel;
import com.evalDanh.evalDanh.security.IsUser;
import com.evalDanh.evalDanh.views.MaterielView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/materiel")
@RequiredArgsConstructor
@CrossOrigin
public class MaterielController {

    @Autowired
    protected MaterielDao materielDao;

    @GetMapping("/list")
    @IsUser
    @JsonView(MaterielView.class)
    public List<Materiel> show() {
        return materielDao.findAll();
    }

    @GetMapping("/get/{id}")
    @IsUser
    @JsonView(MaterielView.class)
    public ResponseEntity<Materiel> get(@PathVariable Integer id) {
        return materielDao.findById(id)
                .map(materiel -> ResponseEntity.ok(materiel))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @IsUser
    @JsonView(MaterielView.class)
    public ResponseEntity<Materiel> add(@RequestBody Materiel materiel) {
        Materiel saved = materielDao.save(materiel);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/edit/{id}")
    @IsUser
    @JsonView(MaterielView.class)
    public ResponseEntity<Materiel> edit(@PathVariable Integer id, @RequestBody Materiel materiel) {
        return materielDao.findById(id)
                .map(existing -> {
                    existing.setDesignation(materiel.getDesignation());
                    Materiel updated = materielDao.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @IsUser
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (materielDao.existsById(id)) {
            materielDao.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

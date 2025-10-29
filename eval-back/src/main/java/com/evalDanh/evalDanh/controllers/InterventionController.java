package com.evalDanh.evalDanh.controllers;

import com.evalDanh.evalDanh.dao.InterventionDao;
import com.evalDanh.evalDanh.models.Intervention;
import com.evalDanh.evalDanh.models.Materiel;
import com.evalDanh.evalDanh.security.IsUser;
import com.evalDanh.evalDanh.views.InterventionView;
import com.evalDanh.evalDanh.views.MaterielView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/intervention")
@RequiredArgsConstructor
@CrossOrigin
public class InterventionController {

    @Autowired
    protected InterventionDao interventionDao;

    @GetMapping("/list")
    @IsUser
    @JsonView(InterventionView.class)
    public List<Intervention> show() {
        return interventionDao.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/salarie/{id}")
    @IsUser
    @JsonView(InterventionView.class)
    public List<Intervention> getInterventionsBySalarie(@PathVariable Integer id) {
        return interventionDao.findBySalaries_IdOrderByDateAsc(id);
    }

    @PostMapping("/add")
    @IsUser
    @JsonView(InterventionView.class)
    public ResponseEntity<Intervention> addIntervention(@RequestBody Intervention intervention) {
        Intervention saved = interventionDao.save(intervention);
        return ResponseEntity.ok(saved);
    }
}


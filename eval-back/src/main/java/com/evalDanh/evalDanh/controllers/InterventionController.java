package com.evalDanh.evalDanh.controllers;

import com.evalDanh.evalDanh.dao.InterventionDao;
import com.evalDanh.evalDanh.models.Intervention;
import com.evalDanh.evalDanh.views.InterventionView;
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

    @PostMapping("/add")
    @JsonView(InterventionView.class)
    public ResponseEntity<Intervention> addIntervention(@RequestBody Intervention intervention) {
        Intervention saved = interventionDao.save(intervention);
        return ResponseEntity.ok(saved);
    }
}


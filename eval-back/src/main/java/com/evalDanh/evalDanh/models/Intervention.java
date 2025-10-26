package com.evalDanh.evalDanh.models;

import com.evalDanh.evalDanh.views.InterventionView;
import com.evalDanh.evalDanh.views.ProjetView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ProjetView.class)
    protected Integer id;

    @JsonView(ProjetView.class)
    protected Instant date;

    @JsonView(ProjetView.class)
    protected Integer duree;

    @ManyToOne
    protected Projet projet;

    @OneToMany(mappedBy = "intervention")
    @JsonView(ProjetView.class)
    protected List<Salarie> salaries = new ArrayList<>();

    @OneToMany(mappedBy = "intervention")
    @JsonView(ProjetView.class)
    protected List<Materiel> materiels = new ArrayList<>();
}

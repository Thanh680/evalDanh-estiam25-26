package com.evalDanh.evalDanh.models;

import com.evalDanh.evalDanh.views.InterventionView;
import com.evalDanh.evalDanh.views.ProjetView;
import com.evalDanh.evalDanh.views.SalarieView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Salarie {

    @Id
    @JsonView({SalarieView.class, ProjetView.class})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @JsonView({SalarieView.class, ProjetView.class})
    protected String nom;

    @JsonView({SalarieView.class, ProjetView.class})
    protected String prenom;

    @ManyToOne
    @JoinColumn(name = "intervention_id")
    protected Intervention intervention;
}

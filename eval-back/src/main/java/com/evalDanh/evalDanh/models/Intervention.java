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
    @JsonView({ProjetView.class, InterventionView.class})
    protected Integer id;

    @JsonView({ProjetView.class, InterventionView.class})
    protected Instant date;

    @JsonView({ProjetView.class, InterventionView.class})
    protected Integer duree;

    @ManyToOne
    protected Projet projet;

    @ManyToMany
    @JsonView({ProjetView.class, InterventionView.class})
    @JoinTable(
            name = "intervention_salarie",
            joinColumns = @JoinColumn(name = "intervention_id"),
            inverseJoinColumns = @JoinColumn(name = "salarie_id")
    )
    protected List<Salarie> salaries;

    @OneToMany
    @JoinTable(
            name = "intervention_materiel",
            joinColumns = @JoinColumn(name = "intervention_id"),
            inverseJoinColumns = @JoinColumn(name = "materiel_id")
    )
    @JsonView({ProjetView.class, InterventionView.class})
    protected List<Materiel> materiels;
}

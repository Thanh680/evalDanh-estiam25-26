package com.evalDanh.evalDanh.models;

import com.evalDanh.evalDanh.views.InterventionView;
import com.evalDanh.evalDanh.views.ProjetView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Materiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(InterventionView.class)
    protected Integer id;

    @JsonView(InterventionView.class)
    protected String designation;

    @ManyToOne
    protected Intervention intervention;
}

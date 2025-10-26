package com.evalDanh.evalDanh.models;

import com.evalDanh.evalDanh.views.ClientView;
import com.evalDanh.evalDanh.views.InterventionView;
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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @JsonView(ClientView.class)
    protected String adresse;

    @OneToOne
    @JsonView(InterventionView.class)
    protected Projet projet;

}

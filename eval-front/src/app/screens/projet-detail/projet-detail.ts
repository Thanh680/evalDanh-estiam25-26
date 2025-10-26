import {Component, inject, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {ProjetService} from '@app/services/projet';
import {InterventionService} from '@app/services/intervention';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';

@Component({
  selector: 'app-projet-detail',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './projet-detail.html',
  styleUrl: './projet-detail.scss'
})
export class ProjetDetail implements OnInit{
  projetId: number = 0;
  projet: any = null;

  constructor(private route: ActivatedRoute) {}

  formBuilder = inject(FormBuilder);

  formAjout = this.formBuilder.group({
    date: ['', Validators.required],
    duree: 1,
    salarie: [
      { nom: 'Dupont', prenom: 'Jean' }
    ],
  });

  projetService = inject(ProjetService);
  interventionService = inject(InterventionService);

  ngOnInit() {
    this.projetId = Number(this.route.snapshot.paramMap.get('id'));
    this.projetService.get(this.projetId).subscribe(data => {
      this.projet = data;
    });
  }


  addIntervention() {
    if (this.formAjout.valid) {
      this.interventionService.add(this.formAjout, this.projetId);
      window.location.reload();
    }
  }
}

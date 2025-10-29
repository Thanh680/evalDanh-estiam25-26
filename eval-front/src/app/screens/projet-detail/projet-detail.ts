import {Component, inject, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {ProjetService} from '@app/services/projet';
import {InterventionService} from '@app/services/intervention';
import {FormArray, FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {SalarieService} from '@app/services/salarie';
import {MaterielService} from '@app/services/materiel';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-projet-detail',
  imports: [
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './projet-detail.html',
  styleUrl: './projet-detail.scss',
  providers: [ProjetService, InterventionService, SalarieService, MaterielService]
})
export class ProjetDetail implements OnInit{
  projetId: number = 0;
  projet: any = null;
  salaries: any[] = [];
  materiels: any[] = [];
  totaleDuree: number = 0;

  constructor(private route: ActivatedRoute) {}

  formBuilder = inject(FormBuilder);

  formAjout = this.formBuilder.group({
    date: ['', Validators.required],
    duree: 1,
    salaries: this.formBuilder.array([]),
    materiels: this.formBuilder.array([]),
  });

  projetService = inject(ProjetService);
  interventionService = inject(InterventionService);
  salarieService = inject(SalarieService);
  materielService = inject(MaterielService);

  ngOnInit() {
    this.projetId = Number(this.route.snapshot.paramMap.get('id'));
    this.projetService.get(this.projetId).subscribe(data => {
      this.projet = data;
    });

    this.salarieService.list().subscribe(data => {
      this.salaries = data;
    });
    this.materielService.list().subscribe(data => {
      this.materiels = data;
    });
    this.projetService.totaleDuree(this.projetId).subscribe(data => {
      this.totaleDuree = data;
    });
  }

  addIntervention() {
    if (this.formAjout.valid) {
      this.interventionService.add(this.formAjout, this.projetId);
      this.ngOnInit();
      window.location.reload();
    }
  }

  onSalarieChange(id: number, event: any) {
    const salarieArray = this.formAjout.get('salaries') as FormArray;
    if (event.target.checked) {
      salarieArray.push(this.formBuilder.control(id));
    } else {
      const index = salarieArray.controls.findIndex(x => x.value === id);
      salarieArray.removeAt(index);
    }
  }

  onMaterielChange(id: number, event: any) {
    const materielArray = this.formAjout.get('materiels') as FormArray;
    if (event.target.checked) {
      materielArray.push(this.formBuilder.control(id));
    } else {
      const index = materielArray.controls.findIndex(x => x.value === id);
      materielArray.removeAt(index);
    }
  }
}

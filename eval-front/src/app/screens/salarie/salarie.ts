import {Component, inject, OnInit} from '@angular/core';
import {SalarieService} from '@app/services/salarie';
import { Salarie } from '@app/models/salarie';
import {InterventionService} from '@app/services/intervention';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-salarie',
  imports: [
    ReactiveFormsModule,
    DatePipe
  ],
  templateUrl: './salarie.html',
  styleUrl: './salarie.scss',
  providers: [SalarieService]
})
export class SalarieDetails implements OnInit{
  salaries: Salarie[] = [];
  selectedSalarieId: number | null = null;
  interventions: any[] = [];

  formBuilder = inject(FormBuilder);
  formGroup = this.formBuilder.group({
    id: ['', Validators.required]
  });

  salarieService = inject(SalarieService);
  interventionService = inject(InterventionService);

  ngOnInit() {
    this.salarieService.list().subscribe(salaries => {
      this.salaries = salaries;
    });
  }

  onSalarieChange(event: Event) {
    const target = event.target as HTMLSelectElement | null;
    const id = target ? target.value : null;
    this.selectedSalarieId = id ? Number(id) : null;
    console
.log('Selected Salarie ID:', this.selectedSalarieId);
    if (this.selectedSalarieId) {
      this.interventionService.listBySalarie(this.selectedSalarieId).subscribe(interventions => {
        this.interventions = interventions;
      });
    } else {
      this.interventions = [];
    }
    console.log(this.interventions)
  }

}

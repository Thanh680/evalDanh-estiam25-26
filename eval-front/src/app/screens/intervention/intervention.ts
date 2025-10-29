import { Component, inject } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

import { HttpClient } from '@angular/common/http';
import { InterventionService } from '@app/services/intervention';

@Component({
  selector: 'app-intervention',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './intervention.html',
  styleUrl: './intervention.scss'
})
export class Intervention {

  interventionService = inject(InterventionService);

  formBuilder = inject(FormBuilder);
  formGroup = this.formBuilder.group({
    date: ['', Validators.required],
    duree: [null, Validators.required]
  });

  add(){
    this.interventionService.add(this.formGroup,0);
  }
  list() {
  }

  delete(id: number){

  }
}

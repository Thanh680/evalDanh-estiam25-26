import {Component, inject, OnInit} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import { ProjetService } from '@app/services/projet';
import {AsyncPipe} from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-projet',
  imports: [AsyncPipe, ReactiveFormsModule, RouterLink],
  templateUrl: './projet.html',
  styleUrl: './projet.scss'
})
export class Projet implements OnInit{

    formBuilder = inject(FormBuilder);
    formGroup = this.formBuilder.group({
      nom: ['', Validators.required]
    });
        projetService = inject(ProjetService);

        selectedProjet: any = null;

        ngOnInit() {
          this.list();
        }


  add() {
    const projet = { nom: this.formGroup.value.nom ?? '' };
    this.projetService.add(projet).subscribe(() => this.list());
  }



        edit() {
          const formValue = this.formGroup.getRawValue();
          const projet = {
            nom: formValue.nom ?? ''
          };
          this.projetService.edit(projet).subscribe(() => this.list());
        }


        delete() {
          this.projetService.delete(this.selectedProjet).subscribe(() => this.list());
        }

        list() {
          this.projetService.list().subscribe();
        }
}

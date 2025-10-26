import { inject, Injectable } from "@angular/core";
import {HttpClient} from '@angular/common/http';
import { Intervention } from "@app/models/intervention";
import { Salarie } from "@app/models/salarie";
import {BehaviorSubject, tap} from 'rxjs';

@Injectable({
  providedIn: 'root'  // ✅ fournit le service globalement
})

export class InterventionService {
interventions$: BehaviorSubject<Intervention[]> = new BehaviorSubject<Intervention[]>([]);

      http = inject(HttpClient);

  list() {
    this.http.get(`http://localhost:8080/api/intervention/list`)
      .subscribe(response => {
        console.log('Intervention data:', response);
      });
  }

  add(formGroup: any, projetId: number) {
    const intervention: Intervention = {
      date: new Date(formGroup.value.date),
      duree: formGroup.value.duree,
      salarie: [
    { nom: 'Dupont', prenom: 'Jean' }
  ],
      projet: { id: projetId }
    };
    console.log('Prepared intervention:', formGroup.value);
    this.http.post(`http://localhost:8080/api/intervention/add`, intervention)
      .subscribe(response => {
        console.log('Adding intervention:', intervention);
      });
    }

  edit(intervention: Intervention) {
    this.http.put(`http://localhost:8080/api/intervention`, intervention)
      .subscribe(response => {
        console.log('Editing intervention:', intervention);
      });
  }

  delete(id: number) {
    this.http.delete(`http://localhost:8080/api/intervention/${id}`)
      .subscribe(response => {
        console.log('Deleting intervention:', id);
      });
  }
}

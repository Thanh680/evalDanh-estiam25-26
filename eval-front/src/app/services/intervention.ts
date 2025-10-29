import { inject, Injectable } from "@angular/core";
import {HttpClient} from '@angular/common/http';
import { Intervention } from "@app/models/intervention";
import { Salarie } from "@app/models/salarie";
import {BehaviorSubject, tap, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
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

  listBySalarie(salarieId: number): Observable<Intervention[]> {
    return this.http.get<Intervention[]>(`http://localhost:8080/api/intervention/salarie/${salarieId}`);
  }

  add(formGroup: any, projetId: number) {
    const intervention: Intervention = {
      date: new Date(formGroup.value.date),
      duree: formGroup.value.duree,
      salaries: formGroup.value.salaries.map((id: number) => ({ id })),
      materiels: formGroup.value.materiels.map((id: number) => ({ id })),
      projet: { id: projetId }
    };
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

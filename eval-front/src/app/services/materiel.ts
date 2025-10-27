import {BehaviorSubject, tap} from 'rxjs';
import {Projet} from '@app/models/projet';
import {inject} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Materiel} from '@app/models/materiel';

export class MaterielService {
  materiel$: BehaviorSubject<Materiel[]> = new BehaviorSubject<Materiel[]>([]);

  http = inject(HttpClient);

  list() {
    return this.http.get<Materiel[]>(`http://localhost:8080/api/materiel/list`)
      .pipe(tap(materiels => {
        this.materiel$.next(materiels)
      }))
  }
  get(id: number){
    return this.http.get<Materiel>(`http://localhost:8080/api/materiel/get/${id}`)
      .pipe(tap(() => this.list().subscribe()));
  }
  add(materiel: Materiel) {
    return this.http.post<Materiel>(`http://localhost:8080/api/materiel/add`, materiel)
      .pipe(tap(() => this.list().subscribe()));
  }

  edit(materiel: Materiel) {
    return this.http.put<Materiel>(`http://localhost:8080/api/materiel/edit/${materiel.id}`, materiel)
      .pipe(tap(() => this.list().subscribe()));
  }

  delete(id: number) {
    return this.http.delete<void>(`http://localhost:8080/api/materiel/delete/${id}`)
      .pipe(tap(() => this.list().subscribe()));
  }
}

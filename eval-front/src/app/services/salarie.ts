import {BehaviorSubject, tap} from 'rxjs';
import {Materiel} from '@app/models/materiel';
import {inject} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Salarie} from '@app/models/salarie';

export class SalarieService {
  salarie$: BehaviorSubject<Salarie[]> = new BehaviorSubject<Salarie[]>([]);

  http = inject(HttpClient);

  list() {
    return this.http.get<Salarie[]>(`http://localhost:8080/api/salarie/list`)
      .pipe(tap(salaries => {
        this.salarie$.next(salaries)
      }))
  }
  get(id: number){
    return this.http.get<Salarie>(`http://localhost:8080/api/salarie/get/${id}`)
      .pipe(tap(() => this.list().subscribe()));
  }
  add(salarie: Salarie) {
    return this.http.post<Salarie>(`http://localhost:8080/api/salarie/add`, salarie)
      .pipe(tap(() => this.list().subscribe()));
  }

  edit(salarie: Salarie) {
    return this.http.put<Salarie>(`http://localhost:8080/api/salarie/edit/${salarie.id}`, salarie)
      .pipe(tap(() => this.list().subscribe()));
  }

  delete(id: number) {
    return this.http.delete<void>(`http://localhost:8080/api/salarie/delete/${id}`)
      .pipe(tap(() => this.list().subscribe()));
  }
}

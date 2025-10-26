import {Projet} from '@app/models/projet';
import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, tap} from 'rxjs';

@Injectable({
  providedIn: 'root'  // ✅ fournit le service globalement
})

export class ProjetService {
  projet$: BehaviorSubject<Projet[]> = new BehaviorSubject<Projet[]>([]);

  http = inject(HttpClient);

  list() {
    return this.http.get<Projet[]>(`http://localhost:8080/api/projet/list`)
      .pipe(tap(projets => {
        this.projet$.next(projets)
      }))
  }
  get(id: number){
    return this.http.get<Projet>(`http://localhost:8080/api/projet/get/${id}`)
      .pipe(tap(() => this.list().subscribe()));
  }
  add(projet: Projet) {
    return this.http.post<Projet>(`http://localhost:8080/api/projet/add`, projet)
      .pipe(tap(() => this.list().subscribe()));
  }

  edit(projet: Projet) {
    return this.http.put<Projet>(`http://localhost:8080/api/projet/edit/${projet.id}`, projet)
      .pipe(tap(() => this.list().subscribe()));
  }

  delete(id: number) {
    return this.http.delete<void>(`http://localhost:8080/api/projet/delete/${id}`)
      .pipe(tap(() => this.list().subscribe()));
  }
}

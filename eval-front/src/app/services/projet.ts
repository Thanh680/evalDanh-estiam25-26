import {Projet} from '@app/models/projet';
import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, tap} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class ProjetService {
  projet$: BehaviorSubject<Projet[]> = new BehaviorSubject<Projet[]>([]);
  token = localStorage.getItem('token');

  http = inject(HttpClient);

  list() {
    return this.http.get<Projet[]>(`http://localhost:8080/api/projet/list`,{
      headers: { Authorization: `Bearer ${this.token}` }})
      .pipe(tap(projets => {
        this.projet$.next(projets)
      }))
  }
  get(id: number){
    return this.http.get<Projet>(`http://localhost:8080/api/projet/get/${id}`,{
      headers: { Authorization: `Bearer ${this.token}` }})
      .pipe(tap(() => this.list().subscribe()));
  }
  add(projet: Projet) {
    return this.http.post<Projet>(`http://localhost:8080/api/projet/add`, projet,{
      headers: { Authorization: `Bearer ${this.token}` }})
      .pipe(tap(() => this.list().subscribe()));
  }

  edit(projet: Projet) {
    return this.http.put<Projet>(`http://localhost:8080/api/projet/edit/${projet.id}`, projet,{
      headers: { Authorization: `Bearer ${this.token}` }})
      .pipe(tap(() => this.list().subscribe()));
  }

  delete(id: number) {
    return this.http.delete<void>(`http://localhost:8080/api/projet/delete/${id}`,{
      headers: { Authorization: `Bearer ${this.token}` }})
      .pipe(tap(() => this.list().subscribe()));
  }
}

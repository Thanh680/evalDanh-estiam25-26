import {Materiel} from '@app/models/materiel';
import {BehaviorSubject, tap} from 'rxjs';
import {inject} from '@angular/core';
import {AppUser} from '@app/models/appuser';
import {HttpClient} from '@angular/common/http';
import {Projet} from '@app/models/projet';

export class AppuserService {
  appuser$: BehaviorSubject<AppUser[]> = new BehaviorSubject<AppUser[]>([]);

  http = inject(HttpClient);
  token = localStorage.getItem('token');

  login(user: { email: string; password: string }) {
    return this.http.post('http://localhost:8080/api/auth/login', user, { responseType: 'text' });
  }

  getProjet() {
    return this.http.get<number>('http://localhost:8080/api/auth/getProjet',{
      headers: { Authorization: `Bearer ${this.token}` }});
  }

  isAdmin() {
    return this.http.get<boolean>('http://localhost:8080/api/auth/isAdmin',{
      headers: { Authorization: `Bearer ${this.token}` }});
  }

}

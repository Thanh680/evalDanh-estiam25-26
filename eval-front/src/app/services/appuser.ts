import {Materiel} from '@app/models/materiel';
import {BehaviorSubject, tap} from 'rxjs';
import {inject} from '@angular/core';
import {AppUser} from '@app/models/appuser';
import {HttpClient} from '@angular/common/http';

export class AppuserService {
  appuser$: BehaviorSubject<AppUser[]> = new BehaviorSubject<AppUser[]>([]);

  http = inject(HttpClient);

  login(user: { email: string; password: string }) {
    return this.http.post('http://localhost:8080/api/auth/login', user, { responseType: 'text' });
  }
}

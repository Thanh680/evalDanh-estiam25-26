import {Component, signal, OnInit, inject} from '@angular/core';
import { RouterLink, RouterOutlet, RouterLinkActive } from '@angular/router';
import { Router } from '@angular/router';
import {Projet} from '@app/models/projet';
import {AppuserService} from '@app/services/appuser';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './app.html',
  styleUrl: './app.scss',
  providers: [AppuserService]
})
export class App implements OnInit {

  constructor(private router: Router) {}

  protected readonly title = signal('eval');
  appuserService = inject(AppuserService);
  loggedIn = signal(this.isLoggedIn());
  projet: number | null = null;
  isAdmin: boolean | null = null;

  ngOnInit() {
    const token = localStorage.getItem('token');
    if (token) {
      this.appuserService.getProjet().subscribe(projet => {
        this.projet = projet;
      });
      this.appuserService.isAdmin().subscribe(result => {
        this.isAdmin = result;
      });
    }
  }

  isLoggedIn(): boolean {
    const token = localStorage.getItem('token');
    return !!token;
  }

  logout() {
    localStorage.removeItem('token');
    this.ngOnInit();
    this.router.navigate(['/']).then(() => {
      window.location.reload();
    });
  }
}

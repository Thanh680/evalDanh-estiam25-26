import { Routes } from '@angular/router';
import { Home } from './screens/home/home';
import { Login } from './screens/login/login';
import { Intervention } from './screens/intervention/intervention';
import { PageNotFound } from './screens/page-not-found/page-not-found';
import { Projet } from './screens/projet/projet';
import {ProjetDetail} from '@screens/projet-detail/projet-detail';
import {SalarieDetails} from '@screens/salarie/salarie';

export const routes: Routes = [
  { path: 'home', component: Home },
  { path: 'projet', component: Projet },
  { path: 'projet/:id', component: ProjetDetail },
  { path: 'salarie', component: SalarieDetails },
  { path: 'login', component: Login },
  { path: '', redirectTo: "home", pathMatch: 'full' },
  { path: '**', component: PageNotFound }
];

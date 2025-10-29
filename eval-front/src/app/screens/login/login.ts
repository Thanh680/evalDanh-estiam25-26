import { Component, inject } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import {AppuserService} from '@app/services/appuser';

@Component({
  selector: 'app-login',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss',
  providers: [AppuserService]
})
export class Login {

  appuserService = inject(AppuserService);

  formBuilder = inject(FormBuilder);
  formGroup = this.formBuilder.group({
    email: ['',[Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$"), Validators.required]],
    password: ['',[Validators.required]]
  })

  login(){
    const user = {
      email: this.formGroup.value.email ?? '',
      password: this.formGroup.value.password ?? ''
    };
    console.log(user);
    this.appuserService.login(user).subscribe({
      next: (token) => {
        // Connexion réussie
        console.log('Token reçu:', token);
      },
      error: (err) => {
        console.log('Erreur reçue:', err);
        if (err.status === 401) {
          // Email ou mot de passe incorrect
          alert('Email ou mot de passe incorrect');
        }
      }
    });
  }
}

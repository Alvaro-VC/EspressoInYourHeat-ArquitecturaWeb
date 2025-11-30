import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-editar-perfil-componente',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './editar-perfil-componente.html',
  styleUrl: './editar-perfil-componente.css',
})
export class EditarPerfilComponente {
  profileForm: FormGroup;

  profilePreview = {
    name: 'Paulo Alvarez Gutierrez',
    age: 21,
    city: 'Lima, Perú',
    avatarUrl: 'assets/img/paulo-avatar.png' // cambia por tu imagen real
  };

  constructor(private fb: FormBuilder, private router: Router) {
    this.profileForm = this.fb.group({
      firstName: ['Paulo', Validators.required],
      lastName: ['Alvarez Gutierrez', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      username: ['', Validators.required],
      preferences: ['']
    });
  }

  get firstName() {
    return this.profileForm.get('firstName');
  }

  get lastName() {
    return this.profileForm.get('lastName');
  }

  get email() {
    return this.profileForm.get('email');
  }

  get username() {
    return this.profileForm.get('username');
  }

  onSubmit(): void {
    if (this.profileForm.invalid) {
      this.profileForm.markAllAsTouched();
      return;
    }

    // Aquí luego conectas con tu servicio de usuario
    console.log('Perfil guardado', this.profileForm.value);
    alert('Cambios guardados correctamente (mock).');
  }

  // Navegación barra inferior
  onRegresar(): void {
    this.router.navigateByUrl('/match');
  }

  onHome(): void {
    this.router.navigateByUrl('/');
  }

  onDescubrirMas(): void {
    this.router.navigateByUrl('/comunidad');
  }

  onVerCafeterias(): void {
    this.router.navigateByUrl('/cafeterias');
  }
}

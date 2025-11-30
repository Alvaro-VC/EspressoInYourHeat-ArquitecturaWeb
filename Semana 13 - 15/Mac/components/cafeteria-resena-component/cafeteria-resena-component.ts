import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cafeteria-resena-component',
  imports: [],
  templateUrl: './cafeteria-resena-component.html',
  styleUrl: './cafeteria-resena-component.css',
})
export class CafeteriaResenaComponent {
  constructor(private router: Router) {
  }
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
  onEditarPerfil(): void {
    this.router.navigateByUrl('/editar-perfil');
  }
}

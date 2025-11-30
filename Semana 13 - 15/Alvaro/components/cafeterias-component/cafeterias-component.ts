import { Component } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-cafeterias-component',
  imports: [],
  templateUrl: './cafeterias-component.html',
  styleUrl: './cafeterias-component.css',
})
export class CafeteriasComponent {
  constructor(private router: Router) {}

  ngOnInit(){

  }
  onRegresar(): void {
    this.router.navigateByUrl('/home');
  }

  onHome(): void {
    this.router.navigateByUrl('/');
  }
  onIrMatches(): void {
    this.router.navigateByUrl('/match');
  }
  onDescubrirMas(): void {
    this.router.navigateByUrl('/match');
  }

  onVerCafeterias(): void {
    this.router.navigateByUrl('/cafeterias');
  }
  onEditarPerfil(): void {
    this.router.navigateByUrl('/editar-perfil');
  }
}

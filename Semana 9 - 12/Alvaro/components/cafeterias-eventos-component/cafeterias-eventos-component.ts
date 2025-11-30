import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cafeterias-eventos-component',
  imports: [
    RouterLink
  ],
  templateUrl: './cafeterias-eventos-component.html',
  styleUrl: './cafeterias-eventos-component.css',
})
export class CafeteriasEventosComponent {
  constructor(private router: Router) {}
}

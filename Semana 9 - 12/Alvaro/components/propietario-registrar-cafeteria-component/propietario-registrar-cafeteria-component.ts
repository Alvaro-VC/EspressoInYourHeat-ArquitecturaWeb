import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CafeteriaService } from '../../services/cafeteria-service';
import { Cafeteria } from '../../model/cafeteria';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PropietarioCafeteriaService } from '../../services/propietario-cafeteria-service';

@Component({
  selector: 'app-propietario-registrar-cafeteria',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './propietario-registrar-cafeteria-component.html',
  styleUrls: ['./propietario-registrar-cafeteria-component.css']
})
export class PropietarioRegistrarCafeteriaComponent {

  cafeteriaForm!: FormGroup;
  fb = inject(FormBuilder);
  cafeteriaService = inject(CafeteriaService);
  propietarioService = inject(PropietarioCafeteriaService);
  router = inject(Router);

  propietarioId!: number;

  constructor() {
    this.cafeteriaForm = this.fb.group({
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      direccion: ['', Validators.required],
      telefono: ['', Validators.required],
      horarioAtencion: ['', Validators.required],
      aforo: ['', [Validators.required, Validators.min(1)]],
    });
  }

  ngOnInit() {
    const userId = Number(localStorage.getItem('usuarioId'));

    this.propietarioService.obtenerPorIdPropio(userId).subscribe({
      next: (idPropietario: number) => {
        this.propietarioId = idPropietario;
        console.log("Propietario ID cargado:", this.propietarioId);
      },
      error: (err) => console.error("Error obteniendo propietario:", err)
    });
  }

  onSubmit() {
    if (this.cafeteriaForm.invalid) {
      this.cafeteriaForm.markAllAsTouched();
      alert("Por favor completa todos los campos");
      return;
    }

    const valores = this.cafeteriaForm.value;

    const cafeteria: Cafeteria = {
      id: 0,
      nombre: valores.nombre!,
      descripcion: valores.descripcion!,
      direccion: valores.direccion!,
      telefono: valores.telefono!,
      horarioAtencion: valores.horarioAtencion!,
      aforo: Number(valores.aforo),

      // ⭐ ESTA ES LA FORMA CORRECTA
      propietarioCafeteria: { id: this.propietarioId }
    };

    console.log("Enviando cafetería:", cafeteria);

    this.cafeteriaService.insert(cafeteria).subscribe({
      next: () => {
        alert("Cafetería registrada correctamente");
        this.router.navigate(['/propietariointerfaz']);
      },
      error: err => {
        console.error(err);
        alert("Error al registrar cafetería");
      }
    });
  }
}

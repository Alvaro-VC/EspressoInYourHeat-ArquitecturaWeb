import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { RouterLink, Router } from '@angular/router';
import { Evento } from '../../model/evento';
import { EventoService } from '../../services/evento-service';
import { CafeteriaService } from '../../services/cafeteria-service';
import { PropietarioCafeteriaService } from '../../services/propietario-cafeteria-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-propietario-registrar-evento',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink, CommonModule],
  templateUrl: './propietario-registrar-evento-component.html',
  styleUrls: ['./propietario-registrar-evento-component.css']
})
export class PropietarioRegistrarEventoComponent {

  eventoForm!: FormGroup;
  fb = inject(FormBuilder);
  eventoService = inject(EventoService);
  cafeteriaService = inject(CafeteriaService);
  propietarioService = inject(PropietarioCafeteriaService);
  router = inject(Router);

  cafeterias: any[] = [];
  propietarioId!: number;

  constructor() {
    this.eventoForm = this.fb.group({
      titulo: ['', Validators.required],
      descripcion: ['', Validators.required],
      estado: ['', Validators.required],
      fechaInicio: ['', Validators.required],
      fechaFin: ['', Validators.required],
      cantidadParticipantes: ['', [Validators.required, Validators.min(1)]],
      cafeteriaId: ['', Validators.required]
    });
  }

  ngOnInit() {
    const usuarioId = Number(localStorage.getItem('usuarioId'));

    this.propietarioService.obtenerPorIdPropio(usuarioId).subscribe({
      next: (propietarioId) => {
        this.propietarioId = propietarioId;

        this.cafeteriaService.encontrarPorPropietarioId(propietarioId).subscribe({
          next: (cafs) => {
            this.cafeterias = cafs;
          },
          error: (err) => console.error("Error cargando cafeterías:", err)
        });
      },
      error: (err) => console.error("Error obteniendo propietario:", err)
    });
  }

  // 🔥 Convertir DD/MM/YYYY → YYYY-MM-DD (LocalDate spring boot)
  convertToISO(dateStr: string): string {
    if (!dateStr.includes("/")) return dateStr; // ya está en formato válido
    const [day, month, year] = dateStr.split("/");
    return `${year}-${month}-${day}`;
  }

  onSubmit() {
    if (this.eventoForm.invalid) {
      this.eventoForm.markAllAsTouched();
      alert("Por favor completa todos los campos");
      return;
    }

    const f = this.eventoForm.value;

    // 🔥 Convertimos las fechas ANTES de enviar al backend
    const fechaInicioISO = this.convertToISO(f.fechaInicio);
    const fechaFinISO = this.convertToISO(f.fechaFin);

    const evento: Evento = {
      id: 0,
      titulo: f.titulo!,
      descripcion: f.descripcion!,
      estado: f.estado!,
      fechaInicio: fechaInicioISO,
      fechaFin: fechaFinISO,
      cantidadParticipantes: Number(f.cantidadParticipantes),
      cafeteria: { id: Number(f.cafeteriaId) }
    };

    console.log("Evento enviado (FINAL):", evento);

    this.eventoService.insert(evento).subscribe({
      next: () => {
        alert("Evento registrado correctamente");
        this.router.navigate(['/propietariointerfaz']);
      },
      error: (err) => {
        console.error("ERROR BACKEND:", err);
        alert("Ocurrió un error al registrar el evento");
      }
    });
  }
}

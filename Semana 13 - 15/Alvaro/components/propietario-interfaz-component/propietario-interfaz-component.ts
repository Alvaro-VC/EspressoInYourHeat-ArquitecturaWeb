import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { EventoService } from '../../services/evento-service';
import { Evento } from '../../model/evento';
import { PropietarioCafeteriaService } from '../../services/propietario-cafeteria-service';
import { CafeteriaService } from '../../services/cafeteria-service';

@Component({
  selector: 'app-propietario-interfaz-component',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './propietario-interfaz-component.html',
  styleUrls: ['./propietario-interfaz-component.css'],
})
export class PropietarioInterfazComponent {

  fb = inject(FormBuilder);
  router = inject(Router);
  eventoService = inject(EventoService);
  propietarioService = inject(PropietarioCafeteriaService);
  cafeteriaService = inject(CafeteriaService);

  eventsOpen = false;
  filtersOpen = false;

  filtroActivo: string | null = null;
  showCafesBlock = true;

  eventosList: any[] = [];
  editandoEvento: any = null;

  // ⭐ LISTA DE CAFETERÍAS
  cafeterias: any[] = [];

  formFechaEstado = this.fb.group({
    fecha: [''],
    estado: [''],
    cafeteriaId: ['']  // ⭐ AGREGADO
  });

  formRango = this.fb.group({
    inicio: [''],
    fin: ['']
  });

  formCafeteria = this.fb.group({
    cafeteriaId: ['']
  });

  formFavorita = this.fb.group({
    cafeteriaId: ['']
  });

  formEditar = this.fb.group({
    id: [''],
    titulo: [''],
    descripcion: [''],
    estado: [''],
    fechaInicio: [''],
    fechaFin: [''],
    cantidadParticipantes: ['']
  });

  resultados: any[] = [];

  constructor() {}

  ngOnInit() {
    this.cargarEventos();

    // ⭐ ID DEL USUARIO
    const userId = Number(localStorage.getItem('usuarioId'));

    this.propietarioService.obtenerPorIdPropio(userId).subscribe({
      next: (propietarioId) => {

        // ⭐ CARGAR CAFETERÍAS DEL PROPIETARIO
        this.cafeteriaService.encontrarPorPropietarioId(propietarioId).subscribe({
          next: lista => {
            this.cafeterias = lista;
          },
          error: err => console.error("ERROR AL CARGAR CAFETERÍAS", err)
        });

      },
      error: err => console.error(err)
    });
  }

  cargarEventos() {
    this.eventoService.listar().subscribe(data => {
      this.eventosList = data;
    });
  }

  editarEvento(id: number): void {
    this.showCafesBlock = false;
    this.filtroActivo = 'editar';

    this.eventoService.listarPorId(id).subscribe((e: any) => {
      this.editandoEvento = e;

      this.formEditar.patchValue({
        id: String(e.id),
        titulo: e.titulo,
        descripcion: e.descripcion,
        estado: e.estado,
        fechaInicio: e.fechaInicio,
        fechaFin: e.fechaFin,
        cantidadParticipantes: String(e.cantidadParticipantes)
      });
    });
  }

  actualizarEvento(): void {
    const data = this.formEditar.value;

    const eventoActualizado: Evento = {
      id: Number(data.id),
      titulo: data.titulo!,
      descripcion: data.descripcion!,
      estado: data.estado!,
      fechaInicio: data.fechaInicio!,
      fechaFin: data.fechaFin!,
      cantidadParticipantes: Number(data.cantidadParticipantes),
      cafeteria: this.editandoEvento.cafeteria
    };

    this.eventoService.update(eventoActualizado).subscribe(() => {
      alert("Evento actualizado");
      this.showCafesBlock = true;
      this.filtroActivo = null;
    });
  }

  toggleEvents() { this.eventsOpen = !this.eventsOpen; }
  toggleFilters() { this.filtersOpen = !this.filtersOpen; }

  goFilter(filtro: string) {
    this.filtroActivo = filtro;
    this.showCafesBlock = false;
    this.resultados = [];
  }

  mostrarCafeterias() {
    this.showCafesBlock = true;
    this.filtroActivo = null;
  }

  buscarFechaEstado() {
    const f = this.formFechaEstado.value;
    this.eventoService
      .filtrarFechaEstado(f.estado!, f.fecha!)
      .subscribe(r => { this.resultados = r; });
  }

  buscarRango() {
    const f = this.formRango.value;
    this.eventoService.filtrarRango(f.inicio!, f.fin!).subscribe(r => {
      this.resultados = r;
    });
  }

  buscarCafeteria(): void {
    const f = this.formCafeteria.value;
    const id = Number(f.cafeteriaId);
    this.eventoService.filtrarCafeteria(id).subscribe(r => {
      this.resultados = r;
    });
  }

  buscarFavorita(): void {
    const f = this.formFavorita.value;
    const id = Number(f.cafeteriaId);
    this.eventoService.filtrarFavorita(id).subscribe(r => {
      this.resultados = r;
    });
  }
}

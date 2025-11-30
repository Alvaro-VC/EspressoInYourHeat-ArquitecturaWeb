import {inject, Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Evento} from '../model/evento';

@Injectable({
  providedIn: 'root',
})
export class EventoService {
  private url = environment.apiUrl;
  private httpClient = inject(HttpClient);
  constructor(){};
  insert (evento: Evento){
    console.log("Enviando insert: ", evento);
    return this.httpClient.post(this.url + "/evento", evento);
  }
  update(evento: Evento){
    console.log("Enviando update: ", evento);
    return this.httpClient.put(this.url + "/evento", evento);
  }
  delete(id : number){
    return this.httpClient.delete(this.url + "/evento/" + id);
  }
  listar(){
    console.log(this.url + "/eventos")
    return this.httpClient.get<Evento[]>(this.url + "/eventos");
  }
  listarPorId(id : number){
    console.log(this.url + "/evento/" + id)
    return this.httpClient.get<Evento[]>(this.url + "/evento/" + id);
  }
  // === FILTROS ===

  filtrarFechaEstado(estado: string, fecha: string) {
    return this.httpClient.get<Evento[]>(`${this.url}/eventos/${estado},${fecha}`);
  }

  filtrarRango(inicio: string, fin: string) {
    return this.httpClient.get<Evento[]>(`${this.url}/eventos/rango/${inicio},${fin}`);
  }

  filtrarCafeteria(id: number) {
    return this.httpClient.get<Evento[]>(`${this.url}/eventos/cafeteria/${id}`);
  }

  filtrarFavorita(id: number) {
    return this.httpClient.get<Evento[]>(`${this.url}/eventos/cafeteria/${id}`);
  }
}

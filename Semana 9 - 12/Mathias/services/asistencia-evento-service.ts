import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {AsistenciaEvento} from '../model/asistencia-evento';

@Injectable({
  providedIn: 'root',
})
export class AsistenciaEventoService {
  private url =  environment.apiUrl;
  private httpClient = inject(HttpClient);
  constructor(){}
  insert (assistenciaEvento: AsistenciaEvento){
    console.log("Enviando insert: ", assistenciaEvento);
    return this.httpClient.post(this.url + "/asistenciaevento", assistenciaEvento);
  }
  update(assistenciaEvento: AsistenciaEvento){
    console.log("Enviando update: ", assistenciaEvento);
    return this.httpClient.put(this.url + "/asistenciaevento", assistenciaEvento);
  }
  delete(id : number){
    return this.httpClient.delete(this.url + "/asistenciaevento/" + id);
  }
  listar(){
    console.log(this.url + "/asistenciaseventos")
    return this.httpClient.get<AsistenciaEventoService[]>(this.url + "/asistenciaseventos");
  }
  listarPorId(id : number){
    console.log(this.url + "/asistenciaevento/" + id)
    return this.httpClient.get<AsistenciaEventoService[]>(this.url + "/asistenciaevento/" + id);
  }

}

import {inject, Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Mensaje} from '../model/mensaje';

@Injectable({
  providedIn: 'root',
})
export class MensajeService {
  private url = environment.apiUrl;
  private httpClient = inject(HttpClient);
  constructor(){};
  insert (mensaje : Mensaje){
    console.log("Enviando insert: ", mensaje);
    return this.httpClient.post(this.url + "/mensaje", mensaje);
  }
  update(mensaje: Mensaje){
    console.log("Enviando update: ", mensaje);
    return this.httpClient.put(this.url + "/mensaje", mensaje);
  }
  delete(id : number){
    return this.httpClient.delete(this.url + "/mensaje/" + id);
  }
  listar(){
    console.log(this.url + "/mensajes")
    return this.httpClient.get<Mensaje[]>(this.url + "/mensajes");
  }
  listarPorId(id : number){
    console.log(this.url + "/mensaje/" + id)
    return this.httpClient.get<Mensaje[]>(this.url + "/mensaje/" + id);
  }
}

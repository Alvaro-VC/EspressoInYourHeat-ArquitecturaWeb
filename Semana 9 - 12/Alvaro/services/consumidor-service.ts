import {inject, Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Consumidor} from '../model/consumidor';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ConsumidorService {
  private url = environment.apiUrl;
  private httpClient = inject(HttpClient);
  constructor(){};
  insert (consumidor: Consumidor):Observable<any> {
    console.log("Enviando insert: ", consumidor);
    return this.httpClient.post(this.url + "/consumidor", consumidor);
  }
  update(consumidor: Consumidor):Observable<any>{
    console.log("Enviando update: ", consumidor);
    return this.httpClient.put(this.url + "/consumidor", consumidor);
  }
  delete(id : number):Observable<any>{
    return this.httpClient.delete(this.url + "/consumidor/" + id);
  }
  listar():Observable<any>{
    console.log(this.url + "/consumidores")
    return this.httpClient.get<Consumidor[]>(this.url + "/consumidores");
  }
  listarPorId(id : number):Observable<any>{
    console.log(this.url + "/consumidor/" + id)
    return this.httpClient.get<Consumidor[]>(this.url + "/consumidor/" + id);
  }
  obtenerPorIdPropio(id : number){ /*Si se le pasa el id de usuario retorna id de consumidor*/
    console.log(this.url + "/consumidor/propio/" + id)
    return this.httpClient.get<number>(this.url + "/consumidor/propio/" + id)

  }
}

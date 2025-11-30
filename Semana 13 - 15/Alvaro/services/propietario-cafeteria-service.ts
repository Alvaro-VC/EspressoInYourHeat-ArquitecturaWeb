import {inject, Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {PropietarioCafeteria} from '../model/propietario-cafeteria';

@Injectable({
  providedIn: 'root',
})
export class PropietarioCafeteriaService {
  private url = environment.apiUrl;
  private httpClient = inject(HttpClient);
  constructor(){};
  insert (propietariocafeteria : PropietarioCafeteria){
    console.log("Enviando insert: ", propietariocafeteria);
    return this.httpClient.post(this.url + "/propietariocafeteria", propietariocafeteria);
  }
  update(propietariocafeteria: PropietarioCafeteria){
    console.log("Enviando update: ", propietariocafeteria);
    return this.httpClient.put(this.url + "/propietariocafeteria", propietariocafeteria);
  }
  delete(id : number){
    return this.httpClient.delete(this.url + "/propietariocafeteria/" + id);
  }
  listar(){
    console.log(this.url + "/propietarioscafeterias")
    return this.httpClient.get<PropietarioCafeteria[]>(this.url + "/propietarioscafeterias");
  }
  listarPorId(id : number){
    console.log(this.url + "/preferenciacafe/" + id)
    return this.httpClient.get<PropietarioCafeteria[]>(this.url + "/propietariocafeteria/" + id);
  }
  obtenerPorIdPropio(id : number){ /*Si se le pasa el id de usuario retorna id de propietario*/
    console.log(this.url + "/propietariocafeteria/propio/" + id)
    return this.httpClient.get<number>(this.url + "/propietariocafeteria/propio/" + id)

  }
}

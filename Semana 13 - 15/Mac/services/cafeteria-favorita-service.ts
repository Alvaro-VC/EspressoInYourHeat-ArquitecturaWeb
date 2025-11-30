import {inject, Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {CafeteriaFavorita} from '../model/cafeteria-favorita';

@Injectable({
  providedIn: 'root',
})
export class CafeteriaFavoritaService {
  private url = environment.apiUrl;
  private httpClient = inject(HttpClient);
  constructor(){}
  insert (cafeteriaFavorita: CafeteriaFavorita){
    console.log("Enviando insert: ", cafeteriaFavorita);
    return this.httpClient.post(this.url + "/cafeteriafavorita", cafeteriaFavorita);
  }
  update(cafeteriaFavorita: CafeteriaFavorita){
    console.log("Enviando update: ", cafeteriaFavorita);
    return this.httpClient.put(this.url + "/cafeteriafavorita", cafeteriaFavorita);
  }
  delete(id : number){
    return this.httpClient.delete(this.url + "/cafeteriafavorita/" + id);
  }
  listar(){
    console.log(this.url + "/cafeteriasfavoritas")
    return this.httpClient.get<CafeteriaFavorita[]>(this.url + "/cafeteriasfavoritas");
  }
  listarPorId(id : number){
    console.log(this.url + "/cafeteriafavorita/" + id)
    return this.httpClient.get<CafeteriaFavorita[]>(this.url + "/cafeteriafavorita/" + id);
  }
}

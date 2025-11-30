import {inject, Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {PreferenciaCafe} from '../model/preferencia-cafe';

@Injectable({
  providedIn: 'root',
})
export class PreferenciaCafeService {
  private url = environment.apiUrl;
  private httpClient = inject(HttpClient);
  constructor(){};
  insert (preferenciacafe : PreferenciaCafe){
    console.log("Enviando insert: ", preferenciacafe);
    return this.httpClient.post(this.url + "/preferenciacafe", preferenciacafe);
  }
  update(preferenciacafe: PreferenciaCafe){
    console.log("Enviando update: ", preferenciacafe);
    return this.httpClient.put(this.url + "/preferenciacafe", preferenciacafe);
  }
  delete(id : number){
    return this.httpClient.delete(this.url + "/preferenciacafe/" + id);
  }
  listar(){
    console.log(this.url + "/preferenciascafes")
    return this.httpClient.get<PreferenciaCafe[]>(this.url + "/preferenciascafes");
  }
  listarPorId(id : number){
    console.log(this.url + "/preferenciacafe/" + id)
    return this.httpClient.get<PreferenciaCafe[]>(this.url + "/preferenciacafe/" + id);
  }
}

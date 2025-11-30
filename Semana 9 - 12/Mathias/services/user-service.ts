import {inject, Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {User} from '../model/user';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private url = environment.apiUrl;
  private httpClient = inject(HttpClient);
  constructor(){};
  createUser(user:User):Observable<any>{
    return this.httpClient.post<User>(this.url + "/user", user)
  }
  saveUseRole(userId: number, rolId: number):Observable<any>{
    return this.httpClient.post<number>(this.url + "/save/" + userId + "/"+ rolId,{})
  }

}

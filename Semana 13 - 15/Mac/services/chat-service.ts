import {inject, Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Chat} from '../model/chat';

@Injectable({
  providedIn: 'root',
})
export class ChatService {
  private url = environment.apiUrl;
  private httpClient = inject(HttpClient);
  constructor(){}
  insert (chat: Chat){
    console.log("Enviando insert: ", chat);
    return this.httpClient.post(this.url + "/chat", chat);
  }
  update(chat: Chat){
    console.log("Enviando update: ", chat);
    return this.httpClient.put(this.url + "/chat", chat);
  }
  delete(id : number){
    return this.httpClient.delete(this.url + "/chat/" + id);
  }
  listar(){
    console.log(this.url + "/chats")
    return this.httpClient.get<Chat[]>(this.url + "/chats");
  }
  listarPorId(id : number){
    console.log(this.url + "/chat/" + id)
    return this.httpClient.get<Chat[]>(this.url + "/chat/" + id);
  }
  encontrarChatsPorIdConsumidor(id : number){
    console.log(this.url + "/chats/propio/" + id)
    return this.httpClient.get<Chat[]>(this.url + "/chat/propio/" + id);
  }
}

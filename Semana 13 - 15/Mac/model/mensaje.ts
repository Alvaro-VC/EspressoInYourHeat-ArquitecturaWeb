import {Chat} from './chat';
import {Consumidor} from './consumidor';

export class Mensaje {
  id:number ;
  texto:string;
  fechaEmviado:Date;
  chat: Chat;
  consumidor : Consumidor;
}

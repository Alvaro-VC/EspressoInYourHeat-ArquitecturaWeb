import {Evento} from './evento';
import {Consumidor} from './consumidor';

export class AsistenciaEvento {
  id: number;
  fechaRegistro : Date;
  consumidor : Consumidor;
  evento : Evento;
}

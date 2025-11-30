import {Cafeteria} from './cafeteria';

export class Evento {
  id : number ;
  titulo : string;
  descripcion : string;
  estado: string;
  fechaInicio: string | Date;
  fechaFin: string | Date;
  cantidadParticipantes: number;
  cafeteria: { id: number };
}

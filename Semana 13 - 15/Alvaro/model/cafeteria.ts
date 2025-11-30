import {PropietarioCafeteria} from './propietario-cafeteria';

export class Cafeteria {
  id : number;
  nombre : string;
  descripcion : string;
  direccion : string;
  telefono : string;
  horarioAtencion : string;
  aforo :  number;
  propietarioCafeteria!: { id: number };
}

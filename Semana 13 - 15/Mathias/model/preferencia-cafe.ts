import {Consumidor} from './consumidor';

export class PreferenciaCafe {
  id :number ;
  cafe: string;
  intensidad : string;
  nivelAzucar : string;
  conLeche: boolean;
  acompanamiento: string;
  descripcionDetalle : string;
  consumidor: Consumidor;
}

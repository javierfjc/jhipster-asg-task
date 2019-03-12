import { IContacto } from 'app/shared/model/contacto.model';

export interface ICliente {
    id?: number;
    descripcion?: string;
    contactos?: IContacto[];
}

export class Cliente implements ICliente {
    constructor(public id?: number, public descripcion?: string, public contactos?: IContacto[]) {}
}

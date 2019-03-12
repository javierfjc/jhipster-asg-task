import { Moment } from 'moment';
import { IPerfil } from 'app/shared/model/perfil.model';

export interface IEmpleado {
    id?: number;
    nombre?: string;
    email?: string;
    telefono?: string;
    antiguedad?: Moment;
    salario?: number;
    comision?: number;
    tareaId?: number;
    tareaId?: number;
    tareaId?: number;
    departamentoId?: number;
    perfils?: IPerfil[];
    jefeId?: number;
}

export class Empleado implements IEmpleado {
    constructor(
        public id?: number,
        public nombre?: string,
        public email?: string,
        public telefono?: string,
        public antiguedad?: Moment,
        public salario?: number,
        public comision?: number,
        public tareaId?: number,
        public tareaId?: number,
        public tareaId?: number,
        public departamentoId?: number,
        public perfils?: IPerfil[],
        public jefeId?: number
    ) {}
}

import { Moment } from 'moment';
import { IEmpleado } from 'app/shared/model/empleado.model';
import { ITarea } from 'app/shared/model/tarea.model';

export const enum TareasEstado {
    ASIGNADO = 'ASIGNADO',
    INICIADO = 'INICIADO',
    PENDIENTE = 'PENDIENTE',
    APLAZADO = 'APLAZADO',
    FINALIZADO = 'FINALIZADO',
    APROBADO = 'APROBADO',
    INSTALADO = 'INSTALADO'
}

export interface ITarea {
    id?: number;
    descripcion?: string;
    estado?: TareasEstado;
    fechaCreado?: Moment;
    fechaPrevistoInicio?: Moment;
    fechaInicio?: Moment;
    fechaFinal?: Moment;
    horasPrevisto?: number;
    proyectoId?: number;
    contactoId?: number;
    creas?: IEmpleado[];
    asignados?: IEmpleado[];
    validas?: IEmpleado[];
    tareaId?: number;
    maestras?: ITarea[];
    tareaId?: number;
    esperas?: ITarea[];
}

export class Tarea implements ITarea {
    constructor(
        public id?: number,
        public descripcion?: string,
        public estado?: TareasEstado,
        public fechaCreado?: Moment,
        public fechaPrevistoInicio?: Moment,
        public fechaInicio?: Moment,
        public fechaFinal?: Moment,
        public horasPrevisto?: number,
        public proyectoId?: number,
        public contactoId?: number,
        public creas?: IEmpleado[],
        public asignados?: IEmpleado[],
        public validas?: IEmpleado[],
        public tareaId?: number,
        public maestras?: ITarea[],
        public tareaId?: number,
        public esperas?: ITarea[]
    ) {}
}

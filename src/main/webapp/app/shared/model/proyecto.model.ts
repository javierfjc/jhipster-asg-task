import { ITarea } from 'app/shared/model/tarea.model';

export interface IProyecto {
    id?: number;
    descripcion?: string;
    tareas?: ITarea[];
}

export class Proyecto implements IProyecto {
    constructor(public id?: number, public descripcion?: string, public tareas?: ITarea[]) {}
}

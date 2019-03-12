import { IEmpleado } from 'app/shared/model/empleado.model';

export interface IDepartamento {
    id?: number;
    descripcion?: string;
    empleados?: IEmpleado[];
}

export class Departamento implements IDepartamento {
    constructor(public id?: number, public descripcion?: string, public empleados?: IEmpleado[]) {}
}

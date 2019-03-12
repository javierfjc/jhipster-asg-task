import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IEmpleado } from 'app/shared/model/empleado.model';
import { EmpleadoService } from './empleado.service';
import { ITarea } from 'app/shared/model/tarea.model';
import { TareaService } from 'app/entities/tarea';
import { IDepartamento } from 'app/shared/model/departamento.model';
import { DepartamentoService } from 'app/entities/departamento';

@Component({
    selector: 'jhi-empleado-update',
    templateUrl: './empleado-update.component.html'
})
export class EmpleadoUpdateComponent implements OnInit {
    empleado: IEmpleado;
    isSaving: boolean;

    tareas: ITarea[];

    departamentos: IDepartamento[];

    empleados: IEmpleado[];
    antiguedad: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected empleadoService: EmpleadoService,
        protected tareaService: TareaService,
        protected departamentoService: DepartamentoService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ empleado }) => {
            this.empleado = empleado;
            this.antiguedad = this.empleado.antiguedad != null ? this.empleado.antiguedad.format(DATE_TIME_FORMAT) : null;
        });
        this.tareaService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ITarea[]>) => mayBeOk.ok),
                map((response: HttpResponse<ITarea[]>) => response.body)
            )
            .subscribe((res: ITarea[]) => (this.tareas = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.departamentoService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IDepartamento[]>) => mayBeOk.ok),
                map((response: HttpResponse<IDepartamento[]>) => response.body)
            )
            .subscribe((res: IDepartamento[]) => (this.departamentos = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.empleadoService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEmpleado[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEmpleado[]>) => response.body)
            )
            .subscribe((res: IEmpleado[]) => (this.empleados = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.empleado.antiguedad = this.antiguedad != null ? moment(this.antiguedad, DATE_TIME_FORMAT) : null;
        if (this.empleado.id !== undefined) {
            this.subscribeToSaveResponse(this.empleadoService.update(this.empleado));
        } else {
            this.subscribeToSaveResponse(this.empleadoService.create(this.empleado));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmpleado>>) {
        result.subscribe((res: HttpResponse<IEmpleado>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackTareaById(index: number, item: ITarea) {
        return item.id;
    }

    trackDepartamentoById(index: number, item: IDepartamento) {
        return item.id;
    }

    trackEmpleadoById(index: number, item: IEmpleado) {
        return item.id;
    }
}

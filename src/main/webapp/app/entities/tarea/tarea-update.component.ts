import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { ITarea } from 'app/shared/model/tarea.model';
import { TareaService } from './tarea.service';
import { IProyecto } from 'app/shared/model/proyecto.model';
import { ProyectoService } from 'app/entities/proyecto';
import { IContacto } from 'app/shared/model/contacto.model';
import { ContactoService } from 'app/entities/contacto';

@Component({
    selector: 'jhi-tarea-update',
    templateUrl: './tarea-update.component.html'
})
export class TareaUpdateComponent implements OnInit {
    tarea: ITarea;
    isSaving: boolean;

    proyectos: IProyecto[];

    contactos: IContacto[];

    tareas: ITarea[];
    fechaCreado: string;
    fechaPrevistoInicio: string;
    fechaInicio: string;
    fechaFinal: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected tareaService: TareaService,
        protected proyectoService: ProyectoService,
        protected contactoService: ContactoService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tarea }) => {
            this.tarea = tarea;
            this.fechaCreado = this.tarea.fechaCreado != null ? this.tarea.fechaCreado.format(DATE_TIME_FORMAT) : null;
            this.fechaPrevistoInicio =
                this.tarea.fechaPrevistoInicio != null ? this.tarea.fechaPrevistoInicio.format(DATE_TIME_FORMAT) : null;
            this.fechaInicio = this.tarea.fechaInicio != null ? this.tarea.fechaInicio.format(DATE_TIME_FORMAT) : null;
            this.fechaFinal = this.tarea.fechaFinal != null ? this.tarea.fechaFinal.format(DATE_TIME_FORMAT) : null;
        });
        this.proyectoService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IProyecto[]>) => mayBeOk.ok),
                map((response: HttpResponse<IProyecto[]>) => response.body)
            )
            .subscribe((res: IProyecto[]) => (this.proyectos = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.contactoService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IContacto[]>) => mayBeOk.ok),
                map((response: HttpResponse<IContacto[]>) => response.body)
            )
            .subscribe((res: IContacto[]) => (this.contactos = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.tareaService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ITarea[]>) => mayBeOk.ok),
                map((response: HttpResponse<ITarea[]>) => response.body)
            )
            .subscribe((res: ITarea[]) => (this.tareas = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.tarea.fechaCreado = this.fechaCreado != null ? moment(this.fechaCreado, DATE_TIME_FORMAT) : null;
        this.tarea.fechaPrevistoInicio = this.fechaPrevistoInicio != null ? moment(this.fechaPrevistoInicio, DATE_TIME_FORMAT) : null;
        this.tarea.fechaInicio = this.fechaInicio != null ? moment(this.fechaInicio, DATE_TIME_FORMAT) : null;
        this.tarea.fechaFinal = this.fechaFinal != null ? moment(this.fechaFinal, DATE_TIME_FORMAT) : null;
        if (this.tarea.id !== undefined) {
            this.subscribeToSaveResponse(this.tareaService.update(this.tarea));
        } else {
            this.subscribeToSaveResponse(this.tareaService.create(this.tarea));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITarea>>) {
        result.subscribe((res: HttpResponse<ITarea>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackProyectoById(index: number, item: IProyecto) {
        return item.id;
    }

    trackContactoById(index: number, item: IContacto) {
        return item.id;
    }

    trackTareaById(index: number, item: ITarea) {
        return item.id;
    }
}

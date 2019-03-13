import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ITarea } from 'app/shared/model/tarea.model';
import { TareaService } from './tarea.service';

@Component({
    selector: 'jhi-tarea-update',
    templateUrl: './tarea-update.component.html'
})
export class TareaUpdateComponent implements OnInit {
    tarea: ITarea;
    isSaving: boolean;
    fechaCreado: string;
    fechaPrevistoInicio: string;
    fechaInicio: string;
    fechaFinal: string;

    constructor(protected tareaService: TareaService, protected activatedRoute: ActivatedRoute) {}

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
}

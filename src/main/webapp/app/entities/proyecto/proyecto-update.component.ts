import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IProyecto } from 'app/shared/model/proyecto.model';
import { ProyectoService } from './proyecto.service';

@Component({
    selector: 'jhi-proyecto-update',
    templateUrl: './proyecto-update.component.html'
})
export class ProyectoUpdateComponent implements OnInit {
    proyecto: IProyecto;
    isSaving: boolean;

    constructor(protected proyectoService: ProyectoService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ proyecto }) => {
            this.proyecto = proyecto;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.proyecto.id !== undefined) {
            this.subscribeToSaveResponse(this.proyectoService.update(this.proyecto));
        } else {
            this.subscribeToSaveResponse(this.proyectoService.create(this.proyecto));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IProyecto>>) {
        result.subscribe((res: HttpResponse<IProyecto>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}

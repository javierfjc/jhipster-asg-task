import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IDepartamento } from 'app/shared/model/departamento.model';
import { DepartamentoService } from './departamento.service';

@Component({
    selector: 'jhi-departamento-update',
    templateUrl: './departamento-update.component.html'
})
export class DepartamentoUpdateComponent implements OnInit {
    departamento: IDepartamento;
    isSaving: boolean;

    constructor(protected departamentoService: DepartamentoService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ departamento }) => {
            this.departamento = departamento;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.departamento.id !== undefined) {
            this.subscribeToSaveResponse(this.departamentoService.update(this.departamento));
        } else {
            this.subscribeToSaveResponse(this.departamentoService.create(this.departamento));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDepartamento>>) {
        result.subscribe((res: HttpResponse<IDepartamento>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IPerfil } from 'app/shared/model/perfil.model';
import { PerfilService } from './perfil.service';

@Component({
    selector: 'jhi-perfil-update',
    templateUrl: './perfil-update.component.html'
})
export class PerfilUpdateComponent implements OnInit {
    perfil: IPerfil;
    isSaving: boolean;

    constructor(protected perfilService: PerfilService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ perfil }) => {
            this.perfil = perfil;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.perfil.id !== undefined) {
            this.subscribeToSaveResponse(this.perfilService.update(this.perfil));
        } else {
            this.subscribeToSaveResponse(this.perfilService.create(this.perfil));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPerfil>>) {
        result.subscribe((res: HttpResponse<IPerfil>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}

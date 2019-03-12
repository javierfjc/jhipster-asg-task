import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IPerfil } from 'app/shared/model/perfil.model';
import { PerfilService } from './perfil.service';
import { IEmpleado } from 'app/shared/model/empleado.model';
import { EmpleadoService } from 'app/entities/empleado';

@Component({
    selector: 'jhi-perfil-update',
    templateUrl: './perfil-update.component.html'
})
export class PerfilUpdateComponent implements OnInit {
    perfil: IPerfil;
    isSaving: boolean;

    empleados: IEmpleado[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected perfilService: PerfilService,
        protected empleadoService: EmpleadoService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ perfil }) => {
            this.perfil = perfil;
        });
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

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackEmpleadoById(index: number, item: IEmpleado) {
        return item.id;
    }
}

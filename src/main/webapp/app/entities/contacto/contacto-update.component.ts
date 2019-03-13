import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IContacto } from 'app/shared/model/contacto.model';
import { ContactoService } from './contacto.service';
import { ICliente } from 'app/shared/model/cliente.model';
import { ClienteService } from 'app/entities/cliente';

@Component({
    selector: 'jhi-contacto-update',
    templateUrl: './contacto-update.component.html'
})
export class ContactoUpdateComponent implements OnInit {
    contacto: IContacto;
    isSaving: boolean;

    clientes: ICliente[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected contactoService: ContactoService,
        protected clienteService: ClienteService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ contacto }) => {
            this.contacto = contacto;
        });
        this.clienteService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICliente[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICliente[]>) => response.body)
            )
            .subscribe((res: ICliente[]) => (this.clientes = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.contacto.id !== undefined) {
            this.subscribeToSaveResponse(this.contactoService.update(this.contacto));
        } else {
            this.subscribeToSaveResponse(this.contactoService.create(this.contacto));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IContacto>>) {
        result.subscribe((res: HttpResponse<IContacto>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackClienteById(index: number, item: ICliente) {
        return item.id;
    }
}

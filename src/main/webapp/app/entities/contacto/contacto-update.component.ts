import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IContacto } from 'app/shared/model/contacto.model';
import { ContactoService } from './contacto.service';

@Component({
    selector: 'jhi-contacto-update',
    templateUrl: './contacto-update.component.html'
})
export class ContactoUpdateComponent implements OnInit {
    contacto: IContacto;
    isSaving: boolean;

    constructor(protected contactoService: ContactoService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ contacto }) => {
            this.contacto = contacto;
        });
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
}

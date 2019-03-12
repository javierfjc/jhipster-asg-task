/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { EmpleadoService } from 'app/entities/empleado/empleado.service';
import { IEmpleado, Empleado } from 'app/shared/model/empleado.model';

describe('Service Tests', () => {
    describe('Empleado Service', () => {
        let injector: TestBed;
        let service: EmpleadoService;
        let httpMock: HttpTestingController;
        let elemDefault: IEmpleado;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(EmpleadoService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Empleado(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, 0, 0);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        antiguedad: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Empleado', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        antiguedad: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        antiguedad: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Empleado(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Empleado', async () => {
                const returnedFromService = Object.assign(
                    {
                        nombre: 'BBBBBB',
                        email: 'BBBBBB',
                        telefono: 'BBBBBB',
                        antiguedad: currentDate.format(DATE_TIME_FORMAT),
                        salario: 1,
                        comision: 1
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        antiguedad: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Empleado', async () => {
                const returnedFromService = Object.assign(
                    {
                        nombre: 'BBBBBB',
                        email: 'BBBBBB',
                        telefono: 'BBBBBB',
                        antiguedad: currentDate.format(DATE_TIME_FORMAT),
                        salario: 1,
                        comision: 1
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        antiguedad: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Empleado', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});

import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'proyecto',
                loadChildren: './proyecto/proyecto.module#JhipsterTaskProyectoModule'
            },
            {
                path: 'cliente',
                loadChildren: './cliente/cliente.module#JhipsterTaskClienteModule'
            },
            {
                path: 'contacto',
                loadChildren: './contacto/contacto.module#JhipsterTaskContactoModule'
            },
            {
                path: 'tarea',
                loadChildren: './tarea/tarea.module#JhipsterTaskTareaModule'
            },
            {
                path: 'departamento',
                loadChildren: './departamento/departamento.module#JhipsterTaskDepartamentoModule'
            },
            {
                path: 'perfil',
                loadChildren: './perfil/perfil.module#JhipsterTaskPerfilModule'
            },
            {
                path: 'empleado',
                loadChildren: './empleado/empleado.module#JhipsterTaskEmpleadoModule'
            },
            {
                path: 'proyecto',
                loadChildren: './proyecto/proyecto.module#JhipsterTaskProyectoModule'
            },
            {
                path: 'cliente',
                loadChildren: './cliente/cliente.module#JhipsterTaskClienteModule'
            },
            {
                path: 'contacto',
                loadChildren: './contacto/contacto.module#JhipsterTaskContactoModule'
            },
            {
                path: 'tarea',
                loadChildren: './tarea/tarea.module#JhipsterTaskTareaModule'
            },
            {
                path: 'departamento',
                loadChildren: './departamento/departamento.module#JhipsterTaskDepartamentoModule'
            },
            {
                path: 'perfil',
                loadChildren: './perfil/perfil.module#JhipsterTaskPerfilModule'
            },
            {
                path: 'empleado',
                loadChildren: './empleado/empleado.module#JhipsterTaskEmpleadoModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterTaskEntityModule {}

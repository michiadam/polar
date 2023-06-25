import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProjectComponent} from "./component/project/project.component";



const routes: Routes = [
    {path: '', redirectTo: 'home', pathMatch: 'full'},
    {path: 'project/:projectID', component: ProjectComponent},
];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {

}

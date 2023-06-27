import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MatInputModule} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatCardModule} from "@angular/material/card";
import {HttpClientModule} from "@angular/common/http";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatButtonModule} from "@angular/material/button";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatSidenavModule} from "@angular/material/sidenav";
import {ProjectComponent} from './component/project/project.component';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {CommonModule} from "@angular/common";
import {CdkDrag, CdkDropList, CdkDropListGroup} from "@angular/cdk/drag-drop";

@NgModule({
    declarations: [
        AppComponent,
        ProjectComponent,
    ],
    imports: [
        CommonModule,
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        MatInputModule,
        FormsModule,
        MatCardModule,
        MatProgressSpinnerModule,
        MatButtonModule,
        BrowserAnimationsModule,
        ReactiveFormsModule,
        MatSidenavModule,
        FontAwesomeModule,
        CdkDropList,
        CdkDropListGroup,
        CdkDrag
    ],
    providers: [
        HttpClientModule
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}

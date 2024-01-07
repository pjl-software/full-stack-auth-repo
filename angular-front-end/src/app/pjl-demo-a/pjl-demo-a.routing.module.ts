import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DemoAComponent } from './components/demo-a/demo-a.component';

const routes: Routes = [
  {
    path: 'a',
    component: DemoAComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RoutingModuleDemoA {}

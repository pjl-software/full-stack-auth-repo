import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DemoBComponent } from './components/demo-b/demo-b.component';

const routes: Routes = [
  {
    path: 'b',
    component: DemoBComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RoutingModuleDemoB {}

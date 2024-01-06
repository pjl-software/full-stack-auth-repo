import { NgModule } from '@angular/core';
import { PjlSharedModule } from '../pjl-shared/shared.module';
import { DemoBComponent } from './components/demo-b/demo-b.component';
import { RoutingModuleDemoB } from './pjl-demo-b.routing.module';

@NgModule({
  imports: [PjlSharedModule, RoutingModuleDemoB],
  declarations: [DemoBComponent],
  providers: [],
  exports: [DemoBComponent],
})
export class PjlDemoBModule {}

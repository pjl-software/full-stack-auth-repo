import { NgModule } from '@angular/core';
import { PjlDemoBModule } from '../pjl-demo-b/pjl-demo-b.module';
import { PjlSharedModule } from '../pjl-shared/shared.module';
import { DemoAComponent } from './components/demo-a/demo-a.component';
import { RoutingModuleDemoA } from './pjl-demo-a.routing.module';

@NgModule({
  imports: [PjlSharedModule, RoutingModuleDemoA, PjlDemoBModule],
  providers: [],
  declarations: [DemoAComponent],
  exports: [DemoAComponent],
})
export class PjlDemoAModule {}

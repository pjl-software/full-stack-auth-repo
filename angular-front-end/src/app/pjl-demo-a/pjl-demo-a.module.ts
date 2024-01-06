import { NgModule } from '@angular/core';
import { PjlSharedModule } from '../pjl-shared/shared.module';
import { DemoAComponent } from './components/demo-a/demo-a.component';
import { RoutingModuleDemoA } from './pjl-demo-a.routing.module';

@NgModule({
  imports: [PjlSharedModule, RoutingModuleDemoA],
  providers: [],
  declarations: [DemoAComponent],
  exports: [],
})
export class PjlDemoAModule {}

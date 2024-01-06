import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';

@NgModule({
  imports: [
    CommonModule, // Angular Core module, comes with some of the most commonly used built-in Angular functions. Best to import once here, and then import this Module in the application libraries that will need it.
    HttpClientModule, // Custom built Angular services that use HttpClient need this import
  ],
  declarations: [],
  exports: [
    CommonModule, // We must re-export the CommonModule, importing it is not enough
  ],
})
export class PjlSharedModule {}

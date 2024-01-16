import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [CommonModule, FormsModule, HttpClientModule],
  declarations: [],
  exports: [CommonModule, FormsModule],
})
export class PjlSharedModule {}

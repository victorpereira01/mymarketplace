import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { PickAddressPage } from './pickaddress';

@NgModule({
  declarations: [
    PickAddressPage,
  ],
  imports: [
    IonicPageModule.forChild(PickAddressPage),
  ],
})
export class PickaddressPageModule {}

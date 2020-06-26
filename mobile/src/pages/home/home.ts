import { Component } from '@angular/core';
import { NavController, IonicPage, MenuController } from 'ionic-angular';
import { CredentialDTO } from '../../models/credential.dto';

@IonicPage()
@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  creds: CredentialDTO = {
    email: "",
    password: ""
  }

  constructor(public navCtrl: NavController, public menu: MenuController) {
  }

  ionViewWillEnter() {
    this.menu.swipeEnable(false);
  }
  
  ionViewDidLeave() {
    this.menu.swipeEnable(true);
  }
  
  login() {
    console.log(this.creds);
    this.navCtrl.setRoot('CategoriesPage');
  }
}

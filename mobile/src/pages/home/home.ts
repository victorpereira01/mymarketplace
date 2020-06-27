import { Component } from '@angular/core';
import { NavController, IonicPage, MenuController } from 'ionic-angular';
import { CredentialDTO } from '../../models/credential.dto';
import { AuthService } from '../../services/auth.service';

@IonicPage()
@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  cred: CredentialDTO = {
    email: "",
    password: ""
  }

  constructor(public navCtrl: NavController, public menu: MenuController, public auth: AuthService) {
  }

  ionViewWillEnter() {
    this.menu.swipeEnable(false);
  }

  ionViewDidLeave() {
    this.menu.swipeEnable(true);
  }

  ionViewDidEnter() {
    this.auth.refreshToken()
      .subscribe(response => {
        this.auth.successfullLogin(response.headers.get('Authorization'));
        this.navCtrl.setRoot('CategoriesPage');
      },
        error => { });
  }

  login() {
    this.auth.authenticate(this.cred)
      .subscribe(response => {
        this.auth.successfullLogin(response.headers.get('Authorization'));
        this.navCtrl.setRoot('CategoriesPage');
      },
        error => { });
  }

  signUp() {
    this.navCtrl.push('SignupPage')
  }
}

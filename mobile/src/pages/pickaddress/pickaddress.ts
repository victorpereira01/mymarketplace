import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { AddressDTO } from '../../models/address.dto';
import { StorageService } from '../../services/storage.service';
import { ClientService } from '../../services/domain/client.service';


@IonicPage()
@Component({
  selector: 'page-pick-address',
  templateUrl: 'pickaddress.html',
})
export class PickAddressPage {

  items: AddressDTO[];

  constructor(
    public navCtrl: NavController, 
    public navParams: NavParams,
    public storage: StorageService,
    public clientService: ClientService) {
  }

  ionViewDidLoad() {
    let localUser = this.storage.getLocalUser();
    if (localUser && localUser.email) {
      this.clientService.findByEmail(localUser.email)
        .subscribe(response => {
          this.items = response['adresses'];
        },
          error => {
            if (error.status == 403) {
              this.navCtrl.setRoot('HomePage');
            }
          });
    } else {
      this.navCtrl.setRoot('HomePage');
    }
  }
}

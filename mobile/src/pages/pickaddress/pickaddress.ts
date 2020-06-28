import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { AddressDTO } from '../../models/address.dto';


@IonicPage()
@Component({
  selector: 'page-pick-address',
  templateUrl: 'pickaddress.html',
})
export class PickAddressPage {

  items: AddressDTO[];

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  ionViewDidLoad() {
    this.items = [
      {
        id: "1",
        publicPlace: "Rua Quinze de Novembro",
        number: "300",
        complement: "Apto 200",
        neighborhood: "Santa Mônica",
        cep: "48293822",
        city: {
          id: "1",
          name: "Uberlândia",
          state: {
            id: "1",
            name: "Minas Gerais"
          }
        }
      },
      {
        id: "2",
        publicPlace: "Rua Alexandre Toledo da Silva",
        number: "405",
        complement: null,
        neighborhood: "Centro",
        cep: "88933822",
        city: {
          id: "3",
          name: "São Paulo",
          state: {
            id: "2",
            name: "São Paulo"
          }
        }
      }
    ];
  }
}

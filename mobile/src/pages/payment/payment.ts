import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { OrderDTO } from '../../models/order.dto';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@IonicPage()
@Component({
  selector: 'page-payment',
  templateUrl: 'payment.html',
})
export class PaymentPage {

  order: OrderDTO;

  installments: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

  formGroup: FormGroup;

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    public formBuilder: FormBuilder) {
    
      this.order = this.navParams.get('order');
      this.formGroup = this.formBuilder.group({
        installments: [1, Validators.required],
        "@type": ["cardPayment", Validators.required]
      })
  }

  nextPage() {
    this.order.payment = this.formGroup.value;
    console.log(this.order);
  }
}

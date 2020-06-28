import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController } from 'ionic-angular';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CityService } from '../../services/domain/city.service';
import { StateService } from '../../services/domain/state.service';
import { StateDTO } from '../../models/state.dto';
import { CityDTO } from '../../models/city.dto';
import { ClientService } from '../../services/domain/client.service';

@IonicPage()
@Component({
  selector: 'page-signup',
  templateUrl: 'signup.html',
})
export class SignupPage {

  formGroup: FormGroup;

  states: StateDTO[];

  cities: CityDTO[];

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    public formBuilder: FormBuilder,
    public cityService: CityService,
    public stateService: StateService,
    public clientService: ClientService,
    public alertCtrl: AlertController) {
    this.formGroup = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(120)]],
      email: ['', [Validators.required, Validators.email]],
      type: ['', [Validators.required]],
      cpfOrCnpj: ['', [Validators.required, Validators.minLength(11), Validators.maxLength(14)]],
      password: ['', [Validators.required]],
      publicPlace: ['', [Validators.required]],
      number: ['', [Validators.required]],
      complement: ['', []],
      neighborhood: ['', []],
      cep: ['', [Validators.required]],
      tel1: ['', [Validators.required]],
      tel2: ['', []],
      tel3: ['', []],
      stateId: [null, [Validators.required]],
      cityId: [null, [Validators.required]],
    });
  }

  ionViewDidLoad() {
    this.stateService.findAll()
      .subscribe(response => {
        this.states = response;
        this.formGroup.controls.stateId.setValue(this.states[0].id);
        this.updateCities();
      },
        error => { });
  }

  updateCities() {
    let state_Id = this.formGroup.value.stateId;
    this.cityService.findAll(state_Id)
      .subscribe(response => {
        this.cities = response;
        this.formGroup.controls.cityId.setValue(null);
      },
        error => { });
  }

  signupUser() {
    this.clientService.insert(this.formGroup.value)
      .subscribe(response => {
        this.showInsertOk();
      },
        error => { });
  }

  showInsertOk() {
    let alert = this.alertCtrl.create({
      title: 'Sucesso!',
      message: 'Cadastro efetuado com sucesso',
      enableBackdropDismiss: false,
      buttons: [
        {
          text: 'Ok',
          handler: () => {
            this.navCtrl.pop();
          }
        }
      ]
    });
    alert.present();
  }
}


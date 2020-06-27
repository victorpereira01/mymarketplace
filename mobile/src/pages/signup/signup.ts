import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CityService } from '../../services/domain/city.service';
import { StateService } from '../../services/domain/state.service';
import { StateDTO } from '../../models/state.dto';
import { CityDTO } from '../../models/city.dto';

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
    public stateService: StateService) {
      this.formGroup = this.formBuilder.group({
        name: ['Joaquim', [Validators.required, Validators.minLength(5), Validators.maxLength(120)]],
        email: ['joaquim@gmail.com', [Validators.required, Validators.email]],
        type: ['1', [Validators.required]],
        cpfOuCnpj: ['06134596280', [Validators.required, Validators.minLength(11), Validators.maxLength(14)]],
        password: ['123', [Validators.required]],
        publicPlace: ['Rua Via', [Validators.required]],
        number: ['25', [Validators.required]],
        complement: ['Apto 3', []],
        neighborhood: ['Copacabana', []],
        cep: ['10828333', [Validators.required]],
        tel1: ['977261827', [Validators.required]],
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
      error => {});
  }

  updateCities() {
    let state_Id = this.formGroup.value.stateId;
    this.cityService.findAll(state_Id)
      .subscribe(response => {
        this.cities = response;
        this.formGroup.controls.cityId.setValue(null);
      },
      error => {});
  }

  signupUser() {
  }
}

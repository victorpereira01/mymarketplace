import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { ProductDTO } from '../../models/product.dto';
import { ProductService } from '../../services/domain/product.service';
import { API_CONFIG } from '../../config/api.config';

@IonicPage()
@Component({
  selector: 'page-product-detail',
  templateUrl: 'product-detail.html',
})
export class ProductDetailPage {

  item: ProductDTO;

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    public productService: ProductService) {
  }

  ionViewDidLoad() {
    let product_id = this.navParams.get('product_id');
    console.log(product_id);
    this.productService.findById(product_id)
      .subscribe(response => {
        this.item = response;
        this.getImageUrlIfExists();
      },
        error => { })
  }

  getImageUrlIfExists() {
    this.productService.getImageFromBucket(this.item.id)
      .subscribe(response => {
        this.item.imgUrl = `${API_CONFIG.bucketBaseUrl}/prod${this.item.id}.jpg`;
        console.log(this.item.imgUrl);
      },
      error => {});
  }
}

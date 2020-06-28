import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { ProductDTO } from '../../models/product.dto';
import { ProductService } from '../../services/domain/product.service';
import { API_CONFIG } from '../../config/api.config';

@IonicPage()
@Component({
  selector: 'page-produtos',
  templateUrl: 'products.html',
})
export class ProductsPage {

  items: ProductDTO[];

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    public productService: ProductService) {
  }

  ionViewDidLoad() {
    let category_id = this.navParams.get('categoryId');
    this.productService.findByCategory(category_id)
      .subscribe(response => {
        this.items = response['content'];
        this.loadImageUrls();
      },
        error => { });
  }

  loadImageUrls() {
    for (var i = 0; i < this.items.length; i++) {
      let item = this.items[i];
      this.productService.getSmallImageFromBucket(item.id)
        .subscribe(response => {
          item.imgUrl = `${API_CONFIG.bucketBaseUrl}/prod${item.id}-small.jpg`;
        },
          error => { });
    }
  }

  showDetail(product_id: string) {
    this.navCtrl.push('ProductDetailPage', {product_id: product_id});
  }
}
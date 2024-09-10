import { Injectable } from '@angular/core';
import {Product} from "../common/product";

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  static addProducts(products: Product[]) {
    localStorage.setItem('products', JSON.stringify(products))
  }

  static getProductById(productId: number) {
    const json: string | null = localStorage.getItem('products');
    const products: Product[] = json ? JSON.parse(json) : [];
    return products.find(p => p.id == productId);
  }

  static clear() {
    localStorage.clear();
  }

}

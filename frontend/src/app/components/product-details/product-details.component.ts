import {Component, OnInit} from '@angular/core';
import {Product} from "../../common/product";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../services/storage.service";
import {ProductService} from "../../services/product.service";
import {NgClass, NgForOf} from "@angular/common";

@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [
    NgForOf,
    NgClass
  ],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent implements OnInit {
  productId!: number;
  product?: Product;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private productService: ProductService) {
  }

  ngOnInit(): void {
    this.productId = +this.route.snapshot.paramMap.get("productId")!;
    this.product = StorageService.getProductById(this.productId);
    if (!this.product) {
      this.productService.getProductById(this.productId).subscribe(data => {
        this.product = data;
      })
    }
  }

}

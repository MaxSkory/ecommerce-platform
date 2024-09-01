import {Component, OnInit} from '@angular/core';
import {Product} from "../../common/product";
import {ProductService} from "../../services/product.service";
import {CurrencyPipe, NgForOf, NgOptimizedImage} from "@angular/common";
import {Page} from "../../common/page";
import {DefaultImgDirective} from "../../util/no-image-directive.util";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [
    NgForOf,
    CurrencyPipe,
    NgOptimizedImage,
    DefaultImgDirective,
  ],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css',
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];
  page: Page = new Page(8, 0);

  constructor(private productService: ProductService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listProducts();
    })
  }

  listProducts(){
    let categoryId: number | null = +this.route.snapshot.paramMap.get("id")!;
    if (categoryId) {
      this.productService.getPageByCategoryId(categoryId, this.page.number, this.page.size).subscribe(
        data => {
          this.products = data.content;
          this.page = data.page;
        }
      )
      return;
    }
    this.productService.getPage(this.page.number, this.page.size).subscribe(
      data => {
        this.products = data.content;
        this.page = data.page;
      }
    )
  }
}


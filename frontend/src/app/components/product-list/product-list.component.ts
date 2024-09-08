import {Component, OnInit} from '@angular/core';
import {Product} from "../../common/product";
import {ProductService} from "../../services/product.service";
import {CurrencyPipe, NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {Page} from "../../common/page";
import {DefaultImgDirective} from "../../util/no-image-directive.util";
import {ActivatedRoute} from "@angular/router";
import {Constants} from "../../enums/constants";

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [
    NgForOf,
    CurrencyPipe,
    NgOptimizedImage,
    DefaultImgDirective,
    NgIf,
  ],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css',
})
export class ProductListComponent implements OnInit {

  categoryId?: number;
  products?: Product[];
  page: Page = new Page(8, 0);
  searchMode?: boolean;

  constructor(private productService: ProductService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    console.log(this.route.snapshot)
    this.route.paramMap.subscribe(() => {
      this.listProducts();
    })
  }

  listProducts(){
    this.categoryId = +this.route.snapshot.paramMap.get("categoryId")!;
    this.searchMode = this.route.snapshot.paramMap.has("keywords")
    this.searchMode ? this.handleSearchProducts() : this.handleListProducts()
  }

  handleSearchProducts() {
    const keywords: string | null = this.route.snapshot.paramMap.get("keywords")!;
    if (keywords) {
      this.productService.search(keywords.trim()
          .replaceAll(/[^\s\w]/g, "")
          .replaceAll(" ", ","),
        this.categoryId, this.page.number, this.page.size).subscribe(
        data => {
          this.products = data.content;
          this.page = data.page;
        }
      )
    } else {
      this.handleListProducts();
    }
  }

  private handleListProducts() {
    if (this.categoryId) {
      this.productService.getPageByCategoryId(this.categoryId, this.page.number, this.page.size).subscribe(
        data => {
          this.products = data.content;
          this.page = data.page;
        }
      )
    } else {
      this.productService.getPage(this.page.number, this.page.size).subscribe(
        data => {
          this.products = data.content;
          this.page = data.page;
        }
      )
    }
  }

  getProductDetails(product: Product) {
    console.log(JSON.stringify(product))
  }

  protected readonly Constants = Constants;
}


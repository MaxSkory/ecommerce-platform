import {Component, OnInit} from '@angular/core';
import {Product} from "../../common/product";
import {ProductService} from "../../services/product.service";
import {CurrencyPipe, NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {Page} from "../../common/page";
import {DefaultImgDirective} from "../../util/no-image-directive.util";
import {ActivatedRoute, Router} from "@angular/router";
import {Constants} from "../../enums/constants";
import {StorageService} from "../../services/storage.service";

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
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listProducts().subscribe(
        data => {
          this.products = data.content;
          this.page = data.page;
          StorageService.addProducts(this.products);
        });
      ;
    })
  }

  listProducts() {
    this.categoryId = +this.route.snapshot.paramMap.get("categoryId")!;
    this.searchMode = this.route.snapshot.paramMap.has("keywords");
    if (this.searchMode) {
      return this.handleSearchProducts();
    }
    return this.handleListProducts();
  }

  handleSearchProducts() {
    const keywords: string = this.route.snapshot.paramMap.get("keywords")!;
    return this.productService.search(keywords.trim()
        .replaceAll(/[^\s\w]/g, "")
        .replaceAll(" ", ","),
      this.categoryId, this.page.number, this.page.size);
  }

  private handleListProducts() {
    if (this.categoryId) {
      return this.productService.getPageByCategoryId(this.categoryId, this.page.number, this.page.size);
    }
    return this.productService.getPage(this.page.number, this.page.size);
  }

  openProductDetails(product: Product) {
    this.router.navigateByUrl(`/products/${product.id}`);
  }

  protected readonly Constants = Constants;
}


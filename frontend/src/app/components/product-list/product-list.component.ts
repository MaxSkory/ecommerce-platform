import {Component, OnInit} from '@angular/core';
import {Product} from "../../models/product";
import {ProductService} from "../../services/product.service";
import {CurrencyPipe, NgForOf, NgOptimizedImage} from "@angular/common";
import {Page} from "../../models/page";
import {IMG_PLACEHOLDER} from "../../enums/constants";

@Component({
    selector: 'app-product-list',
    standalone: true,
    imports: [
        NgForOf,
        CurrencyPipe,
        NgOptimizedImage
    ],
    templateUrl: './product-list.component.html',
    styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit {
    products: Product[] = [];
    page: Page = new Page(20, 0);

    constructor(private productService: ProductService) {
    }

    ngOnInit(): void {
        this.listProducts();
    }

    listProducts() {
        this.productService.getAll(this.page.number, this.page.size).subscribe(
            data => {
                this.products = data.content;
                this.page = data.page;
                console.log(this.products);
                console.log(this.page);
            }
        )
    }

    protected readonly IMG_PLACEHOLDER = IMG_PLACEHOLDER;
}


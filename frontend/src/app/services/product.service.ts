import {Injectable} from '@angular/core';
import {Product} from "../models/product";
import {Page} from "../models/page";
import {HttpClientUtil} from "../util/http-client.util";

@Injectable({
    providedIn: 'root'
})
export class ProductService {

    constructor(private httpClient: HttpClientUtil) {
    }

    getAll(pageNumber?: number, pageSize?: number, sort?: string) {
        const url: string = "http://localhost:8081/api/products";
        let params = this.httpClient.createHttpParams({
            page: pageNumber,
            size: pageSize, sort: sort
        });
        return this.httpClient.fetch<GetProductsResponse>(url, false, params);
    }
}

interface GetProductsResponse {
    content: Product[];
    page: Page;
}

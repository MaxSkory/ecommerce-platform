import {Injectable} from '@angular/core';
import {Product} from "../common/product";
import {Page} from "../common/page";
import {HttpClientUtil} from "../util/http-client.util";
import {Constants} from "../enums/constants";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  baseUrl: string = `${Constants.BASE_URL}/products`;

  constructor(private httpClient: HttpClientUtil) {
  }

  getPage(pageNumber?: number, pageSize?: number, sort?: string) {
    let params = this.httpClient.createHttpParams({
      page: pageNumber,
      size: pageSize,
      sort: sort
    });
    return this.httpClient.fetch<GetProductsResponse>(this.baseUrl, false, params);
  }

  getPageByCategoryId(categoryId: number, pageNumber?: number, pageSize?: number, sort?: string) {
    let params = this.httpClient.createHttpParams({
      page: pageNumber,
      size: pageSize,
      sort: sort
    })
    return this.httpClient.fetch<GetProductsResponse>(`${this.baseUrl}/category/${categoryId}`, false, params)
  }
}

interface GetProductsResponse {
  content: Product[];
  page: Page;
}

import {Injectable} from '@angular/core';
import {Product} from "../common/product";
import {Page} from "../common/page";
import {HttpClientUtil} from "../util/http-client.util";
import {Constants} from "../enums/constants";
import {StorageService} from "./storage.service";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  baseUrl: string = `${Constants.BASE_URL}/products`;

  constructor(private httpClient: HttpClientUtil) {
  }

  getPage(pageNumber?: number, pageSize?: number, sort?: string) {
    const params = this.httpClient.createHttpParams({
      page: pageNumber,
      size: pageSize,
      sort: sort
    });
    return this.httpClient.fetch<GetProductListResponse>(this.baseUrl, false, params);
  }

  getPageByCategoryId(categoryId: number, pageNumber?: number, pageSize?: number, sort?: string) {
    const params = this.httpClient.createHttpParams({
      page: pageNumber,
      size: pageSize,
      sort: sort
    })
    return this.httpClient.fetch<GetProductListResponse>(`${this.baseUrl}/category/${categoryId}`, false, params)
  }

  getProductById(id: number) {
    return this.httpClient.fetch<Product>(`${this.baseUrl}/${id}`, false);
  }

  search(keywords: string, categoryId?: number, pageNumber?: number, pageSize?: number, sort?: string) {
    const params = this.httpClient.createHttpParams({
      categoryIds: categoryId,
      page: pageNumber,
      size: pageSize,
      sort: sort
    })
    return this.httpClient.fetch<GetProductListResponse>(`${this.baseUrl}/search?keywords=${keywords}`, false, params)
  }
}

interface GetProductListResponse {
  content: Product[];
  page: Page;
}

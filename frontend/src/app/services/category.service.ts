import { Injectable } from '@angular/core';
import {HttpClientUtil} from "../util/http-client.util";
import {Category} from "../common/category";
import {Constants} from "../enums/constants";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  url: string = `${Constants.BASE_URL}/categories`;

  constructor(private httpClient: HttpClientUtil) { }

  getAll() {
    return this.httpClient.fetch<Category[]>(this.url, false);
  }
}

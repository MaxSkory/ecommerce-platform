import { Routes } from '@angular/router';
import {ProductListComponent} from "./components/product-list/product-list.component";

export const routes: Routes = [
  {path: 'products/category/:categoryId/search/:keywords', component: ProductListComponent},
  {path: 'products/category/:categoryId', component: ProductListComponent},
  {path: 'products/search/:keywords', component: ProductListComponent},
  {path: 'products', component: ProductListComponent},
  {path: '', redirectTo: '/products', pathMatch: 'full'},
  {path: '**', redirectTo: '/products', pathMatch: 'full'}
];

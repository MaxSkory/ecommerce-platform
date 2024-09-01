import {Component, OnInit} from '@angular/core';
import {CategoryService} from "../../services/category.service";
import {Category} from "../../common/category";
import {NgForOf} from "@angular/common";
import {RouterLink, RouterLinkActive} from "@angular/router";

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [
    NgForOf,
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './sidebar-menu.component.html',
  styleUrl: './sidebar-menu.component.css'
})
export class SidebarMenuComponent implements OnInit {
  categories: Category[] = [];

  constructor(private categoryService: CategoryService) {
  }

  ngOnInit(): void {
    this.listCategories();
  }

  private listCategories() {
    this.categoryService.getAll().subscribe(
      data => {
        this.categories = data;
      }
    )
  }
}

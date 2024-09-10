import {Component} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {
  categoryId?: number;

  constructor(private router: Router, private route: ActivatedRoute) {
  }

  search(value: string) {
    this.categoryId = +this.route.snapshot.firstChild?.paramMap.get('categoryId')!
    this.categoryId ?
      this.router.navigate(['products', 'category', this.categoryId, {
        keywords: value,
        }
      ]) :
      this.router.navigate(['products', {
        keywords: value,
      }
      ]);
  }
}

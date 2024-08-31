export class Page {
  size!: number;
  number!: number;
  totalElements?: number;
  totalPages?: number;


  constructor(size: number, number: number) {
    this.size = size;
    this.number = number;
  }
}

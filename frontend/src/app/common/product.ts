export class Product {
  id!: number;
  sku!: string;
  name!: string;
  description?: string;
  unitPrice!: number;
  imageUrls!: string[];
  primaryImageUrl!: string;
  active!: boolean;
  unitsInStock!: number;
  dateCreated!: Date;
  lastUpdate!: Date;
  categoryId!: number;
}

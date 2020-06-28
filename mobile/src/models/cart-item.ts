import { ProductDTO } from "./product.dto";

export interface CartItem {
    quantity: number;
    product: ProductDTO;
}
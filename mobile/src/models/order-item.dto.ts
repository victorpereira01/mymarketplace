import { RefDTO } from "./ref.dto";

export interface OrderItemDTO {
    quantity: number;
    product: RefDTO;
}
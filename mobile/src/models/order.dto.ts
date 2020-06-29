import { RefDTO } from "./ref.dto";
import { PaymentDTO } from "./payment.dto";
import { OrderItemDTO } from "./order-item.dto";

export interface OrderDTO {
    client: RefDTO;
    deliveryAddress: RefDTO;
    payment: PaymentDTO;
    items: OrderItemDTO[];
}
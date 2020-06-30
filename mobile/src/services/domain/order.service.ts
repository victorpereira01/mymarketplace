import { HttpClient } from "@angular/common/http";
import { API_CONFIG } from "../../config/api.config";
import { Injectable } from "@angular/core";
import { OrderDTO } from "../../models/order.dto";

@Injectable()
export class OrderService {

    constructor(public http: HttpClient) {
    }

    insert(obj: OrderDTO) {
        return this.http.post(
            `${API_CONFIG.baseUrl}/orders`,
            obj,
            {
                observe: 'response',
                responseType: 'text'
            }
        )
    }

}
import { HttpClient } from "@angular/common/http";
import { API_CONFIG } from "../../config/api.config";
import { Observable } from "rxjs/Rx";
import { Injectable } from "@angular/core";
import { StateDTO } from "../../models/state.dto";

@Injectable()
export class StateService {

    constructor(public http: HttpClient) {
    }

    findAll(): Observable<StateDTO[]> {
        return this.http.get<StateDTO[]>(`${API_CONFIG.baseUrl}/states`);
    }
}
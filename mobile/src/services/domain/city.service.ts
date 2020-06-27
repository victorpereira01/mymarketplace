import { HttpClient } from "@angular/common/http";
import { API_CONFIG } from "../../config/api.config";
import { Observable } from "rxjs/Rx";
import { CityDTO } from "../../models/city.dto";
import { Injectable } from "@angular/core";

@Injectable()
export class CityService {

    constructor(public http: HttpClient) {
    }

    findAll(state_id: string): Observable<CityDTO[]> {
        return this.http.get<CityDTO[]>(`${API_CONFIG.baseUrl}/states/${state_id}/cities`);
    }
}
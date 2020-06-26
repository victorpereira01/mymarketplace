import { Injectable } from "@angular/core";
import { CredentialDTO } from "../models/credential.dto";
import { HttpClient } from "@angular/common/http";
import { API_CONFIG } from "../config/api.config";

@Injectable()
export class AuthService {

    constructor(public http: HttpClient) {
    }

    authenticate(cred: CredentialDTO) {
        return this.http.post(
            `${API_CONFIG.baseUrl}/login`,
            cred,
            {
                observe: 'response',
                responseType: 'text'
            })
    }
}
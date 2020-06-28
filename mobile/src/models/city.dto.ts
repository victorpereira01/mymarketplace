import { StateDTO } from "./state.dto";

export interface CityDTO {
    id: string;
    name: string;
    state?: StateDTO;
}
import { Address } from "../model/Address";

export interface Pharmacy {
    id: number;
    name: string;
    description: string;
    address: Address;
    consultingPrice: number;
}
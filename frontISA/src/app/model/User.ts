import { Address } from "./Address";

export interface User{
    id: number;
    name:String;
    lastname:String;
    password:String;
    phoneNumber:String;
    address:Address;
    avgRate:number;
    penal:number;
}
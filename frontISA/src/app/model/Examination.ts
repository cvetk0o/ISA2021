import { User } from "./User";

export interface Examination {

    id: number;
    startTime:string;
    endTime:string;
    price: number;
    status: number;
    dermatologist:User;
    
}
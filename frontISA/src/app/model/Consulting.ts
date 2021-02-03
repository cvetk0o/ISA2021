import { User } from "./User";

export interface Consulting {
    id:number;
    startTime:string;
    endTime:string;
    date:string;
    pharmacist:User;
    pharmacy:string;
}
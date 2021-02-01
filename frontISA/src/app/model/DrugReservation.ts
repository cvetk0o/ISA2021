import { Item } from "./Item";

export interface DrugReservation{
    reservedAt: string;
    createdAt: string;
    id: number;
    item: Item;
}
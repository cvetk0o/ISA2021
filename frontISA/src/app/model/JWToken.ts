export interface JWToken {
    token : string;
    role : string;
    sub: string;
    userId: string;
    iat: number; 
    exp : number;
}
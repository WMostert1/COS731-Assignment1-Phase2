import {Car} from './car'


export class Transaction{
    id : number;
    car : Car;
    date : Date;
    description: string;
    transactionType : string;
}
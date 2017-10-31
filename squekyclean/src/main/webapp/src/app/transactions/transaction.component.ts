import { Component, Input, OnInit } from '@angular/core';
import { Transaction } from "./transaction";
import { TransactionService } from "./transaction.service";
import { ActivatedRoute } from "@angular/router";
import { CognitoUtil } from "../service/cognito.service";
import {User} from './user'
import { ToastsManager } from "ng2-toastr/ng2-toastr";

import { ConfirmationDialog } from "../confirm-dialog/confirmation-dialog";
import { MdDialog, MdDialogRef } from '@angular/material';

@Component({
  selector: 'transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit{
  constructor(public dialog: MdDialog, private route: ActivatedRoute,private transactionService : TransactionService, private cognitoUtil: CognitoUtil, private toastr: ToastsManager){}

  dialogRef: MdDialogRef<ConfirmationDialog>;
  
  userTrans : User;
  currentTransactions : Transaction [];
  ngOnInit(): void {
    this.transactionService.getUser(this.cognitoUtil.getCurrentUser().getUsername()).subscribe(data => {
      if(data == undefined || data == null)
        console.log('No data for user.');
      
      this.userTrans = data;
    });

    this.currentTransactions = null;
  }




  hasCars() : boolean{
    if(this.userTrans == undefined || this.userTrans == null)
     return false;

    if(this.userTrans.cars == undefined || this.userTrans.cars == null)
    return false;
    
    return this.userTrans.cars.length != 0
  }

 hasTransactions() : boolean{
    return this.currentTransactions != null && this.currentTransactions != undefined &&  this.currentTransactions.length != 0;
 }

 addCar(){
     this.dialogRef = this.dialog.open(ConfirmationDialog, {
            disableClose: false
        });
        this.dialogRef.componentInstance.confirmMessage = "Please enter the registration number for the car:"

        this.dialogRef.afterClosed().subscribe(result => {
            if (result && this.dialogRef.componentInstance.val) {
                

                if(this.userTrans == undefined || this.userTrans == null){
                    let email = this.cognitoUtil.getCurrentUser().getUsername();
                    this.transactionService.register(email, this.dialogRef.componentInstance.val);
                     this.transactionService.getUser(this.cognitoUtil.getCurrentUser().getUsername()).subscribe(data => {
                    if(data == undefined || data == null)
                        console.log('No data for user.');
                    
                        this.userTrans = data;
                    });
                }else{
                    
                    this.userTrans.cars.push({registrationNumber :    this.dialogRef.componentInstance.val, id : -1, transactions : []});
                    this.updateCars();
            }
            }
            this.dialogRef = null;
        });
 }

 removeCar(regNo : string){
    for(let i = 0; i < this.userTrans.cars.length; i++){
        if(this.userTrans.cars[i].registrationNumber == regNo){
          this.userTrans.cars.splice(i,1);
          this.updateCars();
          return;
        }
    }
    console.log("Couldn't find reg number");
 }

 updateCars(){
    let email = this.cognitoUtil.getCurrentUser().getUsername();
    let regNos = [];

    for(let car of this.userTrans.cars)
        regNos.push(car.registrationNumber);
    console.log(regNos);    

    this.transactionService.updateCars(regNos, email);
 }

 setCurrentTransactions(regNo : string){
    for(let car of this.userTrans.cars){
        if(car.registrationNumber == regNo)
        {
            this.currentTransactions = car.transactions;
            return;
        }
    }
    console.log('Could not match registration numbers of cars');
 }

}

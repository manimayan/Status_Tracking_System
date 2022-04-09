//import { Component, OnInit } from '@angular/core';
//import { FormControl, FormGroup, Validators,FormBuilder, FormArray } from '@angular/forms';
//import {MessagesModule} from 'primeng/primeng';
//import {MessageModule} from 'primeng/primeng';
//import {MessageService} from 'primeng/components/common/messageservice';
//import { Publicholidays } from "app/model/publicholidays";
//import {DisablevacationService} from "./disablevacation.service";
//import { NavbarService } from "app/navbar.service";
//
//@Component({
//    selector: 'app-disablevacation',
//    templateUrl: './disablevacation.component.html',
//    styleUrls: ['./disablevacation.component.css'],
//    providers:[DisablevacationService,MessageService]
//})
//
//export class DisablevacationComponent implements OnInit {
//    
//    public msgs: MessageModule[] = [];
//    public holiday:Publicholidays[]=[];
//    public multiple: Array<Date>;
//    public  isVacation : boolean = true; 
//
//constructor(private service:DisablevacationService,private messageService: MessageService,public nav: NavbarService) {   }
//
//ngOnInit() 
//{this.nav.show();  }
//
//
//onChange($event)
//{
//    if(!this.multiple)
//    {
//        this.isVacation=true;
//    }     
//    this.isVacation=false;
//}
//
//onUnselects($event)
//{
//    alert("unselct")
//    if(!this.multiple)
//    {
//        this.isVacation=false;
//    }     
//    this.isVacation=false;
//} 
//Savepublicholidays(value:Array<Date>){
//    this.msgs = [];
//    var i=0;
//    value.forEach(val => {
//        let ph = new Publicholidays("", (val.getMonth() + 1 + "/" + val.getDate() + "/" + val.getFullYear()));
//        this.holiday[i]=ph;
//        i++;
//    });
//    this.service.Savepublicholidays(this.holiday).subscribe(
//            response=>{
//                this.msgs.push({ severity: 'success', summary: '', detail: "Public Holidays Added Sucessfully!" });
//                console.log(value);
//            },
//            error => {
//                return false;
//            }
//    );
//}
//}
//


import { Component, OnInit } from '@angular/core';

import { FormControl, FormGroup, Validators,FormBuilder, FormArray } from '@angular/forms';

import {MessagesModule} from 'primeng/primeng';

import {MessageModule} from 'primeng/primeng';

import {MessageService} from 'primeng/components/common/messageservice';

import { Publicholidays } from "app/model/publicholidays";

import {DisablevacationService} from "./disablevacation.service";

import { NavbarService } from "app/navbar.service";

import {AutoCompleteModule} from 'primeng/primeng';

@Component({

selector: 'app-disablevacation',

templateUrl: './disablevacation.component.html',

styleUrls: ['./disablevacation.component.css'],

providers:[DisablevacationService,MessageService]

})

export class DisablevacationComponent implements OnInit {

public msgs: MessageModule[] = [];

public holiday:Publicholidays[]=[];

public multiple: Array<Date>;

public isVacation : boolean = true;

public allowDeselectDate: true;

constructor(private service:DisablevacationService,private messageService: MessageService,public nav: NavbarService) { }

ngOnInit()

{this.nav.show(); }

　

onOptionClick($event)

{



if(!this.multiple)

{

this.isVacation=true;

}

this.isVacation=false;

}

onUnselects($event)

{



if(!this.multiple)

{

this.isVacation=false;

}

this.isVacation=false;

}





Savepublicholidays(value:Array<Date>){

this.msgs = [];

var i=0;

value.forEach(val => {

let ph = new Publicholidays("", (val.getMonth() + 1 + "/" + val.getDate() + "/" + val.getFullYear()));

this.holiday[i]=ph;

i++;

});

this.service.Savepublicholidays(this.holiday).subscribe(

response=>{

this.msgs.push({ severity: 'success', summary: '', detail: "Public Holidays Added Sucessfully!" });

console.log(value);

},

error => {

return false;

}

);

}

}



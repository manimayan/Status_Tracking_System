import { Injectable } from '@angular/core';
import { BehaviorSubject } from "rxjs/BehaviorSubject";
import { Observable } from "rxjs/Observable";
import { Subject } from "rxjs/Subject";
import { Employee } from "app/model/employee";
import { UploadTicket } from "app/model/UploadTicket";
import { Question } from "app/model/question";

@Injectable()
export class NavbarService {
    hidePre: boolean;
   
    istester:boolean
hiding: boolean;
visible: boolean;
isuser: boolean;
showquestion : boolean[]=[]; 

constructor() { this.visible = false; this.hiding=true; this.hidePre=true;this.isuser=false; this.istester=false;}

hide() { this.visible = false;this.isuser=false; this.istester=false;}

show() { this.visible = true; this.isuser=false; this.istester=false;}
display(){this.isuser=true;this.visible = false; this.istester=false;}
testerdisplay(){this.isuser=false;this.visible = false; this.istester=true;}
hideclarify() { this.hiding=false; }
showclarify(){ this.hiding=true; }
hideadmin(){ this.hidePre=false; }
showadmin(){this.hidePre=true;}
showlabel(i){ this.showquestion[i]=true;} 
hidelabel(i){this.showquestion[i]=false;
alert("hide"+this.showquestion[i])} 
navigationbar(){
    if(this.empdetails.getValue().isAdmin==='Yes')
    {
    this.show();
    }
    if(this.empdetails.getValue().isAdmin==='No'&& this.empdetails.getValue().role==='Developer')
    {
    this.display();
    } 
    if(this.empdetails.getValue().isAdmin==='No'&& this.empdetails.getValue().role==='Business Analyst')
    {
    this.display();
    } 
    if(this.empdetails.getValue().isAdmin==='No'&& this.empdetails.getValue().role==='Tester')
    {
    this.testerdisplay();
    } 
    
}

public empdetails = new BehaviorSubject<Employee>(null);

set_empdetails(value : Employee){
    this.empdetails.next(value);
}

get_empdetails(){
    return this.empdetails.asObservable();
}


/*public questiondetails = new BehaviorSubject<Question>(null);

set_questiondetails(value : Question){
    this.questiondetails.next(value);
}

get_questiondetails(){
    return this.questiondetails.asObservable();
}
*/

public Dumpdetails = new BehaviorSubject<UploadTicket>(null);

setDumpdetails(value : UploadTicket){
    this.Dumpdetails.next(value);
}

getDumpdetails(){
    return this.Dumpdetails.asObservable();
}
}
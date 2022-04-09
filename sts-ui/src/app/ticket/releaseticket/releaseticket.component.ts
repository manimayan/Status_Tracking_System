import { Component, OnInit } from '@angular/core';
import { InputTextModule, DataTableModule, ButtonModule, DialogModule } from 'primeng/primeng';
import { ReleaseticketService } from './releaseticket.service';
import { Releasetic } from "app/model/releasetic";
import { Message } from 'primeng/primeng';
import { Ticket } from "app/model/ticket";
import { NavbarService } from "app/navbar.service";
import { SelectItem } from "primeng/components/common/selectitem";
import { UploadTicket } from "app/model/UploadTicket";
import { Subscription } from "rxjs/Subscription";

@Component( {
    selector: 'app-releaseticket',
    templateUrl: './releaseticket.component.html',
    styleUrls: ['./releaseticket.component.css'], providers: [ReleaseticketService]
} )
export class ReleaseticketComponent implements OnInit {
    subscription: Subscription;
    Dumpdetails: UploadTicket;
    public createdBy : string;
    public createdOn : Date;
    public cities: SelectItem[];
    public selectedVisibleClaimColumn: any[] = [];
    public selectedVisibleClaimColumn1: any[] = [];
    public claimColumn: any[] = [];
    public cols: any[];
    public visibleClaimColumn: SelectItem[] = [];
    public selectedCities: any[] = [];
    selectedCities1: string[] = [];
    public selectedClaim: any[] = [];
    private empList: Releasetic[] = [];
    private list: Releasetic[] = [];
    private releaseTicket: any[] = [];
    columnOptions: SelectItem[];

    private ReportDetails: Releasetic[] = [];
    constructor( private service: ReleaseticketService, public nav: NavbarService ) { 
        this.subscription=this.nav.getDumpdetails().subscribe(
                value => { 
                         this.Dumpdetails = value; 
                         });
 
    }

    ngOnInit() {
        this.getdumpdetails();
        this.getTicketDetails();
        this.nav.show();
        this.cities = [];
        this.cities.push( { label: 'Ticket Id', value: 'Ticket Id' } );
        this.cities.push( { label: 'Description', value: 'Description' } );
        this.cities.push( { label: 'Application', value: 'Application' } );
        this.cities.push( { label: 'Employee name', value: 'Employee name' } );
        this.cities.push( { label: 'Release', value: 'Release' } );

        this.claimColumn = [
            { field: 'ticketId', header: 'Ticket Id', status: true },
            { field: 'ticketDescription', header: 'Description', status: true },
            { field: 'applicationName', header: 'Application', status: true },
            { field: 'employeeName', header: 'Employee name', status: true },
            { field: 'releaseTicket', header: 'Release', status: true },
        ];
        for ( let i = 0; i < this.claimColumn.length; i++ ) {
            if ( this.claimColumn[i].status == true ) {
                this.selectedVisibleClaimColumn.push( this.claimColumn[i] );
                this.selectedCities.push( this.claimColumn[i].header );
            }
        }
    }
    
    
    getTicketDetails() {
        this.service.getTicketDetails()
            .subscribe(
            res => {
                this.empList = res;
                console.log( this.empList );
            } );
    }


    selecteddisplay() {
        this.selectedVisibleClaimColumn = [];
        for ( let j = 0; j < this.claimColumn.length; j++ ) {
            for ( let i = 0; i <= this.selectedCities.length; i++ ) {
                if ( this.claimColumn[j].header == this.selectedCities[i] ) {
                    this.claimColumn[j].status = true;
                    break;
                } else {
                    this.claimColumn[j].status = false;
                }
            }
        }

        for ( let i = 0; i < this.claimColumn.length; i++ ) {
            if ( this.claimColumn[i].status == true ) {
                this.selectedVisibleClaimColumn.push( this.claimColumn[i] );
            }
        }
    }
    
    getdumpdetails()
    {
        this.createdBy= this.nav.Dumpdetails.getValue().createdBy
        this.createdOn= this.nav.Dumpdetails.getValue().createdOn; 
    }
    
    update( value ) {
        console.log( value );
        console.log( value.ticketId );
        console.log( value.releaseTicket );
        this.service.update( value.ticketId, value.releaseTicket )
            .subscribe(
            response => {
                this.getTicketDetails();
                console.log( this.releaseTicket );
            }
            );
    }
  } 
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NavbarService } from "app/navbar.service";


@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css'],
  providers:[]
})
export class LogoutComponent implements OnInit {

  constructor(private router: Router,public nav: NavbarService) { }

  ngOnInit() {

  }


}

import { AbstractType, Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BackendService } from '../backend.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  userData = "";
  userFirstname = "";
  userLastname = "";
  userRole = "";
  userEmail = "";
  userPassword = "";
  userId: number | undefined;
  allUsers: any;
  allAdmins: any;
  allmovies: any;
  getId: any;
  getmoviesId: any;
  getcId:any;
  getmovies:any;
  showID: number | undefined;
  showFirstName: string | undefined;
  showLastName: string | undefined;
  showRole: string | undefined;
  showEmail: string | undefined;
  showUpdateRole = true;
  ifAdmin=true;
  ifUser=true;
  ifDelete=true;
  searchStatus=false;
  moviestatus=false;
  constructor(private httpClient: HttpClient,
    private backendService: BackendService, private router: Router) { }

  ngOnInit(): void {
    this.userFirstname = this.backendService.getData().firstName;
    this.userId = this.backendService.getData().userId;
    this.userLastname = this.backendService.getData().lastName;
    this.userRole = this.backendService.getData().role;
    this.userEmail = this.backendService.getData().email;
    this.userPassword = this.backendService.getData().password;

    this.httpClient.get<any>(this.backendService.getAllUsers).subscribe(userdata => {
      this.allUsers = userdata;
      console.log(this.allUsers);
    });
    this.httpClient.get<any>(this.backendService.getAllAdmins).subscribe(admindata => {
      this.allAdmins = admindata;
      console.log(this.allAdmins);
    });

    this.httpClient.get<any>(this.backendService.getAllmovies).subscribe(moviesrdata => {
      this.allmovies = moviesrdata;
      console.log(this.allmovies);
    });
  }

  homePage() {
    this.router.navigate([`${'login'}`]);
  }
  displayUser() {
    this.ifAdmin=true;
    this.ifUser=true;
    this.searchStatus=false;
    this.ifDelete=true;
    console.log(this.getId);
    this.backendService.getUserDetailsById(this.getId).subscribe(details => {
      if(!details){
        this.searchStatus=true;
        this.ifAdmin=false;
        this.ifUser=false;
        this.ifDelete=false;
      }
      this.backendService.setData(details);
      console.log(details);
      console.log("getting datails from backend service " + this.backendService.getData().firstName);
      this.showID = this.backendService.getData().userId;
      this.showFirstName = this.backendService.getData().firstName;
      this.showLastName = this.backendService.getData().lastName;
      this.showRole = this.backendService.getData().role;
      this.showEmail = this.backendService.getData().email;
      if(this.showRole=='admin'){
        this.ifAdmin=false;
      }else{
        this.ifUser=false;
      }
    });
  }
  updateRoleToAdmin() {
    console.log(this.backendService.getData());
    this.httpClient.put<any>(this.backendService.upDateUser, {
      userId: this.backendService.getData().userId,
      firstName: this.backendService.getData().firstName,
      lastName: this.backendService.getData().lastName,
      role: "admin",
      email: this.backendService.getData().email,
      password: this.backendService.getData().password
    }).subscribe(data => {
      console.log(data);
      this.homePage();
    });
  }
  updateRoleToUser() {
    console.log(this.backendService.getData());
    this.httpClient.put<any>(this.backendService.upDateUser, {
      userId: this.backendService.getData().userId,
      firstName: this.backendService.getData().firstName,
      lastName: this.backendService.getData().lastName,
      role: "user",
      email: this.backendService.getData().email,
      password: this.backendService.getData().password
    }).subscribe(data => {
      console.log(data);
      this.homePage();
    });
  }

  deleteMember(){
    console.log(this.getId);
    this.backendService.deleteUserById(this.getId).subscribe(details => {
      this.homePage();
    });
  }
  
  deletemovies(){
    this.moviestatus=false;
    console.log(this.getcId);
    this.backendService.getmoviesDetailsById(this.getcId).subscribe(details => {
      if(details ==null){
        this.moviestatus=true;
      }else{
        this.backendService.deletemoviesById(this.getcId).subscribe(details => {
          this.homePage();
      });
      }
    });

    
  }

  addmovies(){
    console.log(this.backendService.getData());
    this.httpClient.post<any>(this.backendService.addmovies, {
      movies: this.getmovies,
      status: "live"
    }).subscribe(data => {
      console.log(data);
      this.homePage();
    });
  }

  updatemoviestatus(){
    this.moviestatus=false;
    this.backendService.getmoviesDetailsById(this.getmoviesId).subscribe(details => {
     if(details ==null){
       this.moviestatus=true;
     }
      this.backendService.setmoviesData(details);
      console.log(details);
      this.httpClient.put<any>(this.backendService.updatemoviesStatus, {
        cId: this.backendService.getmoviesData().cId,
        movies:this.backendService.getmoviesData().movies,
        status: "live"
      }).subscribe(data => {
        console.log(data);
        this.homePage();
      });

    });

  }
}
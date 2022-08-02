import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class BackendService {
// loginURL=" http://35.230.79.86:80/user/login";
loginURL=" http://localhost:8080/user/login";
registerURL="http://localhost:8080/user";
getAllAdmins="http://localhost:8080/user/role/admin";
getAllUsers= "http://localhost:8080/user/role/user";
upDateUser=" http://localhost:8080/user/updateuser";
getAllmovies="http://localhost:8080/movies";
addmovies="http://localhost:8080/movies";
getStatusByLive="http://localhost:8080/movies/status/live";
updatemoviesStatus="http://localhost:8080/movies/updatestatus";
  constructor(private httpClient:HttpClient) { }

  private message:any;
  private moviesData:any;

  setData(data:any){
    this.message = data;
    console.log(this.message);
  }

  getData(){
    console.log(this.message);
    return this.message;
  }

  setmoviesData(data:any){
    this.moviesData = data;
    console.log(this.moviesData);
  }

  getmoviesData(){
    console.log(this.moviesData);
    return this.moviesData;
  }

  getUserDetailsById(id:number){
    return this.httpClient.get("http://localhost:8080/user/"+id);
  }

  deleteUserById(id:number){
    return this.httpClient.delete("http://localhost:8080/user/"+id);
  }

  getmoviesDetailsById(id:number){
    return this.httpClient.get("http://localhost:8080/movies/"+id);
  }

  deletemoviesById(id:number){
    return this.httpClient.delete("http://localhost:8080/movies/"+id);
  }

}

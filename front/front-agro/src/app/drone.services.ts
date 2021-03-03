import { AbstractHttpService } from './http-client.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {DroneResponseDTO, DroneUpdateDTO} from './objects';

@Injectable({
  providedIn: 'root'
})
export class DroneServices extends AbstractHttpService{

  constructor(
    private httpClient: HttpClient
  ) {
    super('/requisicoes/', httpClient);
  }

  protected getBackendUrl(): string {
    return 'http://localhost:8080/api';
  }

  pesguisaDrone(idDrone: number): Observable<any> {
    return this.httpClient.get<DroneResponseDTO>( this.getBackendUrl() + this.uri + `${idDrone}`, this.options());
  }

  salvarDrone(idDrone: number, droneUpdateDTO: DroneUpdateDTO): Observable<any>{
    return this.httpClient.put( this.getBackendUrl() + this.uri + `${idDrone}`, droneUpdateDTO, this.options());
  }

}

import { Component } from '@angular/core';
import { DroneResponseDTO, DroneUpdateDTO } from './objects';
import {HttpClient} from '@angular/common/http';
import { DroneServices } from './drone.services';

// @ts-ignore
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string;
  ativarRastreamento = true;
  droneUpdateDTO = new DroneUpdateDTO();
  droneResponseDTO = new DroneResponseDTO();
  droneId: number;

  constructor(
    private http: HttpClient,
    private droneServices: DroneServices
  ) { }

  // tslint:disable-next-line:typedef
  formatoTemperatura(value: number) {
    return value + 'º';
  }

  // tslint:disable-next-line:typedef
  formatoUmindade(value: number) {
    return value + '%';
  }

  // tslint:disable-next-line:typedef
  clickRastreamento(){
    this.ativarRastreamento = !this.ativarRastreamento;
  }

  // tslint:disable-next-line:typedef
  clickSalvar(){
    this.transferirDadosDto();
    this.droneServices.salvarDrone(this.droneId, this.droneUpdateDTO).subscribe(
      resultData => console.log('this.droneResponseDTO', this.droneResponseDTO)
    );
  }

  // tslint:disable-next-line:typedef
  clickPesquisar() {
    this.droneServices.pesguisaDrone(this.droneId).subscribe(
      resultData => this.droneResponseDTO = resultData
    );
  }

  // tslint:disable-next-line:typedef
  transferirDadosDto(){
    this.droneUpdateDTO.longitude = this.droneResponseDTO.longitude;
    this.droneUpdateDTO.latitude = this.droneResponseDTO.latitude;
    this.droneUpdateDTO.umidade = this.droneResponseDTO.umidade;
    this.droneUpdateDTO.temperatura = this.droneResponseDTO.temperatura;
  }

}

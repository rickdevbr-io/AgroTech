package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DroneUpdateDTO {
    private String latitude;
    private String longitude;
    private int temperatura;
    private int umidade;
}

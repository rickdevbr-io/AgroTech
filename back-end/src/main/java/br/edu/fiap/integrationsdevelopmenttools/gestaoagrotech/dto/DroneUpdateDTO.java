package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DroneUpdateDTO {
    private String latitude;
    private String longitude;
    private int temperatura;
    private int umidade;
}

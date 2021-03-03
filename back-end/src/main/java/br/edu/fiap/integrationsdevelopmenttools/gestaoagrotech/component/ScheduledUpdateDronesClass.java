package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.component;

import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.controller.GestaoAgroTechController;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto.DroneUpdateDTO;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.entity.DroneEntity;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class ScheduledUpdateDronesClass {
    private final int valorMin = 0;
    private final int latitudeMax = 90;
    private final int longitudeMax = 180;
    private final int decimalMax = 99;
    private final String menos = "-";
    private final String minuto = "’";
    private final String segundo = "”";
    private final int temperaturaMin = -25;
    private final int temperaturaMax = 40;
    private final String grau = "°";
    private final int porcentagemMax = 100;

    private final Logger logger = LoggerFactory.getLogger(GestaoAgroTechController.class);

    DroneService droneService;

    ScheduledUpdateDronesClass(DroneService droneService) {
        this.droneService = droneService;
    }

    @Scheduled(fixedDelay = 10000)
    public void scheduledUpdateDrones() {

        List<DroneEntity> listaDrones = droneService.findAll();

        for (DroneEntity item : listaDrones) {
            DroneUpdateDTO droneUpdateDTO = new DroneUpdateDTO();

            droneUpdateDTO.setLatitude(preparaLatitude());
            droneUpdateDTO.setLongitude(preparaLongitude());
            droneUpdateDTO.setTemperatura((int) (Math.random() * (temperaturaMax - temperaturaMin + 1) + temperaturaMin));
            droneUpdateDTO.setUmidade((int) (Math.random() * (porcentagemMax - valorMin + 1) + valorMin));

            droneService.update(item.getId(), droneUpdateDTO);
        }
    }

    public String preparaLatitude() {
        String sinal = "";
        String valorInteiro;
        String valorMinuto;
        String valorSegundo;
        String norteSul = "N";

        if ((1 + (int) (Math.random() * 100)) <= 50)
            sinal = menos;

        if ((1 + (int) (Math.random() * 100)) <= 50)
            norteSul = "S";


        valorInteiro = Integer.toString((int) (Math.random() * (latitudeMax - valorMin + 1) + valorMin));
        valorMinuto = String.format("%02d", (int) (Math.random() * (decimalMax - valorMin + 1) + valorMin));
        valorSegundo = String.format("%02d", (int) (Math.random() * (decimalMax - valorMin + 1) + valorMin));

        return sinal.concat(valorInteiro).concat(grau).concat(valorMinuto).concat(minuto).concat(valorSegundo).concat(segundo).concat(norteSul);
    }

    public String preparaLongitude() {
        String sinal = "";
        String valorInteiro;
        String valorMinuto;
        String valorSegundo;
        String lesteOeste = "E";

        if ((1 + (int) (Math.random() * 100)) <= 50)
            sinal = menos;

        if ((1 + (int) (Math.random() * 100)) <= 50)
            lesteOeste = "W";

        valorInteiro = Integer.toString((int) (Math.random() * (longitudeMax - valorMin + 1) + valorMin));
        valorMinuto = String.format("%02d", (int) (Math.random() * (decimalMax - valorMin + 1) + valorMin));
        valorSegundo = String.format("%02d", (int) (Math.random() * (decimalMax - valorMin + 1) + valorMin));

        return sinal.concat(valorInteiro).concat(grau).concat(valorMinuto).concat(minuto).concat(valorSegundo).concat(segundo).concat(lesteOeste);
    }
}

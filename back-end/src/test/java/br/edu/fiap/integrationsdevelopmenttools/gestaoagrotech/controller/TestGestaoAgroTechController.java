package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.controller;

import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto.DroneJsonDTO;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto.DroneUpdateDTO;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.service.DroneService;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TestGestaoAgroTechController {

    @Mock
    DroneService mockDroneService = Mockito.mock(DroneService.class);

    @Mock
    Logger mockLogger = Mockito.mock(Logger.class);

    @Test
    public void mustGetDadosDrone() {
        GestaoAgroTechController gestaoAgroTechController = new GestaoAgroTechController(mockDroneService);

        DroneJsonDTO droneJsonDTO = DroneJsonDTO.builder()
                .id(1L)
                .latitude("1")
                .longitude("1")
                .temperatura(1)
                .umidade(1)
                .build();

        when(mockDroneService.findById(anyLong())).thenReturn(droneJsonDTO);

        doNothing().when(mockLogger).info(anyString());

        assertEquals(HttpStatus.OK, gestaoAgroTechController.getDadosDrone(1L).getStatusCode());
    }

    @Test
    public void mustGetDadosDrone_Exception() {
        GestaoAgroTechController gestaoAgroTechController = new GestaoAgroTechController(mockDroneService);

        doNothing().when(mockLogger).error(anyString());

        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST))
                .when(mockDroneService)
                .findById(anyLong());

        assertEquals(HttpStatus.BAD_REQUEST, gestaoAgroTechController.getDadosDrone(1L).getStatusCode());
    }

    @Test
    public void mustUpdateDadosDrone() {
        GestaoAgroTechController gestaoAgroTechController = new GestaoAgroTechController(mockDroneService);

        DroneUpdateDTO droneUpdateDTO = DroneUpdateDTO.builder()
                .latitude("1")
                .longitude("1")
                .temperatura(1)
                .umidade(1)
                .build();

        DroneJsonDTO droneJsonDTO = DroneJsonDTO.builder()
                .id(1L)
                .latitude("1")
                .longitude("1")
                .temperatura(1)
                .umidade(1)
                .build();

        when(mockDroneService.update(anyLong(), any(DroneUpdateDTO.class))).thenReturn(droneJsonDTO);

        doNothing().when(mockLogger).info(anyString());

        ResponseEntity<Object> dadosDrone = gestaoAgroTechController.updateDadosDrone(1L, droneUpdateDTO);

        assertEquals(HttpStatus.OK, dadosDrone.getStatusCode());
        assertEquals(droneJsonDTO, dadosDrone.getBody());
    }

    @Test
    public void mustMustUpdateDadosDrone_Exception(){
        GestaoAgroTechController gestaoAgroTechController = new GestaoAgroTechController(mockDroneService);

        DroneUpdateDTO droneUpdateDTO = DroneUpdateDTO.builder()
                .latitude("1")
                .longitude("1")
                .temperatura(1)
                .umidade(1)
                .build();

        doNothing().when(mockLogger).error(anyString());

        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST))
                .when(mockDroneService)
                .update(anyLong(), any(DroneUpdateDTO.class));

        assertEquals(HttpStatus.BAD_REQUEST, gestaoAgroTechController.updateDadosDrone(1L, droneUpdateDTO).getStatusCode());
    }
}

package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.component;

import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto.DroneJsonDTO;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto.DroneUpdateDTO;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.entity.DroneEntity;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.service.DroneService;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TestScheduledUpdateDronesClass {

    @Mock
    DroneService mockDroneService = Mockito.mock(DroneService.class);

    @Test
    public void mustScheduledUpdateDrones() {

        ScheduledUpdateDronesClass scheduledUpdateDronesClass = new ScheduledUpdateDronesClass(mockDroneService);

        DroneEntity droneEntity = DroneEntity.builder()
                .id(1L)
                .latitude("1")
                .longitude("1")
                .temperatura(1)
                .umidade(1)
                .build();

        List<DroneEntity> listaDrones = new ArrayList<>();
        listaDrones.add(droneEntity);

        DroneJsonDTO droneJsonDTO = DroneJsonDTO.builder()
                .id(1L)
                .latitude("1")
                .longitude("1")
                .temperatura(1)
                .umidade(1)
                .build();

        when(mockDroneService.findAll()).thenReturn(listaDrones);

        when(mockDroneService.update(anyLong(), any(DroneUpdateDTO.class))).thenReturn(droneJsonDTO);

        scheduledUpdateDronesClass.scheduledUpdateDrones();

        verify(mockDroneService, atMostOnce()).update(anyLong(), any(DroneUpdateDTO.class));

    }
}

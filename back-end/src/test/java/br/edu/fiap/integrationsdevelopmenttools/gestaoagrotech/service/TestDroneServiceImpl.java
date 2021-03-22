package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.service;

import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto.DroneJsonDTO;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto.DroneUpdateDTO;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.entity.DroneEntity;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.repository.DroneRepository;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestDroneServiceImpl {

    @Mock
    public DroneRepository mockDroneRepository = Mockito.mock(DroneRepository.class);

    @Test
    public void mustFindById(){
        DroneServiceImpl droneService = new DroneServiceImpl(mockDroneRepository);

        DroneEntity droneEntity = DroneEntity.builder()
                .id(1L)
                .latitude("1")
                .longitude("1")
                .temperatura(1)
                .umidade(1)
                .build();

        when(mockDroneRepository.findDrone(anyLong())).thenReturn(java.util.Optional.ofNullable(droneEntity));

        DroneJsonDTO droneJsonDTO = droneService.findById(1L);

        assertEquals(droneEntity.getId(), droneJsonDTO.getId());
        assertEquals(droneEntity.getLatitude(), droneJsonDTO.getLatitude());
        assertEquals(droneEntity.getLongitude(), droneJsonDTO.getLongitude());
        assertEquals(droneEntity.getTemperatura(), droneJsonDTO.getTemperatura());
        assertEquals(droneEntity.getUmidade(), droneJsonDTO.getUmidade());
    }

    @Test
    public void mustUpdate(){
        DroneServiceImpl droneService = new DroneServiceImpl(mockDroneRepository);

        DroneEntity droneEntity = DroneEntity.builder()
                .id(1L)
                .latitude("1")
                .longitude("1")
                .temperatura(1)
                .umidade(1)
                .build();

        DroneEntity droneEntityExec = DroneEntity.builder()
                .id(1L)
                .latitude("3")
                .longitude("3")
                .temperatura(3)
                .umidade(3)
                .build();

        DroneUpdateDTO droneUpdateDTO = DroneUpdateDTO.builder()
                .latitude("3")
                .longitude("3")
                .temperatura(3)
                .umidade(3)
                .build();

        when(mockDroneRepository.findDrone(anyLong())).thenReturn(java.util.Optional.ofNullable(droneEntity));

        when(mockDroneRepository.save(any(DroneEntity.class))).thenReturn(droneEntityExec);

        DroneJsonDTO droneJsonDTO = droneService.update(1L, droneUpdateDTO);

        assertEquals(droneUpdateDTO.getLatitude(), droneJsonDTO.getLatitude());
        assertEquals(droneUpdateDTO.getLongitude(), droneJsonDTO.getLongitude());
        assertEquals(droneUpdateDTO.getTemperatura(), droneJsonDTO.getTemperatura());
        assertEquals(droneUpdateDTO.getUmidade(), droneJsonDTO.getUmidade());
    }

    @Test
    public void mustFindAll(){
        DroneServiceImpl droneService = new DroneServiceImpl(mockDroneRepository);

        List<DroneEntity> droneEntities = new ArrayList<>();

        when(mockDroneRepository.findAll()).thenReturn(droneEntities);

        List<DroneEntity> droneEntitiesReturn = droneService.findAll();

        assertNotNull(droneEntitiesReturn);
    }
}

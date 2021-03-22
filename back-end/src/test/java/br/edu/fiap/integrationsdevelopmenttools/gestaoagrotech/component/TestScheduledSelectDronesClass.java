package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.component;

import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto.DroneJsonDTO;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.service.DroneService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TestScheduledSelectDronesClass {

    @Mock
    KafkaTemplate<String, String> mockKafkaTemplate = Mockito.mock(KafkaTemplate.class);

    private static final String TOPIC = "gestaoagrotech";

    @Mock
    DroneService mockDroneService = Mockito.mock(DroneService.class);

    @Mock
    Logger mockLogger = Mockito.mock(Logger.class);

    @Test
    public void deveScheduledUpdateDrones() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ScheduledSelectDronesClass scheduledSelectDronesClassA = new ScheduledSelectDronesClass(mockDroneService);
        ScheduledSelectDronesClass scheduledSelectDronesClass = new ScheduledSelectDronesClass(mockDroneService, mockKafkaTemplate);
        DroneJsonDTO droneJsonDTO = DroneJsonDTO.builder()
                .id(1L)
                .latitude("1")
                .longitude("1")
                .temperatura(1)
                .umidade(1)
                .build();
        List<DroneJsonDTO> listaDronesJson = new ArrayList<>();
        listaDronesJson.add(droneJsonDTO);

        when(mockDroneService.droneListToJson()).thenReturn(new ArrayList<>());

        when(mockKafkaTemplate.send(TOPIC, mapper.writeValueAsString(listaDronesJson))).thenReturn(null);

        doNothing().when(mockLogger).info(anyString());

        scheduledSelectDronesClass.scheduledUpdateDrones();

        when(mockDroneService.droneListToJson()).thenReturn(listaDronesJson);

        scheduledSelectDronesClass.scheduledUpdateDrones();

        verify(mockKafkaTemplate, atMostOnce()).send(anyString(), anyString());
    }

}

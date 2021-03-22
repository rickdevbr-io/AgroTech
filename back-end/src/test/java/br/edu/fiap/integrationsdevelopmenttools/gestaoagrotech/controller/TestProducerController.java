package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.controller;

import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto.DroneJsonDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestProducerController {

    @Mock
    KafkaTemplate<String, String> mockKafkaTemplate = Mockito.mock(KafkaTemplate.class);

    private static final String TOPIC = "gestaoagrotech";

    @Test
    public void mustPublishMessage() throws JsonProcessingException {
        ProducerController producerController = new ProducerController(mockKafkaTemplate);

        ObjectMapper mapper = new ObjectMapper();

        DroneJsonDTO droneJsonDTO = DroneJsonDTO.builder()
                .id(1L)
                .latitude("1")
                .longitude("1")
                .temperatura(1)
                .umidade(1)
                .build();

        when(mockKafkaTemplate.send(TOPIC, mapper.writeValueAsString(droneJsonDTO))).thenReturn(null);

        assertEquals("Mensagem publicada!", producerController.publishMessage(droneJsonDTO));
    }
}

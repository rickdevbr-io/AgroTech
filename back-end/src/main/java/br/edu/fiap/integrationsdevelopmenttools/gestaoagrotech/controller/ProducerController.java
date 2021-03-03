package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.controller;

import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto.DroneJsonDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class ProducerController {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "gestaoagrotech";

    @PostMapping("/publish")
    public String publishMessage(@RequestBody DroneJsonDTO droneJsonDTO) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        kafkaTemplate.send(TOPIC, mapper.writeValueAsString(droneJsonDTO));
        return "Mensagem publicada!";
    }
}

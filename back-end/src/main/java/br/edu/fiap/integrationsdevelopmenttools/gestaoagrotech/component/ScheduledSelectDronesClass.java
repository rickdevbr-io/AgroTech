package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.component;

import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.controller.GestaoAgroTechController;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto.DroneJsonDTO;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.service.DroneService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class ScheduledSelectDronesClass {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "gestaoagrotech";

    DroneService droneService;

    private final Logger logger = LoggerFactory.getLogger(GestaoAgroTechController.class);

    ScheduledSelectDronesClass(DroneService droneService) {
        this.droneService = droneService;
    }

    @Scheduled(fixedDelay = 60000)
    public void scheduledUpdateDrones() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<DroneJsonDTO> listaDronesJson = droneService.droneListToJson();

        if (listaDronesJson.size() > 0) {
            kafkaTemplate.send(TOPIC, mapper.writeValueAsString(listaDronesJson));

            logger.info("Mensagem publicada! ".concat(mapper.writeValueAsString(listaDronesJson)));
        } else
            logger.info("Nenhum registro foi encontrado, dentro das condições necessárias, para publicação.");
    }
}

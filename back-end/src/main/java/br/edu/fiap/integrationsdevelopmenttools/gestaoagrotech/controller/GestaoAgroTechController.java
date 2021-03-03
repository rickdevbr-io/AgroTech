package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.controller;

import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto.DroneJsonDTO;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto.DroneUpdateDTO;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("requisicoes")
public class GestaoAgroTechController {
    public static final String URI_ID_DRONE = "{id}";
    public static final String DRONE_CONSULTA_REALIZADA = "Consulta realizada com sucesso.";
    public static final String DRONE_ATUALIZADO = "Dados do drone atualizados com sucesso.";
    private final Logger logger = LoggerFactory.getLogger(GestaoAgroTechController.class);

    private final DroneService droneService;

    public GestaoAgroTechController(DroneService droneService) {
        this.droneService = droneService;
    }

    @GetMapping(URI_ID_DRONE)
    public ResponseEntity<Object> getDadosDrone(@PathVariable Long id) {
        ResponseEntity<Object> responseEntity;

        try {
            DroneJsonDTO droneJsonDTO = droneService.findById(id);

            responseEntity = ResponseEntity.ok().body(droneJsonDTO);
            logger.info(DRONE_CONSULTA_REALIZADA);
            return responseEntity;

        } catch (ResponseStatusException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }

    @PutMapping(URI_ID_DRONE)
    public ResponseEntity<Object> updateDadosDrone(@PathVariable Long id, @RequestBody DroneUpdateDTO droneUpdateDTO) {
        ResponseEntity<Object> responseEntity;

        try {
            DroneJsonDTO droneJsonDTO = droneService.update(id, droneUpdateDTO);

            responseEntity = ResponseEntity.ok().body(droneJsonDTO);
            logger.info(DRONE_ATUALIZADO);
            return responseEntity;

        } catch (ResponseStatusException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }
}
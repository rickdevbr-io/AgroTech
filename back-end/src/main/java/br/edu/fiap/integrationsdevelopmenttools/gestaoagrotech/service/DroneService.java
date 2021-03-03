package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.service;

import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto.DroneJsonDTO;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto.DroneUpdateDTO;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.entity.DroneEntity;

import java.util.List;

public interface DroneService {
    DroneJsonDTO findById(Long idDrone);

    DroneJsonDTO update(Long id, DroneUpdateDTO droneUpdateDTO);

    List<DroneEntity> findAll();

    List<DroneJsonDTO> droneListToJson();
}

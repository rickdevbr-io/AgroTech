package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.service;

import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto.DroneJsonDTO;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.dto.DroneUpdateDTO;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.entity.DroneEntity;
import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.repository.DroneRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DroneServiceImpl implements DroneService {

    private DroneRepository droneRepository;

    public DroneServiceImpl(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public DroneJsonDTO findById(Long idDrone) {
        DroneEntity droneEntity = getDrone(idDrone);

        return droneToJsonResponse(droneEntity);
    }

    @Override
    public DroneJsonDTO update(Long id, DroneUpdateDTO droneUpdateDTO) {
        DroneEntity droneEntity = getDrone(id);

        droneEntity.setLatitude(droneUpdateDTO.getLatitude());
        droneEntity.setLongitude(droneUpdateDTO.getLongitude());
        droneEntity.setTemperatura(droneUpdateDTO.getTemperatura());
        droneEntity.setUmidade(droneUpdateDTO.getUmidade());

        DroneEntity droneEntitySalvo = droneRepository.save(droneEntity);

        return droneToJsonResponse(droneEntitySalvo);
    }

    @Override
    public List<DroneEntity> findAll() {
        return droneRepository.findAll();
    }

    public List<DroneJsonDTO> droneListToJson() {
        List<DroneJsonDTO> lista = new ArrayList<>();
        for (DroneEntity item : droneRepository.findAllForEmail()) {
            lista.add(droneToJsonResponse(item));
        }
        return lista;
    }

    private DroneJsonDTO droneToJsonResponse(DroneEntity droneEntity) {
        return DroneJsonDTO.builder()
                .id(droneEntity.getId())
                .latitude(droneEntity.getLatitude())
                .longitude(droneEntity.getLongitude())
                .temperatura(droneEntity.getTemperatura())
                .umidade(droneEntity.getUmidade())
                .build();
    }

    private DroneEntity getDrone(Long id) {
        return droneRepository.findDrone(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

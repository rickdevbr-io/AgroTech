package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "drones")
@Getter
@Setter
public class DroneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private Long id;
    private String latitude;
    private String longitude;
    private int temperatura;
    private int umidade;
}

package br.com.iteris.decola2020.papEventos.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class StatusEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdEventoStatus;

    @Column(nullable = false, length = 250)
    private String NomeStatus;
}
package br.com.iteris.decola2020.papEventos.domain.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdEvento;
    
    @ManyToOne
    @JoinColumn(name = "IdEventoStatus", nullable = false)
    private StatusEvento status;

    @ManyToOne
    @JoinColumn(name = "IdCategoriaEvento", nullable = false)
    private CategoriaEvento categoria;

    @Column(nullable = false, length = 250)
    private String Nome;

    @Column(nullable = false)
    private Date DataHoraInicio;

    @Column(nullable = false)
    private Date DataHoraFim;

    @Column(nullable = false, length = 250)
    private String Local;

    @Column(nullable = false, length = 1000)
    private String Descricao;

    @Column(nullable = false)
    @Size(min = 1)
    private Integer LimiteVagas;
}
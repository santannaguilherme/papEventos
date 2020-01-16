package br.com.iteris.decola2020.papEventos.domain.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

public class Participacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdParticipacao;

    @ManyToOne
    @JoinColumn(name = "IdEvento", nullable = false)
    private Evento evento;

    @Column(nullable = false, length = 250)
    private String LoginParticipante; 
    
    @Column(nullable = false)
    private Boolean FlagPresente;

    @Column(nullable = false)
    @Size(min = 0)
    private Integer Nota;

    @Column(nullable = false, length = 1000)
    private String Comentario;

}
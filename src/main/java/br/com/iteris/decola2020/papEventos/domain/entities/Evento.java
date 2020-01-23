package br.com.iteris.decola2020.papEventos.domain.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEvento")
    private Integer idEvento;
    
    @ManyToOne
    @JoinColumn(name = "IdEventoStatus", nullable = false)
    private StatusEvento status;

    @ManyToOne
    @JoinColumn(name = "IdCategoriaEvento", nullable = false)
    private CategoriaEvento categoria;

    @Column(name = "Nome", nullable = false, length = 250)
    private String nome;

    @Column(name = "DataHoraInicio", nullable = false)
    private Date dataHoraInicio;

    @Column(name = "DataHoraFim",nullable = false)
    private Date dataHoraFim;

    @Column(name = "Local",nullable = false, length = 250)
    private String local;

    @Column(name = "Descricao", nullable = false, length = 1000)
    private String descricao;

    @Column(name = "LimiteVagas",nullable = false)
    private Integer limiteVagas;
}
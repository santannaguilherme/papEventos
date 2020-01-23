package br.com.iteris.decola2020.papEventos.domain.entities;

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
    private Integer Nota;

    @Column(nullable = false, length = 1000)
    private String Comentario;

}
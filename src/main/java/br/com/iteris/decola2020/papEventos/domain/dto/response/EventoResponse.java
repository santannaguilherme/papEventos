package br.com.iteris.decola2020.papEventos.domain.dto.response;

import java.util.Date;

import br.com.iteris.decola2020.papEventos.domain.entities.CategoriaEvento;
import br.com.iteris.decola2020.papEventos.domain.entities.StatusEvento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoResponse {

    private Integer idEvento;

    private String nome;

    private Date dataHoraInicio;

    private Date dataHoraFim;

    private String local;

    private String descricao;

    private Integer limiteVagas;

    private StatusEvento status;

    private CategoriaEvento categoria;
}
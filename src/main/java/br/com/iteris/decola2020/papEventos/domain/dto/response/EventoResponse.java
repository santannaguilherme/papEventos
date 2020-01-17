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

    private Integer IdEvento;

    private String Nome;

    private Date DataHoraInicio;

    private Date DataHoraFim;

    private String Local;

    private String Descricao;

    private Integer LimiteVagas;

    private StatusEvento status;

    private CategoriaEvento categoria;
}
package br.com.iteris.decola2020.papEventos.domain.dto.response;

import br.com.iteris.decola2020.papEventos.domain.entities.Evento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipacaoResponse {
    private Integer idParticipacao;

    private String loginParticipante;

    private Boolean flagPresente;

    private Integer nota;

    private String comentario;

    private Evento evento;
}

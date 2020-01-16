package br.com.iteris.decola2020.papEventos.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipacaoResponse {
    private Integer IdParticipacao;

    private String LoginParticipante;

    private Boolean FlagPresente;

    private Integer Nota;

    private String Comentario;
}

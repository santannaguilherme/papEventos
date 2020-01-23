package br.com.iteris.decola2020.papEventos.domain.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipacaoCreateRequest {

    
    private Integer idEvento;

    @NotEmpty(message = "login is required")
    @Size(max = 250)
    private String loginParticipante;

    private Boolean flagPresente;

   
    private Integer nota;

    @NotEmpty(message = "description is required")
    @Size(max = 1000)
    private String comentario;
}

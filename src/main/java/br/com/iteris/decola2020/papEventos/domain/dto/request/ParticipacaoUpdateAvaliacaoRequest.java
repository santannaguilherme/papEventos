package br.com.iteris.decola2020.papEventos.domain.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipacaoUpdateAvaliacaoRequest {

    @NotNull
    private Integer nota;

    @NotEmpty(message = "description is required")
    @Size(max = 1000)
    private String comentario;
}

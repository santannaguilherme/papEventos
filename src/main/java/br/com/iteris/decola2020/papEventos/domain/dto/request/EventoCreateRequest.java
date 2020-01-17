package br.com.iteris.decola2020.papEventos.domain.dto.request;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.constraints.FutureOrPresent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoCreateRequest {

   
    private Integer IdEventoStatus;

   
    private Integer IdCategoriaEvento;

    @NotEmpty(message = "name is required")
    @Size(max = 250)
    private String Nome;

    @FutureOrPresent
    private Date DataHoraInicio;

    @FutureOrPresent
    private Date DataHoraFim;

    @NotEmpty(message = "local is required")
    @Size(max = 250)
    private String Local;

    @NotEmpty(message = "description is required")
    @Size(max = 1000)
    private String Descricao;

  
    private Integer LimiteVagas;
}
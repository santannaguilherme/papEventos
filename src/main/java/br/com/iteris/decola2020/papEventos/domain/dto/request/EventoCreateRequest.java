package br.com.iteris.decola2020.papEventos.domain.dto.request;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Future;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoCreateRequest {

   
    @NotNull(message = "Categoria is required")
    private Integer IdCategoriaEvento;

    @NotEmpty(message = "name is required")
    @Size(max = 250)
    private String Nome;

    @Future(message = "Can't be a past date")
    @NotNull(message = "Data is required")
    private Date DataHoraInicio;

    @Future(message = "Can't be a past date")
    @NotNull(message = "Data is required")
    private Date DataHoraFim;

    @NotEmpty(message = "local is required")
    @Size(max = 250)
    private String Local;

    @NotEmpty(message = "description is required")
    @Size(max = 1000)
    private String Descricao;

    @NotNull(message = "Vagas is required")
    private Integer LimiteVagas;
}
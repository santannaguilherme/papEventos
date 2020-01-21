package br.com.iteris.decola2020.papEventos.domain.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EventoUpdateRequest
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoUpdateRequest extends EventoCreateRequest {
    
    @NotNull(message = "Status is required")
    private Integer IdEventoStatus;

}
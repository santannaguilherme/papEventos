package br.com.iteris.decola2020.papEventos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção de aplicação. Indica que um objeto não pode ser deletado pois já existe participação.
 * Ao ser lançada na camada controller, causa retorno de erro 400 (Bad request), conforme
 * definido pela anotação @ResponseStatus.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DataCantBeDeletedException extends RuntimeException {


    /**
     *
     */
    private static final long serialVersionUID = 8859227568111927819L;

    public DataCantBeDeletedException(String message) {
        super(message);
    }
}
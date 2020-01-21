package br.com.iteris.decola2020.papEventos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção de aplicação. Indica que a datas são invalidas. Ao ser
 * lançada na camada controller, causa retorno de erro 406 (Not ACCEPTABLE), conforme
 * definido pela anotação @ResponseStatus.
 */
@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class InvalidDateException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidDateException(String message) {
        super(message);
    }

    public InvalidDateException(String message, Throwable e) {
        super(message, e);
    }
}
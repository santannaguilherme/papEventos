package br.com.iteris.decola2020.papEventos.domain.dto.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * ClientCreateRequestTest
 */
@RunWith(MockitoJUnitRunner.class)
public class EventoCreateRequestTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void should_NotBeValid_LocalNull() {
        Date d = new Date();
        d.setTime(d.getTime()+111111);
        EventoCreateRequest createDto = EventoCreateRequest.builder()
        .Descricao("name")
        .Nome("invalido")
        .DataHoraInicio(d)
        .DataHoraFim(d)
        .IdCategoriaEvento(1)
        .LimiteVagas(1)
        .build();

        Set<ConstraintViolation<EventoCreateRequest>> constraintViolations = validator.validate(createDto);

        assertTrue("Modelo deve ser inválido", constraintViolations.size() > 0);
    }

}
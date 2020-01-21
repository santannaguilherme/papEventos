package br.com.iteris.decola2020.papEventos.domain.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * EventoValidatorTest
 */
@RunWith(MockitoJUnitRunner.class)
public class EventoValidatorTest {

    EventoValidator eventoValidator;

    @Before
    public void setUp() {

        eventoValidator = new EventoValidator();

    }

    private final Date inicio = new Date();
    private final Date fim = new Date();

    @Test
    public void should_notBeTheSame() {

        long h1 = 1577847600000L;
        long h2 = 1577847600000L;
        inicio.setTime(h1);
        fim.setTime(h2);

        boolean teste = eventoValidator.ValidaDatasEvento(inicio, fim);

        assertFalse("Não deve ser igual",teste);
        
    }

    @Test
    public void should_notHighier() {

        long h1 = 1578798000000L;
        long h2 = 1577847600000L;
        inicio.setTime(h1);
        fim.setTime(h2);

        boolean teste = eventoValidator.ValidaDatasEvento(inicio, fim);

        assertFalse("Não deve ser maior",teste);
        
    }


    @Test
    public void should_beTheSameDay() {

        long h1 = 1577847600000L;
        long h2 = 1577891470000L;
        inicio.setTime(h1);
        fim.setTime(h2);

        boolean teste = eventoValidator.ValidaDatasEvento(inicio, fim);
        assertTrue("Deve ser no mesmo dia",teste);

        
    }
    
}
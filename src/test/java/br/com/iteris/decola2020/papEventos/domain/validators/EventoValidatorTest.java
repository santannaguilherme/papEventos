package br.com.iteris.decola2020.papEventos.domain.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
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

        boolean teste = EventoValidator.validaDatasEvento(inicio, fim);

        assertFalse("Não deve ser igual",teste);
        
    }

    @Test
    public void should_notHighier() {

        long h1 = 1578798000000L;
        long h2 = 1577847600000L;
        inicio.setTime(h1);
        fim.setTime(h2);

        boolean teste = EventoValidator.validaDatasEvento(inicio, fim);

        assertFalse("Não deve ser maior",teste);
        
    }


    @Test
    public void should_beTheSameDay() {

        long h1 = 1577847600000L;
        long h2 = 1577891470000L;
        inicio.setTime(h1);
        fim.setTime(h2);

        boolean teste = EventoValidator.validaDatasEvento(inicio, fim);
        assertTrue("Deve ser no mesmo dia",teste);

        h1 = 1577847600000L;
        h2 = 1578020400000L;
        inicio.setTime(h1);
        fim.setTime(h2);

        teste = EventoValidator.validaDatasEvento(inicio, fim);
        assertFalse("Deve ser no mesmo dia",teste);

        h1 = 1577847600000L;
        h2 = 1580526000000L;
        inicio.setTime(h1);
        fim.setTime(h2);

        teste = EventoValidator.validaDatasEvento(inicio, fim);
        assertFalse("Deve ser no mesmo mes",teste);

        h1 = 1577847600000L;
        h2 = 1609470000000L;
        inicio.setTime(h1);
        fim.setTime(h2);

        teste = EventoValidator.validaDatasEvento(inicio, fim);
        assertFalse("Deve ser no mesmo ano",teste);

        
    }
    @Test
    public void should_notBeToday() {

        Calendar c = Calendar.getInstance();
        inicio.setTime(c.getTimeInMillis());
        fim.setTime(c.getTimeInMillis()+30000);

        boolean teste = EventoValidator.validaDatasEvento(inicio, fim);
        assertFalse("Não pode ser no dia atual",teste);
    }

    @Test
    public void should_notUpdateToday() {
        Date date = new Date();

        boolean teste = EventoValidator.validaUpdateDate(date);
        assertFalse("Não pode atualizar no dia atual",teste);

        date.setTime(date.getTime()+24*60*60*1000);

        teste = EventoValidator.validaUpdateDate(date);
        assertTrue("Deve poder atualizar em um dia diferente",teste);
    }

    
}
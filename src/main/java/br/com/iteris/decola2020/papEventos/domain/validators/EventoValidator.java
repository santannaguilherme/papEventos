package br.com.iteris.decola2020.papEventos.domain.validators;

import java.util.Calendar;
import java.util.Date;

/**
 * EventoValidator
 */
public class EventoValidator {

    public static boolean ValidaDatasEvento(Date inicio,Date fim){

        long dataInicio = inicio.getTime();
        long dataFim = fim.getTime();

        if(dataFim-dataInicio <= 0){
            return false;
        }
      
        Calendar cal = Calendar.getInstance();
        int hoje = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(inicio);
        int dIni = cal.get(Calendar.DAY_OF_MONTH);
        int mIni = cal.get(Calendar.MONTH);
        int yIni = cal.get(Calendar.YEAR);
        cal.setTime(fim);
        int dFim = cal.get(Calendar.DAY_OF_MONTH);
        int mFim = cal.get(Calendar.MONTH);
        int yFim = cal.get(Calendar.YEAR);

        if(hoje == dIni){
            return false;
        }





        if(dIni == dFim && mIni == mFim && yIni == yFim){
            return true;
        }


        return false;
    }

    
}
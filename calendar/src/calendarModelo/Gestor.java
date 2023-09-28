package calendarModelo;
import calendarBista.*;

import javax.swing.*;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

public class Gestor extends Observable {
    private String[] meses = {"JANUARY","FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST",
            "SEPTEMBER", "OCTOBER", "NOVEMBER", "DICEMBER"};
    private static Gestor g = new Gestor();
    private String m;
    private int ano;
    private Gestor() {

    }

    public static Gestor getGestor(){
        return g;
    }

    public void pasaAlAnterior(String mes,int year) {
        //System.out.println("\nmes");
        ano = year;
        Boolean encontrado = false;
        for(int i=0;i<meses.length && !encontrado ;i++ ){
            if(mes.equals(meses[i])){
                i--;
                if(i<0){
                    ano--;
                    i=11;
                    m = meses[11];
                    encontrado=true;

                }
                m = meses[i];
                encontrado=true;

            }

        }
        setChanged();
        notifyObservers();
        CalendariVista.getCalendario().getMesTabla();
        //funciona


    }
    
    public void pasaAlSiguiente(String mes, int year) {
       // System.out.println(">" + m);
        //System.out.println("\nmes");
        ano = year;
        Boolean encontrado = false;
        for(int i=0;i< meses.length && !encontrado;i++ ){
            if(mes.equals(meses[i])){
                i++;
                if(i==12){
                    i=0;
                    ano++;
                    m = meses[i];
                    encontrado = true;

                }


                m = meses[i];
                encontrado=true;

            }
        }

        setChanged();;
        notifyObservers();
        CalendariVista.getCalendario().getMesTabla();

        }

        //funciona

    public void calcularDiaSiguiente(String fechaStr) throws java.text.ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = dateFormat.parse(fechaStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.MONTH, 1);
        String m = dateFormat.format(calendar.getTime());

        //return diaSiguienteStr;
    }

    public int numeroDeMes(String mes) {
    	Locale locale = new Locale("en", "US"); // "en" para inglés, "US" para Estados Unidos
        DateFormatSymbols symbols = new DateFormatSymbols(locale);
        String[] nombresMeses = symbols.getMonths();
        
    	int numeroMes = -1;
    	for (int i = 0; i < nombresMeses.length; i++) {
    	    if (mes.toLowerCase().equalsIgnoreCase(nombresMeses[i])) {
    	        numeroMes = i;
    	        break;
    	    }
    	}
    	return numeroMes;

    	
    }



    /*
    public void abreEntrenamiento(String fecha){

        Entrenamiento e = new Entrenamiento(fecha);

    }
    */

    public String getMes(){
        return m;
    }

    public int getAno(){
        return ano;
    }








}

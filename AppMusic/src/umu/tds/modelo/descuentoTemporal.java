package umu.tds.modelo;

import java.util.Calendar;
import java.util.Date;

import umu.tds.controlador.Controlador;

public class descuentoTemporal implements Descuento{
	
	private static double DESCUENTO = 0.2;
	private final Date fechaLiminte;

	public descuentoTemporal() {
		
		Calendar date = Calendar.getInstance();
		System.out.println("Current Date and TIme : " + date.getTime());
		long timeInSecs = date.getTimeInMillis();
		fechaLiminte = new Date(timeInSecs + (Controlador.MINUTOS * 60 * 1000));
	}
	
	@Override
	public double aplicarDescuento(int tiempo) {

		double total = tiempo * Controlador.PRECIO;		//Precio total
		if(fechaLiminte.after(Calendar.getInstance().getTime())) return total*DESCUENTO;	//Aplicamos el descuento si no ha expirado el tiempo
		else return total;
	}

	@Override
	public double getDescuento() {
		if(fechaLiminte.after(Calendar.getInstance().getTime()))  return  DESCUENTO;	//Si todavia es aplicable el descuento lo devolvemos
		else return 0;
	}
	
}

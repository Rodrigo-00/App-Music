package umu.tds.modelo;

import java.util.Calendar;
import java.util.Date;

import umu.tds.controlador.Controlador;

public class DescuentoTemporal implements Descuento{
	
	private static double DESCUENTO = 0.2;
	private final Date fechaLiminte;

	public DescuentoTemporal() {
		
		Calendar date = Calendar.getInstance();
		System.out.println("Current Date and TIme : " + date.getTime());
		long timeInSecs = date.getTimeInMillis();
		fechaLiminte = new Date(timeInSecs + (Controlador.MINUTOS * 60 * 1000));
	}
	
	@Override
	public double aplicarDescuento() {

		if(fechaLiminte.after(Calendar.getInstance().getTime())) return Controlador.PRECIO-Controlador.PRECIO*DESCUENTO;	//Aplicamos el descuento si no ha expirado el tiempo
		else return Controlador.PRECIO;
	}

	@Override
	public double getDescuento() {
		if(fechaLiminte.after(Calendar.getInstance().getTime()))  return  DESCUENTO;	//Si todavia es aplicable el descuento lo devolvemos
		else return 0;
	}
	
}

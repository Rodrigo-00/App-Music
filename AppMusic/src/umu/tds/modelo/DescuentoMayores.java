package umu.tds.modelo;

import umu.tds.controlador.Controlador;

public class DescuentoMayores implements Descuento{

	private static double DESCUENTO = 0.5;
	
	@Override
	public double aplicarDescuento(int tiempo) {
		double total = Controlador.PRECIO *tiempo;
		return DESCUENTO*total;
	}

	@Override
	public double getDescuento() {
		return DESCUENTO;
	}
	
	
	
}

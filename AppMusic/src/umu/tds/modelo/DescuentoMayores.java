package umu.tds.modelo;

import umu.tds.controlador.Controlador;

public class DescuentoMayores implements Descuento{

	private static double DESCUENTO = 0.5;
	
	@Override
	public double aplicarDescuento() {
		return DESCUENTO*Controlador.PRECIO;
	}

	@Override
	public double getDescuento() {
		return DESCUENTO;
	}
	
	
	
}

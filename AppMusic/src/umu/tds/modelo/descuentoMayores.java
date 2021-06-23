package umu.tds.modelo;

public class descuentoMayores implements Descuento{

	private static double DESCUENTO = 0.5;
	
	@Override
	public double aplicarDescuento(double cantidad) {
		return DESCUENTO*cantidad;
	}

	@Override
	public double getDescuento() {
		return DESCUENTO;
	}
	
	
	
}

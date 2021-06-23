package umu.tds.modelo;

public class descuentoTemporal implements Descuento{
	
	private static double DESCUENTO = 0.2;

	@Override
	public double aplicarDescuento(double cantidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDescuento() {
		return DESCUENTO;
	}
	
}

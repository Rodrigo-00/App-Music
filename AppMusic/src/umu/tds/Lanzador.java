package umu.tds;

import java.awt.EventQueue;

import umu.tds.controlador.Controlador;
import umu.tds.gui.VentanaLogin;
import umu.tds.modelo.Descuento;
import umu.tds.modelo.descuentoMayores;

public class Lanzador {
	public static void main(final String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Descuento descuento = new descuentoMayores();
					Controlador.getUnicaInstancia().setDescuento(descuento);
					VentanaLogin ventana = new VentanaLogin();
					ventana.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
package umu.tds;

import java.awt.EventQueue;
import umu.tds.gui.VentanaLogin;

public class Lanzador {
	public static void main(final String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin ventana = new VentanaLogin();
					ventana.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
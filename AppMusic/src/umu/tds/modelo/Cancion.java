package umu.tds.modelo;

import java.time.LocalDate;

public class Cancion {

	private static final int REPRODUCCIONESINICIALES = 0;
	
	private String titulo;
	private String rutaFichero;
	private int numReproducciones;
	
	
	public Cancion(String titulo, String rutaFichero) {
		this.titulo = titulo;
		this.rutaFichero = rutaFichero;
		numReproducciones = REPRODUCCIONESINICIALES;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getRutaFichero() {
		return rutaFichero;
	}


	public void setRutaFichero(String rutaFichero) {
		this.rutaFichero = rutaFichero;
	}
	
}

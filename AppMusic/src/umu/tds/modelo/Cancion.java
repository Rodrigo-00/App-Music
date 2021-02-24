package umu.tds.modelo;

import java.time.LocalDate;

public class Cancion {

	private static final int REPRODUCCIONESINICIALES = 0;
	
	private String titulo;
	private String interprete;
	private String estilo;
	private String rutaFichero;
	private int numReproducciones;
	

	public Cancion(String titulo, String interprete, String estilo, String rutaFichero) {
		this.titulo = titulo;
		this.interprete = interprete;
		this.estilo = estilo;
		this.rutaFichero = rutaFichero;
		numReproducciones = REPRODUCCIONESINICIALES;
	}
	
	
	public String getInterprete() {
		return interprete;
	}


	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}


	public String getEstilo() {
		return estilo;
	}


	public void setEstilo(String estilo) {
		this.estilo = estilo;
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

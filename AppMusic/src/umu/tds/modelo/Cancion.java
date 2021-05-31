package umu.tds.modelo;

import java.time.LocalDate;

public class Cancion {


	private static final int REPRODUCCIONESINICIALES = 0;
	
	private String titulo;
	private String interprete;
	private String estilo;
	private String rutaFichero;
	private int reproducciones;
	private int id;
	

	public Cancion(String titulo, String interprete, String estilo, String rutaFichero) {
		this.titulo = titulo;
		this.interprete = interprete;
		this.estilo = estilo;
		this.rutaFichero = rutaFichero;
		reproducciones = REPRODUCCIONESINICIALES;
	}
	
	
	public boolean isInterprete(String interpre) {
		return interprete.equals(interpre);
	}
	
	public boolean isEstilo(String esti) {
		return estilo.equals(esti);
	}
	
	public int getReproducciones() {
		return reproducciones;
	}
	
	public void setReproducciones(int repro) {
		this.reproducciones = repro;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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

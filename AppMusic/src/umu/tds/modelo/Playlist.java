package umu.tds.modelo;

import java.util.LinkedList;
import java.util.List;



public class Playlist {
	
	private static final int IDINICIAL = 0;
	
	private String nombre;
	private int id;
	private List<Cancion> canciones;
	
	public Playlist(String nombre) {
		id = IDINICIAL;
		nombre = this.nombre;
		canciones = new LinkedList<Cancion>();
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void addCancion(Cancion cancion) {
		canciones.add(cancion);
	}
	
	public void removeCancion(Cancion cancion) {
		canciones.remove(cancion);
	}

	public List<Cancion> getCanciones() {
		return canciones;
	}
}

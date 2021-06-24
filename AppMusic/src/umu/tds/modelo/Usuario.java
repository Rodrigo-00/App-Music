package umu.tds.modelo;

import java.util.LinkedList;
import java.util.List;

public class Usuario {
	
	
	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String login;
	private String password;
	private String fechaNacimiento;
	private Boolean premium;
	private List<Cancion> recientes;
	private List<Playlist> playLists;

	public Usuario(String nombre, String apellidos, String email, String login, String password, String fechaNacimiento) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.login = login;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		premium=false;
		recientes = new LinkedList<Cancion>();
		playLists = new LinkedList<Playlist>();
	}
	
	
	public void addReciente(Cancion c) {
		
		if(!recientes.contains(c) && recientes.size() == 10) recientes.remove(9);	//Borramos la ultima cancion 
		else if(recientes.contains(c))	recientes.remove(c);	//Borramos la cancion de la posicion donde este
	
		recientes.add(0, c);	//A�adimos al inicio la cancion
	}
	
	public Playlist crearPlayList(String nombre) {
		
		System.out.println("Nombre de la playlist a crear " + nombre);
		
		for(Playlist list : playLists) {
			System.out.println(list.getNombre());
			if(list.getNombre().equals(nombre)) {
				System.out.println("Existe ya una playlist con el nombre");
				return null;
			}
		}
		
		System.out.println("El nombre sigue siendo "+ nombre);
		Playlist lista = new Playlist(nombre);	//El usuario es el experto en crear una playlist
		playLists.add(lista);
		
		System.out.println("El nombre sigue siendo "+ lista.getNombre());
		return lista;
	}
	
	public void addPlayList(Playlist lista) {
		playLists.add(lista);
	}
	
	
	public List<String> nombresListas(){
		
		List<String> nombres = new LinkedList<String>();
		for(Playlist list : playLists) nombres.add(list.getNombre());
		return nombres;
		
	}
	
	
	public Boolean getPremium() {
		return premium;
	}

	public void setPremium(Boolean premium) {
		this.premium = premium;
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public List<Cancion> getRecientes() {
		return recientes;
	}
	
	public List<Playlist> getPlayLists() {
		return playLists;
	}
	
	public void addCancionToPlaylist(String play, Cancion cancion) {
		Playlist playlist = this.getPlayList(play);
		playlist.addCancion(cancion);
	}
	
	public Playlist getPlayList(String play) {
		for (Playlist playlist:playLists) {
			if(play.equals(playlist.getNombre()))
			return playlist;
		}
		return null;
	}
	
	public boolean isPremium() {
		return premium;
	}
	
	public List<Cancion> getCancionesPlaylist(String playlist){
		for (Playlist play: playLists) {
			if(play.getNombre().equals(playlist)){
				return play.getCanciones();
			}
		}
		return null;
	}


}

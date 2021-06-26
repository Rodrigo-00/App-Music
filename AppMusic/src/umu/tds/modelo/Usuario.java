package umu.tds.modelo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Usuario {
	
	
	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String login;
	private String password;
	private Date fechaNacimiento;
	private Boolean premium;
	private List<Cancion> recientes;
	private List<Playlist> playLists;
	private Descuento descuento;

	public Usuario(String nombre, String apellidos, String email, String login, String password, Date fechaNacimiento) {
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
	
	public Playlist addCancionToPlaylist(String play, Cancion cancion) {
		Playlist playlist = this.getPlayList(play);
		playlist.addCancion(cancion);
		return playlist;
	}
	public Playlist removeCancionPlaylist(String play, Cancion cancion) {
		Playlist playlist = this.getPlayList(play);
		playlist.removeCancion(cancion);
		return playlist;
	}
	
	public Playlist getPlayList(String play) {
		for (Playlist playlist:playLists) {
			if(play.equals(playlist.getNombre()))
			return playlist;
		}
		return null;
	}
	public Playlist eliminaPlaylist(String playl) {
		Playlist playlist = this.getPlayList(playl);
		playLists.remove(playlist);
		return playlist;
	}
	
	public boolean isPremium() {
		return premium;
	}
	
	public List<Cancion> getCancionesPlaylist(String playlist){
		for (Playlist play: playLists) {
			if(play.getNombre().equals(playlist)){
				System.out.println("Esta la playlist");
				return play.getCanciones();
			}
		}
		return null;
	}
	
	public void realizarPago() {
		//if(descuento != null)
		
	}
	
	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}
	
	public Descuento getDescuento() {
		return descuento;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public List<Cancion> getRecientes() {
		return recientes;
	}
	
	public List<Playlist> getPlayLists() {
		return playLists;
	}


}

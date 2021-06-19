package umu.tds.controlador;
import umu.tds.cargadorCanciones.Canciones;
import umu.tds.cargadorCanciones.CargadorCanciones;

import umu.tds.dominio.*;
import umu.tds.modelo.*;
import umu.tds.persistencia.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.scene.media.Media; 
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status; 

public final class Controlador implements PropertyChangeListener{

	private Usuario usuarioActual;
	private static Controlador unicaInstancia;
	
	private IAdaptadorCancionDAO adaptadorCancion;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorPlaylistDAO adaptadorPlaylist;

	private CatalogoCanciones catalogoCanciones;
	private CatalogoUsuarios catalogoUsuarios;
	private MediaPlayer mediaPlayer;
	
	private Controlador() {
		usuarioActual = null;
		inicializarAdaptadores();
		inicializarCatalogos();
		activarReproductor();
	}

	public static Controlador getUnicaInstancia() {
		if (unicaInstancia == null) {
			System.out.println("Crea nueva instancia");
			unicaInstancia = new Controlador();
		}
		return unicaInstancia;
	}
	
	public void propertyChange(PropertyChangeEvent evento) {
	}
	
	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		adaptadorCancion = factoria.getCancionDAO();
		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorPlaylist = factoria.getPlaylistDAO();
	}
	
	private void inicializarCatalogos() {
		catalogoCanciones = CatalogoCanciones.getUnicaInstancia();
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
	}
	
	private void activarReproductor() {
		try {
			com.sun.javafx.application.PlatformImpl.startup(() -> {
			});
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception: " + ex.getMessage());
		}
	}
	

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public boolean esUsuarioRegistrado(String login) {
		return catalogoUsuarios.getUsuario(login) != null;
	}

	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = catalogoUsuarios.getUsuario(nombre);
		if (usuario != null && usuario.getPassword().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}
	
	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password, String fechaNacimiento) {

        if (esUsuarioRegistrado(login)) return false;
        	
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacimiento);

		adaptadorUsuario.registrarUsuario(usuario);

		catalogoUsuarios.addUsuario(usuario); 
		return true;
	}

	public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getLogin()))
			return false;

		adaptadorUsuario.delete(usuario);

		catalogoUsuarios.removeUsuario(usuario);
		return true;
	}
	
	public List<String> nombresListas(){
		
		List<String> nombres = new LinkedList<String>();
		List<Playlist> listas = adaptadorPlaylist.getAll(usuarioActual.getId());
		
		for(Playlist list : listas) nombres.add(list.getNombre());
		
		return nombres;
		
	}
	
	public boolean crearPlaylist(String nombre) {
		
		
		System.out.println("Nombre de la playlist a crear " + nombre);
		
		List<Playlist> listas = adaptadorPlaylist.getAll(usuarioActual.getId());	//Obtenemos todas las playlist del cliente
		
		for(Playlist list : listas) {
			System.out.println(list.getNombre());
			if(list.getNombre().equals(nombre)) {
				System.out.println("Existe ya una con el nombre");
				return false;
			}
		}
		
		System.out.println("El nombre sigue siendo "+ nombre);
		Playlist lista = new Playlist(nombre);
		System.out.println("El nombre sigue siendo "+ lista.getNombre());
		adaptadorPlaylist.registrarPlaylist(lista, usuarioActual.getId());
		return true;
	}
	
	public void cargarCanciones(String fichero) {
		CargadorCanciones cargador = CargadorCanciones.getUnicaInstancia();
		cargador.addCancionesListener(this);
		Canciones canciones = cargador.setArchivoCanciones(fichero);
		for(umu.tds.cargadorCanciones.Cancion cancion: canciones.getCancion()) {
			Cancion song = new Cancion(cancion.getTitulo(), cancion.getInterprete(), cancion.getEstilo(), cancion.getURL());
			adaptadorCancion.registrarCancion(song);
			catalogoCanciones.addCancion(song);
		}
	}
	
	public List<Cancion> getAllCanciones(){
		return catalogoCanciones.getAll();
	}
	
	public List<Cancion> getCancionesInterprete(String interprete){
		return  catalogoCanciones.getCancionesInterprete(interprete);
	}
	
	public Cancion getCancionTitulo(String titulo){
		return catalogoCanciones.getCancionTitulo(titulo);
	}
	
	public Cancion getCancionTituloeInter(String titulo, String interprete){
		return catalogoCanciones.getCancion(titulo, interprete);
	}
	
	public List<Cancion> getCancionesEstilo(String estilo){
		return  catalogoCanciones.getCancionesEstilo(estilo);
	}
	
	public List<Cancion> getCancionesEstiloInter(String estilo, String interprete){
		return  catalogoCanciones.getCancionesEstiloInter(estilo, interprete);
	}
	
	public Cancion getCancionTituloyEsti(String titulo, String estilo) {
		return  catalogoCanciones.getCancionTituloyEsti(titulo, estilo);
	}
	
	public Cancion getCancionTitInterEsti(String titulo, String interprete, String estilo) {
		return  catalogoCanciones.getCancionTitInterEsti(titulo, interprete, estilo);
	}
	
	public List<String> getEstilos() {
		return  catalogoCanciones.getEstilos();
	}
	
	public void reproducirCancion(Cancion c) throws MalformedURLException {
		URL url = new URL(c.getRutaFichero());
		Media hit = new Media(url.toString()); 
		mediaPlayer = new MediaPlayer(hit); 
		mediaPlayer.play();
		usuarioActual.addReciente(c);	//Añadimos la cancion a la lista de canciones recientes del usuario
	}
	
	public void pausarCancion() {
		mediaPlayer.stop();
	}
	
	
	
}

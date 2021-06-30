package umu.tds.controlador;
import umu.tds.cargadorCanciones.Canciones;
import umu.tds.cargadorCanciones.CargadorCanciones;

import umu.tds.dominio.*;
import umu.tds.modelo.*;
import umu.tds.persistencia.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.scene.media.Media; 
import javafx.scene.media.MediaPlayer;

public final class Controlador implements PropertyChangeListener{

	public static final int PRECIO = 20; //precio anual de la aplicación
	public static final int MINUTOS = 1; //tiempo que esta disponible el descuento temporal
	
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
			if(!usuarioActual.isPremium()) {
				usuarioActual.otorgarDescuento();	//Añadimos el descuento al usuario si este no es ya usuario premium
			}else {
				//Actualizamos la playlist de canciones más reproducidas
				List<Cancion> masRepro = catalogoCanciones.getMasReproducidas();
				usuarioActual.crearMasRepro(masRepro);
			}
			return true;
		}
		return false;
	}
	
	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password, Date fechaNacimiento) {

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
		return usuarioActual.nombresListas();
	}
	
	
	
	public boolean crearPlaylist(String nombre) {
		Playlist lista = usuarioActual.crearPlayList(nombre);
		if(lista != null) {	//Si la playlist devuelta no es null, el usuario no tiene una playlist con el mismo nombre
			adaptadorPlaylist.registrarPlaylist(lista);
			adaptadorUsuario.updatePerfil(usuarioActual);
			return true;
		}
		return false;
		
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
	public Cancion getCancionPorId(int id) {
		return  catalogoCanciones.getCancionId(id);
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
		adaptadorUsuario.updatePerfil(usuarioActual);	//Actualizamos en la base de datos el usuario actual
		catalogoCanciones.reproducida(c);	//Delegamos en el catalogo de canciones para actualizar la cancion puesto que hay varios objetos para una misma cancion
		
	}
	
	public void pausarCancion() {
		mediaPlayer.pause();
	}
	
	public void reanudarCancion() {
		mediaPlayer.play();
	}
	
	public void pararCancion() {
		mediaPlayer.stop();
	}
	
	public List<Cancion> getRecientes(){
		return usuarioActual.getRecientes();
	}
	
	public String getLoginUsuario(){
		return usuarioActual.getLogin();
	}
	
	public Boolean isUsuarioPremium(){
		return usuarioActual.isPremium();
	}
	
	//El usuario se convierte en premiumm durante un tiempo
	public double convertirPremium(int tiempo) {
		double pago = usuarioActual.realizarPago(tiempo);
		List<Cancion> masRepro = catalogoCanciones.getMasReproducidas();
		usuarioActual.crearMasRepro(masRepro);
		adaptadorUsuario.updatePerfil(usuarioActual);	//actualizamos al usuario en base de datos
		return pago;
	}
	
	//Consultar precio a pagar para convertirse en premium
	public double consultarDescuento() {
		return usuarioActual.consultarDescuento();
	}
	
	
	public void anadeCancionPlaylist(String playlist, Cancion cancion) {
		Playlist lista = usuarioActual.addCancionToPlaylist(playlist, cancion);
		adaptadorPlaylist.updatePlaylist(lista);
	}
	public void eliminaCancionPlaylist(String playlist, int id) {
		Playlist lista = usuarioActual.removeCancionPlaylist(playlist, id);
		adaptadorPlaylist.updatePlaylist(lista);
	}
	public void eliminaPlaylist(String playlist) {
		Playlist lista = usuarioActual.eliminaPlaylist(playlist);
		adaptadorPlaylist.delete(lista);
		adaptadorUsuario.updatePerfil(usuarioActual);
		
	}
	
	public List<Cancion> getCancionesPlaylist(String playlist){
		return usuarioActual.getCancionesPlaylist(playlist);
	}
	
	public void generaPDF(String path) throws FileNotFoundException, DocumentException {
		usuarioActual.creaPDF(path);
	}
	
}

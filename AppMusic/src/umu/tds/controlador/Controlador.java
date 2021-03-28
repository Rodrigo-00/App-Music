package umu.tds.controlador;
import umu.tds.dominio.*;
import umu.tds.modelo.Playlist;
import umu.tds.modelo.Usuario;
import umu.tds.persistencia.*;

public final class Controlador {

	private Usuario usuarioActual;
	private static Controlador unicaInstancia;
	
	private IAdaptadorCancionDAO adaptadorCancion;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorPlaylistDAO adaptadorPlaylist;

	private CatalogoCanciones catalogoCanciones;
	private CatalogoUsuarios catalogoUsuarios;
	
	private Controlador() {
		usuarioActual = null;
		inicializarAdaptadores();
		inicializarCatalogos();
	}

	public static Controlador getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new Controlador();
		return unicaInstancia;
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

	public void crearPlaylist(String nombre) {
		Playlist lista = new Playlist(nombre);
		//boolean registrar = adaptadorPlaylist.registrarPlaylist(lista);
	}
}

package umu.tds.persistencia;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.Usuario;
import beans.Entidad;
import beans.Propiedad;

/**
 * 
 * Clase que implementa el Adaptador DAO concreto de Usuario para el tipo H2.
 * 
 */
public final class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {

	private static final String USUARIO = "Usuario";
	
	private static final String NOMBRE = "nombre";
	private static final String APELLIDOS = "apellidos";
	private static final String EMAIL = "email";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	private static final String RECIENTES = "recientes";
	
	private static ServicioPersistencia servPersistencia;
	
	private static AdaptadorUsuarioTDS unicaInstancia;

	public static AdaptadorUsuarioTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorUsuarioTDS();
		else
			return unicaInstancia;
	}

	private AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	private Usuario entidadToUsuario(Entidad eUsuario) {
		
		System.out.println("ENTRAMOS EN ENTIDADTOUSUARIO");
		List<Cancion> recientes = new LinkedList<Cancion>();
		
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, APELLIDOS);
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		String login = servPersistencia.recuperarPropiedadEntidad(eUsuario, LOGIN);
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		String fechaNacimiento = servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
		recientes = obtenerCancionesDesdeId(servPersistencia.recuperarPropiedadEntidad(eUsuario, RECIENTES));
		System.out.println("HEMOS LLAMADO A OBTENERCANCIONESDESDEID");
		
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacimiento);
		usuario.setId(eUsuario.getId());
		
		for (Cancion c : recientes) {
			System.out.println("Añadimos en el adaptador "+ c.getTitulo());
			usuario.addReciente(c);
		}
		return usuario;
	}

	private Entidad usuarioToEntidad(Usuario usuario) {
		Entidad eUsuario = new Entidad();
		eUsuario.setNombre(USUARIO);

		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad(NOMBRE, usuario.getNombre()),
				new Propiedad(APELLIDOS, usuario.getApellidos()), 
				new Propiedad(EMAIL, usuario.getEmail()),
				new Propiedad(LOGIN, usuario.getLogin()), 
				new Propiedad(PASSWORD, usuario.getPassword()),
				new Propiedad(FECHA_NACIMIENTO, usuario.getFechaNacimiento()),
				new Propiedad(RECIENTES, obtenerIdCanciones(usuario.getRecientes())))));
		return eUsuario;
	}
	
	
	private List<Cancion> obtenerCancionesDesdeId(String canciones) {

		System.out.println("ENTRA EN OBTENERCANCIONES");
		List<Cancion> listaCanciones = new LinkedList<Cancion>();
		
		System.out.println(canciones);
		
		if(canciones!= null && !canciones.equals("")) {
			System.out.println("ENTRA");
			StringTokenizer strTok = new StringTokenizer(canciones, " ");
			AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();
			while (strTok.hasMoreTokens()) {
				listaCanciones.add(adaptadorC.obtenerCancion(Integer.valueOf((String) strTok.nextElement())));
			}
		}else System.out.println("No hay canciones en el adaptador");
		
		if(listaCanciones.size()==0) System.out.println("LISTA de canciones VACIA");
		
		for (Cancion c : listaCanciones)	System.out.println("Añadimos a la lista a devolver "+ c.getTitulo());
	
		
		return listaCanciones;
	}
	
	private String obtenerIdCanciones(List<Cancion> listaCanciones) {
		String aux = "";
		for (Cancion c : listaCanciones) {
			System.out.println("Obtenemos id de "+ c.getTitulo());
			aux += c.getId() + " ";
			System.out.println("La lista queda "+ aux);
		}
		
		return aux.trim();
	}

	public void registrarUsuario(Usuario usuario) {
		Entidad eUsuario;
		System.out.println("Usuario id " + usuario.getId());
		if(usuario.getId() != 0) {
			boolean existe = true;
			try {
				eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
			}catch (NullPointerException e) {
				existe = false;
			}
			if(existe) return;
		}

		eUsuario = this.usuarioToEntidad(usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
	}

	public boolean delete(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		
		return servPersistencia.borrarEntidad(eUsuario);
	}

	/**
	 * Permite que un Usuario modifique su perfil: password y email
	 */
	public void updatePerfil(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		
		for (Propiedad prop : eUsuario.getPropiedades()) {
			switch (prop.getNombre()) {
			case PASSWORD:
				prop.setValor(String.valueOf(usuario.getPassword()));
				break;
			case EMAIL:
				prop.setValor(String.valueOf(usuario.getEmail()));
				break;
			case NOMBRE:
				prop.setValor(String.valueOf(usuario.getNombre()));
				break;
			case APELLIDOS:
				prop.setValor(String.valueOf(usuario.getApellidos()));
				break;
			case RECIENTES:
				prop.setValor(String.valueOf(obtenerIdCanciones(usuario.getRecientes())));
				break;
			default:
				break;
			}
			servPersistencia.modificarPropiedad(prop);
		}
		
		/*
		servPersistencia.eliminarPropiedadEntidad(eUsuario, PASSWORD);
		servPersistencia.anadirPropiedadEntidad(eUsuario, PASSWORD, usuario.getPassword());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, EMAIL);
		servPersistencia.anadirPropiedadEntidad(eUsuario, EMAIL, usuario.getEmail());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, NOMBRE);
		servPersistencia.anadirPropiedadEntidad(eUsuario, NOMBRE, usuario.getNombre());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, APELLIDOS);
		servPersistencia.anadirPropiedadEntidad(eUsuario, APELLIDOS, usuario.getApellidos());
		
		String canciones = obtenerIdCanciones(usuario.getRecientes());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, RECIENTES);
		servPersistencia.anadirPropiedadEntidad(eUsuario, RECIENTES, canciones);
		System.out.println("Guardamos en el adaptador " + canciones + usuario.getRecientes());
		System.out.println("Y la lista contiene");
		for (Cancion c : usuario.getRecientes()) {
			System.out.println(c.getTitulo());
		}*/
	}

	public Usuario obtenerUsuario(int id) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);
		
		return entidadToUsuario(eUsuario);
	}
	
	public List<Usuario> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);

		List<Usuario> usuarios = new LinkedList<Usuario>();
		for (Entidad eUsuario : entidades) {
			usuarios.add(obtenerUsuario(eUsuario.getId()));
		}
		
		return usuarios;
	}

}

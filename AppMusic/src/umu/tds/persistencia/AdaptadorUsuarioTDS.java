package umu.tds.persistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.Playlist;
import umu.tds.modelo.Usuario;
import beans.Entidad;
import beans.Propiedad;

public final class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {

	private static final String USUARIO = "Usuario";
	
	private static final String NOMBRE = "nombre";
	private static final String APELLIDOS = "apellidos";
	private static final String EMAIL = "email";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	private static final String PREMIUM = "premium";
	private static final String RECIENTES = "recientes";
	private static final String LISTAS = "playLists";
	
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
		List<Playlist> listaPlaylist = new LinkedList<Playlist>();
		
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, APELLIDOS);
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		String login = servPersistencia.recuperarPropiedadEntidad(eUsuario, LOGIN);
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		String fechaNacimiento = servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
		String premium = servPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM);
		recientes = obtenerCancionesDesdeId(servPersistencia.recuperarPropiedadEntidad(eUsuario, RECIENTES));
		listaPlaylist = obtenerPlaylistsDesdeId(servPersistencia.recuperarPropiedadEntidad(eUsuario, LISTAS));
	
		System.out.println(fechaNacimiento);
		
		
		//Parseamos la fecha al formato indicado
		Date fecha = null;
		try {
			fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacimiento);	//Tranformamos la fecha a tipo date 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fecha);
		usuario.setId(eUsuario.getId());
		
		//Si es premium lo indicamos
		if(premium.equals("1"))	usuario.setPremium(true);
		else usuario.setPremium(false);
		
		//A�adimos las canciones a la lista de canciones recientes del usuario
		for (Cancion c : recientes) {
			usuario.addReciente(c);
		}
		
		//A�adimos las playlist a la lista de playlist
		for (Playlist p : listaPlaylist) {
			usuario.addPlayList(p);
		}
		
		
		return usuario;
	}

	private Entidad usuarioToEntidad(Usuario usuario) {
		Entidad eUsuario = new Entidad();
		eUsuario.setNombre(USUARIO);
		
		String fechaNacim = new SimpleDateFormat("dd/MM/yyyy").format(usuario.getFechaNacimiento());
		
		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad(NOMBRE, usuario.getNombre()),
				new Propiedad(APELLIDOS, usuario.getApellidos()), 
				new Propiedad(EMAIL, usuario.getEmail()),
				new Propiedad(LOGIN, usuario.getLogin()), 
				new Propiedad(PASSWORD, usuario.getPassword()),
				new Propiedad(FECHA_NACIMIENTO, fechaNacim),
				new Propiedad(PREMIUM, premiumToString(usuario.isPremium())),
				new Propiedad(RECIENTES, obtenerIdCanciones(usuario.getRecientes())),
				new Propiedad(LISTAS, obtenerIdPlaylist(usuario.getPlayLists())))));
		return eUsuario;
	}
	
	private String premiumToString(Boolean premium) {
		if(premium) return "1";
		else return "0";
	}
	
	
	private List<Playlist> obtenerPlaylistsDesdeId(String listas) {

		List<Playlist> listaPlaylist = new LinkedList<Playlist>();
		
		if(listas!= null && !listas.equals("")) {
			StringTokenizer strTok = new StringTokenizer(listas, " ");
			AdaptadorPlaylistTDS adaptadorP = AdaptadorPlaylistTDS.getUnicaInstancia();
			while (strTok.hasMoreTokens()) {
				listaPlaylist.add(adaptadorP.obtenerPlaylist(Integer.valueOf((String) strTok.nextElement())));
			}
		}
		
		return listaPlaylist;
	}
	
	private List<Cancion> obtenerCancionesDesdeId(String canciones) {

		List<Cancion> listaCanciones = new LinkedList<Cancion>();
		
		if(canciones!= null && !canciones.equals("")) {
			StringTokenizer strTok = new StringTokenizer(canciones, " ");
			AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();
			while (strTok.hasMoreTokens()) {
				listaCanciones.add(adaptadorC.obtenerCancion(Integer.valueOf((String) strTok.nextElement())));
			}
		}	
	
		return listaCanciones;
	}
	
	private String obtenerIdCanciones(List<Cancion> listaCanciones) {
		String aux = "";
		for (Cancion c : listaCanciones) {
			aux += c.getId() + " ";
		}
		
		return aux.trim();
	}
	
	private String obtenerIdPlaylist(List<Playlist> listas) {
		String aux = "";
		for (Playlist l : listas) aux += l.getId() + " ";
		return aux.trim();
	}
	

	public void registrarUsuario(Usuario usuario) {
		Entidad eUsuario;
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
			case PREMIUM:
				prop.setValor(String.valueOf(premiumToString(usuario.isPremium())));
				break;
			case RECIENTES:
				prop.setValor(String.valueOf(obtenerIdCanciones(usuario.getRecientes())));
				break;
			case LISTAS:
				prop.setValor(String.valueOf(obtenerIdPlaylist(usuario.getPlayLists())));
				break;
			default:
				break;
			}
			servPersistencia.modificarPropiedad(prop);
		}
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

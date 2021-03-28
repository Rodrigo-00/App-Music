package umu.tds.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.Playlist;

public final class AdaptadorPlaylistTDS implements IAdaptadorPlaylistDAO{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorPlaylistTDS unicaInstancia;
	
	private static final String PLAYLIST = "Playlist";
	private static final String NOMBRE = "nombre";
	private static final String CANCIONES = "canciones";
	private static final String USUARIO = "idUsuario";
	
	
	
	public static AdaptadorPlaylistTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			return new AdaptadorPlaylistTDS();
		} else
			return unicaInstancia;
	}
	
	private AdaptadorPlaylistTDS() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	
	private Entidad playlistToEntidad(Playlist lista, int idUsuario) {
		Entidad ePlaylist = new Entidad();
		ePlaylist.setNombre(PLAYLIST);
		
		ePlaylist.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
								 new Propiedad(NOMBRE, lista.getNombre()),
								 new Propiedad(CANCIONES, obtenerIdCanciones(lista.getCanciones())),
								 new Propiedad(USUARIO, String.valueOf(idUsuario)))));
		
		return ePlaylist;
	}
	
	private Playlist entidadToPlaylist(Entidad ePlaylist) {

		List<Cancion> canciones = new LinkedList<Cancion>();
		
		String nombre = servPersistencia.recuperarPropiedadEntidad(ePlaylist, NOMBRE);
		canciones = obtenerCancionesDesdeId(servPersistencia.recuperarPropiedadEntidad(ePlaylist, CANCIONES));
		

		System.out.println("LLAmado");
		
		Playlist playlist = new Playlist(nombre);
		playlist.setId(ePlaylist.getId());
		
		for (Cancion c : canciones)
			playlist.addCancion(c);
		
		return playlist;
	}
	

	@Override
	public void registrarPlaylist(Playlist lista, int idUsuario) {
		
		System.out.println("entra");
		boolean existe = true;
		Entidad ePlaylist;
		try {
			ePlaylist = servPersistencia.recuperarEntidad(lista.getId());
		}catch (NullPointerException e) {
			existe = false;
		}
		
		System.out.println("sale");
		
		if(existe) return;
		ePlaylist = playlistToEntidad(lista, idUsuario);
		ePlaylist = servPersistencia.registrarEntidad(ePlaylist);
		lista.setId(ePlaylist.getId());
	}
	
	
	@Override
	public Playlist obtenerPlaylist(int id) {
		
		Entidad ePlaylist = servPersistencia.recuperarEntidad(id);
		return entidadToPlaylist(ePlaylist);
	}
	
	@Override
	public boolean delete(Playlist lista) {
		Entidad ePlaylist;
		ePlaylist = servPersistencia.recuperarEntidad(lista.getId());
		
		return servPersistencia.borrarEntidad(ePlaylist);
	}
	
	@Override
	public void updatePlaylist(Playlist lista) {
		Entidad ePlaylist;

		ePlaylist = servPersistencia.recuperarEntidad(lista.getId());
		
		servPersistencia.eliminarPropiedadEntidad(ePlaylist, NOMBRE);
		servPersistencia.anadirPropiedadEntidad(ePlaylist, NOMBRE, lista.getNombre());
		
		String canciones = obtenerIdCanciones(lista.getCanciones());
		servPersistencia.eliminarPropiedadEntidad(ePlaylist, CANCIONES);
		servPersistencia.anadirPropiedadEntidad(ePlaylist, CANCIONES, canciones);

	}
	
	private String obtenerIdCanciones(List<Cancion> listaCanciones) {
		String aux = "";
		for (Cancion c : listaCanciones) {
			aux += c.getId() + " ";
		}
		return aux.trim();
	}
	
	
	private List<Cancion> obtenerCancionesDesdeId(String canciones) {

		List<Cancion> listaCanciones = new LinkedList<Cancion>();
		StringTokenizer strTok = new StringTokenizer(canciones, " ");
		AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			listaCanciones.add(adaptadorC.obtenerCancion(Integer.valueOf((String) strTok.nextElement())));
		}
		return listaCanciones;
	}
	
	@Override
	public List<Playlist> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(PLAYLIST);

		List<Playlist> listas = new LinkedList<Playlist>();
		for (Entidad ePlaylist : entidades) listas.add(obtenerPlaylist(ePlaylist.getId()));
		
		return listas;
	}
	
	@Override
	public List<Playlist> getAll(int idUsuario) {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(PLAYLIST);

		List<Playlist> listas = new LinkedList<Playlist>();
		for (Entidad ePlaylist : entidades) {
			int usuario = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(ePlaylist, USUARIO));
			System.out.println(usuario+" "+idUsuario);
			if(usuario == idUsuario) listas.add(obtenerPlaylist(ePlaylist.getId()));
		}
		
		return listas;
	}
}

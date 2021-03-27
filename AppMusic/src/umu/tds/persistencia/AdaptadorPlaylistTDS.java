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
import umu.tds.modelo.Usuario;

public final class AdaptadorPlaylistTDS implements IAdaptadorPlaylistDAO{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorPlaylistTDS unicaInstancia;
	
	private static final String PLAYLIST = "Playlist";
	private static final String NOMBRE = "nombre";
	private static final String CANCIONES = "canciones";
	
	
	
	public static AdaptadorPlaylistTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			return new AdaptadorPlaylistTDS();
		} else
			return unicaInstancia;
	}
	
	private AdaptadorPlaylistTDS() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	
	private Entidad playlistToEntidad(Playlist lista) {
		Entidad ePlaylist = new Entidad();
		ePlaylist.setNombre(PLAYLIST);
		
		ePlaylist.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
								 new Propiedad(NOMBRE, lista.getNombre()),
								 new Propiedad(CANCIONES, obtenerIdCanciones(lista.getCanciones())))));
		
		return ePlaylist;
	}
	
	private Playlist entidadToPlaylist(Entidad ePlaylist) {

		List<Cancion> canciones = new LinkedList<Cancion>();
		
		String nombre = servPersistencia.recuperarPropiedadEntidad(ePlaylist, NOMBRE);
		canciones = obtenerCancionesDesdeId(servPersistencia.recuperarPropiedadEntidad(ePlaylist, CANCIONES));

		Playlist playlist = new Playlist(nombre);
		playlist.setId(ePlaylist.getId());
		
		for (Cancion c : canciones)
			playlist.addCancion(c);
		
		return playlist;
	}
	

	@Override
	public void registrarPlaylist(Playlist lista) {
		
		boolean existe = true;
		Entidad ePlaylist;
		try {
			ePlaylist = servPersistencia.recuperarEntidad(lista.getId());
		}catch (NullPointerException e) {
			existe = false;
		}
		
		if(existe) return;
		ePlaylist = playlistToEntidad(lista);
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
}

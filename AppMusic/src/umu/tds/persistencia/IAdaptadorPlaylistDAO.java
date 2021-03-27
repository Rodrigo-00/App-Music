package umu.tds.persistencia;

import umu.tds.modelo.Playlist;
import umu.tds.modelo.Usuario;

public interface IAdaptadorPlaylistDAO {
	
	public void registrarPlaylist(Playlist lista);
	public Playlist obtenerPlaylist(int id);
	public boolean delete(Playlist lista);
}

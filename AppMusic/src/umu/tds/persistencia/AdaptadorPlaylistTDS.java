package umu.tds.persistencia;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Playlist;

public final class AdaptadorPlaylistTDS implements IAdaptadorPlaylistDAO{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorPlaylistTDS unicaInstancia;
	
	public static AdaptadorPlaylistTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			return new AdaptadorPlaylistTDS();
		} else
			return unicaInstancia;
	}
	
	private AdaptadorPlaylistTDS() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public void registrarPlaylist(Playlist lista) {
		// TODO Auto-generated method stub
		
	}
	
}

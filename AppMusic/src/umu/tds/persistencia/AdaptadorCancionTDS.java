package umu.tds.persistencia;

import beans.Entidad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Cancion;

public class AdaptadorCancionTDS implements IAdaptadorCancionDAO{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorCancionTDS unicaInstancia;
	
	public static AdaptadorCancionTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			return new AdaptadorCancionTDS();
		} else
			return unicaInstancia;
	}
	
	private AdaptadorCancionTDS() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
//Mira a ver si te sale este comentario RODRIGOOOOOOOOOOOOO
	@Override
	public void registrarCancion(Cancion cancion) {
		
		Entidad eCancion;
		boolean existe = true; 
		
		// Si la entidad est� registrada no la registra de nuevo
		try {
			eCancion = servPersistencia.recuperarEntidad(cancion.getId());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) return;
		
		
		//Crear entidad Cancion
		eCancion = new Entidad();
	}
	
}

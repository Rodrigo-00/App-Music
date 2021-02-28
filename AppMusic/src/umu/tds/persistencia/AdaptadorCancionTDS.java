package umu.tds.persistencia;

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

	@Override
	public void registrarCancion(Cancion song) {
		// TODO Auto-generated method stub
		
	}
	
}

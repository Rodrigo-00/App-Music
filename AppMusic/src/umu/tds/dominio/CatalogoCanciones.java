package umu.tds.dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import umu.tds.persistencia.FactoriaDAO;
import umu.tds.modelo.Cancion;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.IAdaptadorCancionDAO;

public class CatalogoCanciones {

	//Buscar canciones con filtros de b�squeda el t�tulo, interprete y estilo musical
	private Map<String,Cancion> cancionesInterprete;
	private Map<String,Cancion> cancionesTitulo;
	private Map<String,List<Cancion>> cancionesEstilo;
	private static CatalogoCanciones unicaInstancia;
	private IAdaptadorCancionDAO adaptadorCancion;
	
	
	public static CatalogoCanciones getUnicaInstancia(){
		
		if (unicaInstancia == null)
			return new CatalogoCanciones();
		else
			return unicaInstancia;
	}
	
	private CatalogoCanciones() {
		
		try {
			FactoriaDAO dao = FactoriaDAO.getInstancia();
			adaptadorCancion = dao.getCancionDAO();
			cancionesInterprete = new HashMap<String,Cancion>();
			cancionesTitulo = new HashMap<String,Cancion>();
			cancionesEstilo = new HashMap<String,List<Cancion>>();
			cargarCanciones();
		} catch (DAOException e) {
			e.printStackTrace();
		}	
		
	}
	
	public void addCancion(Cancion cancion) {
		cancionesInterprete.put(cancion.getInterprete(), cancion);
		cancionesTitulo.put(cancion.getTitulo(), cancion);
		if(cancionesEstilo.containsKey(cancion.getEstilo())) {
			List<Cancion> canciones = new LinkedList<Cancion>();
			canciones.add(cancion);
			cancionesEstilo.put(cancion.getEstilo(), canciones);
		
		}else cancionesEstilo.get(cancion.getEstilo()).add(cancion);
	}
	
	
	public void removeCancion (Cancion cancion) {
		
	}
	
	
	private void cargarCanciones() throws DAOException {
		 List<Cancion> canciones = adaptadorCancion.getAll();
			 for (Cancion cancion: canciones) 
				 addCancion(cancion); 
	}
	
}


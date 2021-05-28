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
	private List<Cancion> canciones;
	private Map<String,List<Cancion>> cancionesInterprete;
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
			canciones = new LinkedList<Cancion>();
			cancionesInterprete = new HashMap<String,List<Cancion>>();
			cancionesTitulo = new HashMap<String,Cancion>();
			cancionesEstilo = new HashMap<String,List<Cancion>>();
			cargarCanciones();
		} catch (DAOException e) {
			e.printStackTrace();
		}	
		
	}
	
	public void addCancion(Cancion cancion) {
		
		canciones.add(cancion);
		if(!cancionesInterprete.containsKey(cancion.getInterprete())) {
			List<Cancion> canciones = new LinkedList<Cancion>();
			canciones.add(cancion);
			cancionesInterprete.put(cancion.getInterprete(), canciones);
		}else cancionesInterprete.get(cancion.getInterprete()).add(cancion);
		
		cancionesTitulo.put(cancion.getTitulo(), cancion);
		
		if(!cancionesEstilo.containsKey(cancion.getEstilo())) {
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
	
	public List<Cancion> getAll() {
		return new LinkedList<Cancion>(canciones);
	}
	
	public List<Cancion> getCancionesInterprete(String interprete){
		return new LinkedList<Cancion>(cancionesInterprete.get(interprete));
	}
	
	public Cancion getCancionTitulo(String titulo){
		return cancionesTitulo.get(titulo);
	}
	
	public Cancion getCancion(String titulo, String interprete){
		
		if(!cancionesInterprete.containsKey(interprete)) return null; //Si no existe el interprete o no tiene canciones
		
		LinkedList<Cancion> canciones = (LinkedList<Cancion>) cancionesInterprete.get(interprete);
		for(Cancion c : canciones) {
			if(c.getTitulo().equals(titulo)) return c; //Si la cancion es del interprete, la devolvemos
		}
		return null;
	}
	
	
	
}


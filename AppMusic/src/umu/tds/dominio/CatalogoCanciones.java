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

	//Buscar canciones con filtros de bï¿½squeda el tï¿½tulo, interprete y estilo musical
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
		if(!cancionesInterprete.containsKey(interprete)) return new LinkedList(); //Si no hay se encuentra el interprete
		return new LinkedList<Cancion>(cancionesInterprete.get(interprete));
	}
	
	public Cancion getCancionTitulo(String titulo){
		if(!cancionesTitulo.containsKey(titulo)) return null;
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
	
	public List<Cancion> getCancionesEstilo(String estilo){
		if(!cancionesEstilo.containsKey(estilo)) return new LinkedList(); //Si no hay canciones de ese estilo
		return new LinkedList<Cancion>(cancionesEstilo.get(estilo));
	}
	
	public List<Cancion> getCancionesEstiloInter(String estilo, String interprete){
		
		List<Cancion> lista = new LinkedList<Cancion>();
		if(!cancionesInterprete.containsKey(interprete)) return lista; //Si no existe el interprete o no tiene canciones
		for (Cancion c : cancionesInterprete.get(interprete)) {
			if(c.getEstilo().equals(estilo)) lista.add(c);
		}
		return lista;
	}
	
	public String[] getEstilos(){	//Proporcionamos todos los estilos disponibles
		int tamaño = cancionesEstilo.keySet().size();
		String[] estilos = new String[tamaño+1];
		estilos[0] = "Estilo";
		int es = 1;
		for (String e: cancionesEstilo.keySet()) {
			estilos[es] = e;
			es++;
		}
		return estilos;
	}
	
}


package umu.tds.dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import umu.tds.persistencia.FactoriaDAO;
import umu.tds.modelo.Cancion;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.IAdaptadorCancionDAO;

public class CatalogoCanciones {

	//Buscar canciones con filtros de busqueda el titulo, interprete y estilo musical
	
	private List<Cancion> canciones;
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
			cargarCanciones();
		} catch (DAOException e) {
			e.printStackTrace();
		}	
		
	}
	
	public void addCancion(Cancion cancion) {
		
		boolean contiene = canciones.stream()
				.anyMatch(c -> c.getTitulo().equals(cancion.getTitulo()) && c.getInterprete().equals(cancion.getInterprete()) && c.getEstilo().equals(cancion.getEstilo())); //Suponemos que si el titulo, el interprete y el estilo es el mismo la canción es la misma
		
		if(!contiene) canciones.add(cancion);
	}
	
	
	private void cargarCanciones() throws DAOException {
		 List<Cancion> canciones = adaptadorCancion.getAll();
			 for (Cancion cancion: canciones) 
				 addCancion(cancion); 
	}
	
	public void removeCancion (Cancion cancion) {
		canciones.remove(cancion);
	}
	
	public List<Cancion> getAll() {
		return new LinkedList<Cancion>(canciones);
	}
	
	public List<Cancion> getCancionesInterprete(String interprete){
		return canciones.stream()
				.filter(ca -> ca.isInterprete(interprete))
				.collect(Collectors.toList());
	}
	
	public Cancion getCancionTitulo(String titulo){
		for(Cancion c:canciones) {
			if(c.getTitulo().equals(titulo)) return c;
		}
		return null;
	}
	
	public Cancion getCancion(String titulo, String interprete){		
		for(Cancion c:canciones) {
			if(c.getTitulo().equals(titulo) && c.getInterprete().equals(interprete)) return c;
		}
		return null;
	}
	
	public List<Cancion> getCancionesEstilo(String estilo){
		return canciones.stream()
				.filter(ca -> ca.isEstilo(estilo))
				.collect(Collectors.toList());
	}
	
	public List<Cancion> getCancionesEstiloInter(String estilo, String interprete){
		
		return canciones.stream()
				.filter(ca -> ca.isInterprete(interprete) && ca.isEstilo(estilo))
				.collect(Collectors.toList());
	}
	
	
	public Cancion getCancionTituloyEsti(String titulo, String estilo) {
		for(Cancion c:canciones) {
			if(c.getTitulo().equals(titulo) && c.getEstilo().equals(estilo)) return c;
		}
		return null;
	}
	
	public Cancion getCancionTitInterEsti(String titulo, String interprete, String estilo) {
		for(Cancion c:canciones) {
			if(c.getTitulo().equals(titulo) && c.getInterprete().equals(interprete) && c.getEstilo().equals(estilo)) return c;
		}
		return null;
	}
	
	public List<String> getEstilos(){	//Proporcionamos todos los estilos disponibles
		
		List<String> lista = canciones.stream()
				.map(ca -> ca.getEstilo())
				.distinct()
				.collect(Collectors.toList());
		lista.add(0, "Estilo");
		return lista;
	}
	
}


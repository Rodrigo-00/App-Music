package umu.tds.dominio;

import java.util.Comparator;

import java.util.LinkedList;
import java.util.List;
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
				.anyMatch(c -> c.getTitulo().equals(cancion.getTitulo()) && c.getInterprete().equals(cancion.getInterprete()) && c.getEstilo().equals(cancion.getEstilo())); //Suponemos que si el titulo, el interprete y el estilo es el mismo la canci�n es la misma
		
		if(!contiene) canciones.add(cancion);
	}
	
	
	private void cargarCanciones() throws DAOException {
		
		 List<Cancion> canciones = adaptadorCancion.getAll();
		 
		 canciones.stream().forEach(c->addCancion(c));
	}
	
	
	public void reproducida(Cancion c) {
		
		//Obtenemos el objeto asociado a la cancion que se ha reproducido
		Cancion cancion = c;
		for(Cancion ca: canciones) {
			if(ca.getId() == c.getId()) {
				cancion = ca;
				break;
			}
		}
		
		
		//Actualizamos el objeto
		cancion.reproducida();
		
		System.out.println("Cancion reproducida "+ cancion.getTitulo() +" "+ cancion.getReproducciones());
		
		//Actualizamos en la base de datos la cancion
		adaptadorCancion.updateCancion(cancion);
	}
	
	
	public void removeCancion (Cancion cancion) {
		canciones.remove(cancion);
	}
	
	public List<Cancion> getAll() {
		return canciones;
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
	public Cancion getCancionId(int id) {
		for(Cancion c:canciones) {
			if(c.getId() == id) return c;
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
	
	public List<Cancion> getMasReproducidas(){	//Devolvemos las canciones mas reproducidas ordenadas de mayor a menor
		return canciones.stream()
				.sorted(Comparator.comparing(Cancion::getReproducciones).reversed())
				.limit(10)
				.collect(Collectors.toList());
	}
	
	
}


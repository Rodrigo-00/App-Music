package umu.tds.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.Usuario;

public class AdaptadorCancionTDS implements IAdaptadorCancionDAO{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorCancionTDS unicaInstancia;
	
	private static final String CANCION = "Cancion";
	
	private static final String TITULO = "titulo";
	private static final String INTERPRETE = "interprete";
	private static final String ESTILO = "estilo";
	private static final String RUTA_FICHERO = "rutaFichero";
	private static final String REPRODUCCIONES = "reproducciones";
	
	
	public static AdaptadorCancionTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			return new AdaptadorCancionTDS();
		} else
			return unicaInstancia;
	}
	
	private AdaptadorCancionTDS() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	
	private Cancion entidadToCancion(Entidad eCancion) {

		String titulo = servPersistencia.recuperarPropiedadEntidad(eCancion, TITULO);
		String interprete = servPersistencia.recuperarPropiedadEntidad(eCancion, INTERPRETE);
		String estilo = servPersistencia.recuperarPropiedadEntidad(eCancion, ESTILO);
		String rutaFichero = servPersistencia.recuperarPropiedadEntidad(eCancion, RUTA_FICHERO);
		int reproducciones = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eCancion, REPRODUCCIONES));
		
		Cancion cancion = new Cancion(titulo, interprete, estilo, rutaFichero);
		cancion.setReproducciones(reproducciones);
		cancion.setId(eCancion.getId());
		
		return cancion;
	}
	
	private Entidad cancionToEntidad(Cancion cancion) {
		Entidad eCancion = new Entidad();
		eCancion.setNombre(CANCION);
		
		eCancion.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad(TITULO, cancion.getTitulo()),
				new Propiedad(INTERPRETE, cancion.getInterprete()), 
				new Propiedad(ESTILO, cancion.getEstilo()),
				new Propiedad(RUTA_FICHERO, cancion.getRutaFichero()),
				new Propiedad(REPRODUCCIONES, String.valueOf(cancion.getReproducciones())))));
		return eCancion;
	}

	@Override
	public void registrarCancion(Cancion cancion) {
		
		Entidad eCancion;
		boolean existe = true; 
		
		// Si la entidad esta registrada no la registra de nuevo
		try {
			eCancion = servPersistencia.recuperarEntidad(cancion.getId());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) return;
		
		
		//Crear entidad Cancion
		eCancion = cancionToEntidad(cancion);
		eCancion = servPersistencia.registrarEntidad(eCancion);
		cancion.setId(eCancion.getId());
	}

	@Override
	public Cancion obtenerCancion(int id) {
		
		Entidad eCancion = servPersistencia.recuperarEntidad(id);
		return entidadToCancion(eCancion);
	}
	
	@Override
	public boolean delete(Cancion cancion) {
		Entidad eCancion;
		eCancion = servPersistencia.recuperarEntidad(cancion.getId());
		
		return servPersistencia.borrarEntidad(eCancion);
	}
	
	@Override
	public void updateCancion(Cancion cancion) {
		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getId());
		servPersistencia.eliminarPropiedadEntidad(eCancion, RUTA_FICHERO);
		servPersistencia.anadirPropiedadEntidad(eCancion, RUTA_FICHERO, cancion.getRutaFichero());
		servPersistencia.eliminarPropiedadEntidad(eCancion, ESTILO);
		servPersistencia.anadirPropiedadEntidad(eCancion, ESTILO, cancion.getEstilo());
		servPersistencia.eliminarPropiedadEntidad(eCancion, REPRODUCCIONES);
		servPersistencia.anadirPropiedadEntidad(eCancion, REPRODUCCIONES, String.valueOf(cancion.getReproducciones()));
	}
	
	@Override
	public List<Cancion> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(CANCION);

		List<Cancion> canciones = new LinkedList<Cancion>();
		for (Entidad eCancion : entidades) canciones.add(obtenerCancion(eCancion.getId()));
		
		return canciones;
	}
	
}

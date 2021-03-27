package umu.tds.persistencia;

import java.util.List;

import umu.tds.modelo.Cancion;

public interface IAdaptadorCancionDAO {

	public void registrarCancion(Cancion cancion);
	public Cancion obtenerCancion(int id);
	public boolean delete(Cancion cancion);
	public void updateCancion(Cancion cancion);
	public List<Cancion> getAll();
}

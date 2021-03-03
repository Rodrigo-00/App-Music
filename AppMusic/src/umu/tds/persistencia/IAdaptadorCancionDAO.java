package umu.tds.persistencia;

import umu.tds.modelo.Cancion;

public interface IAdaptadorCancionDAO {

	public void registrarCancion(Cancion cancion);
	public Cancion obtenerCancion(int id);
}

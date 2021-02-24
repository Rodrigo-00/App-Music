package umu.tds.persistencia;

import java.util.List;

import umu.tds.dominio.Usuario;

public interface IAdaptadorUsuarioDAO {
	
	void create(Usuario asistente);
	boolean delete(Usuario asistente);
	void updatePerfil(Usuario asistente);
	Usuario get(int id);
	List<Usuario> getAll();
	
}

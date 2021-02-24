package umu.tds.persistencia;

import java.util.List;

import umu.tds.dominio.Usuario;

public interface IAdaptadorUsuarioDAO {
	
	public void create(Usuario asistente);	//Será mas bien registrar cliente no?
	public boolean delete(Usuario asistente);
	public void updatePerfil(Usuario asistente);
	public Usuario get(int id);
	public List<Usuario> getAll();
	
}

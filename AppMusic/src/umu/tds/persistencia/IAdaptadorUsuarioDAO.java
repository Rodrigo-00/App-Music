package umu.tds.persistencia;

import java.util.List;

import umu.tds.modelo.Usuario;

public interface IAdaptadorUsuarioDAO {
	
	public void registrarUsuario(Usuario usu);
	public boolean delete(Usuario usu);
	public void updatePerfil(Usuario usu);
	public Usuario obtenerUsuario(int id);
	public List<Usuario> getAll();
	
}

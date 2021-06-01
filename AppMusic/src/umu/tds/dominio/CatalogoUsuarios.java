package umu.tds.dominio;

import java.util.HashMap;

import java.util.LinkedList;
import java.util.List;

import umu.tds.modelo.Cancion;
import umu.tds.modelo.Usuario;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;

public class CatalogoUsuarios {
	private static CatalogoUsuarios unicaInstancia;
	private FactoriaDAO factoria;
	private List<Usuario> usuarios;

	public static CatalogoUsuarios getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoUsuarios();
		return unicaInstancia;
	}

	private CatalogoUsuarios (){
		usuarios = new LinkedList<Usuario>();
		
		try {
			factoria = FactoriaDAO.getInstancia();
			List<Usuario> listaAsistentes = factoria.getUsuarioDAO().getAll();
			for (Usuario usuario : listaAsistentes) {
				usuarios.add(usuario);
			}
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}
	}
	
	public List<Usuario> getUsuarios() throws DAOException {
		return new LinkedList<Usuario>(usuarios);
	}
	
	public Usuario getUsuario(String login) {
		for(Usuario u : usuarios) {
			if(u.getLogin().equals(login)) return u;
		}
		return null;
	}

	public Usuario getUsuario(int id) {
		for(Usuario u : usuarios) {
			if(u.getId() == id) return u;
		}
		return null;
	}
	
	public void addUsuario(Usuario usuario) {
		
		boolean contiene = usuarios.stream()
				.anyMatch(u -> u.getId() == usuario.getId());
		if(!contiene) usuarios.add(usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		usuarios.remove(usuario);
	}

}

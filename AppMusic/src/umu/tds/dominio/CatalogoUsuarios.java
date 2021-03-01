package umu.tds.dominio;

import java.util.HashMap;

import java.util.LinkedList;
import java.util.List;

import umu.tds.modelo.Usuario;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
//Prueba as
public class CatalogoUsuarios {
	private static CatalogoUsuarios unicaInstancia;
	private FactoriaDAO factoria;

	private HashMap<Integer, Usuario> asistentesPorID;
	private HashMap<String, Usuario> asistentesPorLogin;

	public static CatalogoUsuarios getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoUsuarios();
		return unicaInstancia;
	}
//comentario a
	private CatalogoUsuarios (){
		asistentesPorID = new HashMap<Integer, Usuario>();
		asistentesPorLogin = new HashMap<String, Usuario>();
		
		try {
			factoria = FactoriaDAO.getInstancia();
			List<Usuario> listaAsistentes = factoria.getUsuarioDAO().getAll();
			for (Usuario usuario : listaAsistentes) {
				asistentesPorID.put(usuario.getId(), usuario);
				asistentesPorLogin.put(usuario.getLogin(), usuario);
			}
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}
	}
	
	public List<Usuario> getUsuarios() throws DAOException {
		return new LinkedList<Usuario>(asistentesPorLogin.values());
	}
	
	public Usuario getUsuario(String login) {
		return asistentesPorLogin.get(login);
	}

	public Usuario getUsuario(int id) {
		return asistentesPorID.get(id);
	}
	
	public void addUsuario(Usuario usuario) {
		asistentesPorID.put(usuario.getId(), usuario);
		asistentesPorLogin.put(usuario.getLogin(), usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		asistentesPorID.remove(usuario.getId());
		asistentesPorLogin.remove(usuario.getLogin());
	}

}

package umu.tds.controlador;
//Comprueba si cambia
import umu.tds.dao.UsuarioDAO;
import umu.tds.dao.DAOException;
import umu.tds.dominio.Usuario;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.dominio.CatalogoUsuarios;
//Segundo comentario 456456
public final class Controlador {
//Tercera comprobacion 
	private Usuario usuarioActual;
	private static Controlador unicaInstancia;
	private FactoriaDAO factoria;

	private Controlador() {
		usuarioActual = null;
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public static Controlador getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new Controlador();
		return unicaInstancia;
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public boolean esUsuarioRegistrado(String login) {
		return CatalogoUsuarios.getUnicaInstancia().getUsuario(login) != null;
	}

	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = CatalogoUsuarios.getUnicaInstancia().getUsuario(nombre);
		if (usuario != null && usuario.getPassword().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}
	
	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password,
			String fechaNacimiento) {

//		if (esUsuarioRegistrado(login))
	//		return false;
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacimiento);

//		UsuarioDAO usuarioDAO = factoria
	//			.getUsuarioDAO(); /* Adaptador DAO para almacenar el nuevo Usuario en la BD */
//		usuarioDAO.create(usuario);

		CatalogoUsuarios.getUnicaInstancia().addUsuario(usuario);
		return true;
	}

	public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getLogin()))
			return false;

		UsuarioDAO usuarioDAO = factoria.getUsuarioDAO(); /* Adaptador DAO para borrar el Usuario de la BD */
		usuarioDAO.delete(usuario);

		CatalogoUsuarios.getUnicaInstancia().removeUsuario(usuario);
		return true;
	}

}

package umu.tds.modelo;

import java.time.LocalDate;

import umu.tds.controlador.Controlador;
import umu.tds.dominio.CatalogoUsuarios;


//ESTA CLASE CREO QUE SE PUEDE BORRAR

public class AppMusic {
	
	private static AppMusic unicaInstancia = null;
	
	private AppMusic() {}
	
	public static AppMusic getInstancia() {
		if (unicaInstancia == null) unicaInstancia = new AppMusic();
		return unicaInstancia;
	}
	
	
	public boolean login(String usuario, String clave) {
		return Controlador.getUnicaInstancia().loginUsuario(usuario, clave);
	}
	
	public Usuario registrarUsuario(String usuario, String clave, String nombre, String apellido, String mail, LocalDate fecha) {
		return null;
	}
}

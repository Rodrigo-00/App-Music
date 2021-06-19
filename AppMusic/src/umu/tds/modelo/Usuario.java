package umu.tds.modelo;

import java.util.LinkedList;
import java.util.List;

public class Usuario {
	
	
	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String login;
	private String password;
	private String fechaNacimiento;
	private Boolean premium;
	private List<Cancion> recientes;

	public Usuario(String nombre, String apellidos, String email, String login, String password, String fechaNacimiento) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.login = login;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		premium=false;
		recientes = new LinkedList<Cancion>();
	}
	
	
	public void addReciente(Cancion c) {
		if(!recientes.contains(c)) {
			if(recientes.size()==10) recientes.remove(9);	//Borramos la ultima cancion 
			recientes.add(0, c);	//Añadimos al inicio la cancion
		}
	}
	
	public Boolean getPremium() {
		return premium;
	}

	public void setPremium(Boolean premium) {
		this.premium = premium;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public List<Cancion> getRecientes() {
		return new LinkedList<Cancion>(recientes);
	}

}

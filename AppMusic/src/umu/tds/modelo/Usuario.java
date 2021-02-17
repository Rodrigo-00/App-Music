package umu.tds.modelo;

import java.time.LocalDate;

public class Usuario {
	private String usuario, clave;
	private String nombre, apellido;
	private String mail;
	private LocalDate fecha;
	
	public Usuario(String usuario, String clave, String nombre, String apellido, String mail, LocalDate fecha) {
		
		setUsuario(usuario);
		setClave(clave);
		setNombre(nombre);
		setApellido(apellido);
		setMail(mail);
		setFecha(fecha);
		
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
}

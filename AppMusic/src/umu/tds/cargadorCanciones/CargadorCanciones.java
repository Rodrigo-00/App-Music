package umu.tds.cargadorCanciones;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;


public class CargadorCanciones implements Serializable{
	private static CargadorCanciones unicaInstancia;
	private String archivoCanciones;
	private PropertyChangeSupport nuevaCancion;
	
	private CargadorCanciones() {
		nuevaCancion = new PropertyChangeSupport(this);
	}
	
	public static CargadorCanciones getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new CargadorCanciones();
		}
		return unicaInstancia;
	}
	
	public void addCancionesListener(PropertyChangeListener listener) {
		nuevaCancion.addPropertyChangeListener(listener);
	}

	public void removeCancionesListener(PropertyChangeListener listener) {
		nuevaCancion.removePropertyChangeListener(listener);
	}
	
	public void setArchivoCanciones(String nuevasCanciones) {
		String old = archivoCanciones;
		String nuevo = nuevasCanciones;
		nuevaCancion.firePropertyChange("arhivoCanciones", old, nuevo);
	}
}

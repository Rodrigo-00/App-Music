package umu.tds.cargadorCanciones;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import umu.tds.cargadorCanciones.Canciones;

public class MapperCancionesXMLtoJava {

	public static Canciones cargarCanciones(String fichero) {

		JAXBContext jc;
		Canciones canciones = null;
		try {
			jc = JAXBContext.newInstance("umu.tds.cargadorCanciones");
			Unmarshaller u = jc.createUnmarshaller();
			File file = new File(fichero);
			canciones = (Canciones) u.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}	
		return canciones;
	}
}

package umu.tds.modelo;

import java.time.LocalDate;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
public class Usuario {
	
	private final String MASREPRODUCIDAS = "MAS REPRODUCIDAS";
	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String login;
	private String password;
	private Date fechaNacimiento;
	private Boolean premium;
	private Playlist masReproducidas;	//Playlist de las canciones m�s reproducidas en toda la aplicacion que no persistimos
	private List<Cancion> recientes;
	private List<Playlist> playLists;
	private Descuento descuento;

	public Usuario(String nombre, String apellidos, String email, String login, String password, Date fechaNacimiento) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.login = login;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		premium=false;
		recientes = new LinkedList<Cancion>();
		playLists = new LinkedList<Playlist>();
	}
	
	
	public void addReciente(Cancion c) {
		
		Cancion ca;
		for(int i = 0; i< recientes.size(); i++) {
			ca = recientes.get(i);
			if(ca.getId() == c.getId()) {
				recientes.remove(i);
				break;
			}
		}
		
		if(recientes.size() == 10)	recientes.remove(9); //En la lista de recientes solo pueden haber 10 canciones
		recientes.add(0, c);	//Añadimos al inicio la cancion
	}
	
	public Playlist crearPlayList(String nombre) {
		
		if(getPlayList(nombre) != null)	//Si ya tenemos una playlist con el nombre devolvemos null
			return null;
		
		Playlist lista = new Playlist(nombre);	//El usuario es el experto en crear una playlist
		playLists.add(lista);
		
		return lista;
	}
	
	public void addPlayList(Playlist lista) {
		playLists.add(lista);
	}
	
	
	public List<String> nombresListas(){
		
		List<String> nombres = playLists.stream()
				.map(p -> p.getNombre())
				.collect(Collectors.toList());
				
		if(premium) nombres.add(0, MASREPRODUCIDAS);
		return nombres;
		
	}
	
	public Playlist addCancionToPlaylist(String play, Cancion cancion) {
		Playlist playlist = this.getPlayList(play);
		playlist.addCancion(cancion);
		return playlist;
	}
	public Playlist removeCancionPlaylist(String play, int id) {
		Playlist playlist = this.getPlayList(play);
		playlist.removeCancion(id);
		return playlist;
	}
	
	public Playlist getPlayList(String play) {
		
		if(premium && play.equals(MASREPRODUCIDAS)) return masReproducidas;
	
		for (Playlist playlist:playLists) {
			if(play.equals(playlist.getNombre()))
			return playlist;
		}
		return null;
	}
	public Playlist eliminaPlaylist(String playl) {
		Playlist playlist = this.getPlayList(playl);
		playLists.remove(playlist);
		return playlist;
	}
	
	public boolean isPremium() {
		return premium;
	}
	
	public List<Cancion> getCancionesPlaylist(String playlist){
		
		if(premium && playlist.equals(MASREPRODUCIDAS)) return masReproducidas.getCanciones();
		
		for (Playlist play: playLists) {
			if(play.getNombre().equals(playlist)) return play.getCanciones();
		}
		return new LinkedList<Cancion>();
	}
	

    private int getEdad() {	//Devuelve la edad del usuario
    	LocalDate localDate = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	Period period = Period.between(localDate, LocalDate.now());
    	return period.getYears();
    }
    
	public void otorgarDescuento() {
		if(getEdad() >= 65) descuento = new DescuentoMayores();
		else descuento = new DescuentoTemporal();
	}
	
	public double realizarPago() {
		premium = true;
		return descuento.aplicarDescuento();
	}
	
	public double consultarDescuento() {
		return descuento.getDescuento();
	}
	
	public void crearMasRepro(List<Cancion> masRepro) {
		masReproducidas = new Playlist(MASREPRODUCIDAS);
		masReproducidas.addCanciones(masRepro);
	}
	
	
	public Document creaPDF(String path) throws FileNotFoundException, DocumentException {
		if(this.premium) {
			FileOutputStream archivo = new FileOutputStream(path+"/appMusic.pdf");
		    Document documento = new Document();
		    PdfWriter.getInstance(documento, archivo);
		    documento.open();
		    documento.add(new Paragraph("Usuario: "+this.login, FontFactory.getFont("arial",40, Font.ITALIC)));
		    if(playLists.isEmpty()) {
				documento.add(new Paragraph("No has creado ninguna playlist todavia.", FontFactory.getFont("arial", 18)));
				return documento;
			}
			for(Playlist p : playLists) {
				documento.add(new Paragraph(""));
				List<Cancion> canciones = p.getCanciones();
			    documento.add(new Paragraph("Playlist "+p.getNombre()+":", FontFactory.getFont("arial", 18)));
			    for (Cancion c: canciones) {
			    	documento.add(new Paragraph("-Titulo: "+c.getTitulo()+", Interprete: "+c.getInterprete()+", Estilo: "+c.getEstilo(),FontFactory.getFont("arial", 10)));
			    }
			}
			documento.close();
			return documento;
		}
		return null;
	}
	
	
	public Playlist getMasRepro() {
		return masReproducidas;
	}
	
	
	public Descuento getDescuento() {
		return descuento;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public List<Cancion> getRecientes() {
		return recientes;
	}
	
	public List<Playlist> getPlayLists() {
		return playLists;
	}


}

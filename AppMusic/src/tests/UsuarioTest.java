package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.*;
import java.util.LinkedList;
import java.util.List;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.Playlist;
import umu.tds.modelo.Usuario;

public class UsuarioTest {
	Usuario usuario;
	Cancion cancion;
	Cancion cancion1;
	Playlist playlist;
	Playlist playlist1;
	
	@Before
	public void usuario() {
		usuario = new Usuario("Pedro", "Garcia", "p.garcia@um.es", "p.garcia", "pedro", new Date());
		cancion = new Cancion("titulo", "interprete", "estilo", "rutaFichero");
		cancion1 = new Cancion("titulo1", "interprete1", "estilo1", "rutaFichero1");
		playlist = new Playlist("nombre");
		playlist1 = new Playlist("nombre1");
	}

	@Test
	public void testAddReciente() {
		usuario.addReciente(cancion);
		if(usuario.getRecientes().contains(cancion))
			assertTrue(true);
	}

	@Test
	public void testCrearPlayListNoExiste() {
		Playlist playlist1 = usuario.crearPlayList("nombre1");
		assertNotNull(playlist1);
	}
	
	@Test
	public void testCrearPlayListExiste() {
		usuario.addPlayList(playlist);
		Playlist playlist1 = usuario.crearPlayList("nombre");
		assertNotNull(playlist1);
	}

	@Test
	public void testNombresListas() {
		usuario.addPlayList(playlist);
		usuario.addPlayList(playlist1);
		List<String> nombres = new LinkedList<String>();
		nombres.add(playlist.getNombre());
		nombres.add(playlist1.getNombre());
		for (String nombre: usuario.nombresListas()) {
			if(!nombre.contains(nombre))
				assertTrue(false);
		}
		assertTrue(true);
	}

	@Test
	public void testGetCancionesPlaylistExiste() {
		usuario.addPlayList(playlist);
		playlist.addCancion(cancion);
		playlist.addCancion(cancion1);
		assertSame(playlist.getCanciones(), usuario.getCancionesPlaylist("nombre"));
	}
	
	@Test
	public void testGetCancionesPlaylistNoExiste() {
		assertTrue(usuario.getCancionesPlaylist("nombre").isEmpty());
	}

	@Test
	public void testOtorgarDescuentoMayorIgual65() {
	}
	
	@Test
	public void testOtorgarDescuentoMenor65() {
		fail("Not yet implemented");
	}

	@Test
	public void testRealizarPago() {
		fail("Not yet implemented");
	}

	@Test
	public void testConsultarDescuento() {
		fail("Not yet implemented");
	}

	@Test
	public void testCrearMasRepro() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreaPDF() {
		fail("Not yet implemented");
	}
}

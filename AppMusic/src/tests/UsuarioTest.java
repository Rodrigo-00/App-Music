package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.*;

import umu.tds.modelo.Cancion;
import umu.tds.modelo.Playlist;
import umu.tds.modelo.Usuario;

public class UsuarioTest {
	Usuario usuario;
	Cancion cancion;
	Playlist playlist;
	
	@Before
	public void usuario() {
		usuario = new Usuario("Pedro", "Garcia", "p.garcia@um.es", "p.garcia", "pedro", new Date());
		cancion = new Cancion("titulo", "interprete", "estilo", "rutaFichero");
		playlist = new Playlist("nombre");
	}

	@Test
	public void testAddReciente() {
		usuario.addReciente(cancion);
		if(usuario.getRecientes().contains(cancion))
			assertTrue(true);
	}

	@Test
	public void testCrearPlayListNoExiste() {
		Playlist playlist1 = usuario.crearPlayList("nombre");
		assertNotNull(playlist1);
	}
	
	@Test
	public void testCrearPlayListExiste() {
		usuario.addPlayList(playlist);
		Playlist playlist1 = usuario.crearPlayList("nombre");
		assertNotNull(playlist1);
	}

	@Test
	public void testAddPlayList() {
		fail("Not yet implemented");
	}

	@Test
	public void testNombresListas() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddCancionToPlaylist() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveCancionPlaylist() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminaPlaylist() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsPremium() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCancionesPlaylist() {
		fail("Not yet implemented");
	}

	@Test
	public void testOtorgarDescuento() {
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

	@Test
	public void testSetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNombre() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetApellidos() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPassword() {
		fail("Not yet implemented");
	}
}

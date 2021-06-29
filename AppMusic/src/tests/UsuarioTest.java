package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.*;

import umu.tds.modelo.Cancion;
import umu.tds.modelo.Usuario;

public class UsuarioTest {
	
	@Before
	public void usuario() {
		Usuario usuario = new Usuario("Pedro", "Garcia", "p.garcia@um.es", "p.garcia", "pedro", new Date());
		Cancion cancion = new Cancion("titulo", "interprete", "estilo", "rutaFichero");
	}

	@Test
	public void testAddReciente() {
		fail("Not yet implemented");
	}

	@Test
	public void testCrearPlayList() {
		fail("Not yet implemented");
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
	public void testGetPlayList() {
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
	public void testGetMasRepro() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDescuento() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPremium() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPremium() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNombre() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNombre() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetApellidos() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetApellidos() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFechaNacimiento() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRecientes() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayLists() {
		fail("Not yet implemented");
	}

}

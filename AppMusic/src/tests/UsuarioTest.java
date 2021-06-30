package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Date;

import org.junit.*;

import com.itextpdf.text.DocumentException;

import java.util.LinkedList;
import java.util.List;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.Descuento;
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
		usuario = new Usuario("Pedro", "Garcia", "p.garcia@um.es", "p.garcia", "pedro", new Date(-521427600000L));
		cancion = new Cancion("titulo", "interprete", "estilo", "rutaFichero");
		cancion1 = new Cancion("titulo1", "interprete1", "estilo1", "rutaFichero1");
		playlist = new Playlist("nombre");
		playlist1 = new Playlist("nombre1");
	}

	@Test
	public void testAddReciente() {
		usuario.addReciente(cancion);
		if((usuario.getRecientes().contains(cancion))&&(usuario.getRecientes().size()<=10))
			assertTrue(true);
		else
			assertTrue(false);
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
		assertNull(playlist1);
	}

	@Test
	public void testNombresListas() {
		usuario.addPlayList(playlist);
		usuario.addPlayList(playlist1);
		List<String> nombres = new LinkedList<String>();
		nombres.add(playlist.getNombre());
		nombres.add(playlist1.getNombre());
		assertTrue(usuario.nombresListas().stream().allMatch(e->nombres.contains(e)));
	}
	
	@Test
	public void testNombresListasPremium() {
		usuario.addPlayList(playlist);
		usuario.addPlayList(playlist1);
		usuario.setPremium(true);
		List<String> nombres = new LinkedList<String>();
		nombres.add(playlist.getNombre());
		nombres.add(playlist1.getNombre());
		nombres.add("MAS REPRODUCIDAS");
		assertTrue(usuario.nombresListas().stream().allMatch(e->nombres.contains(e)));
	}
	
	@Test
	public void testAddCancionToPlaylist() {
		usuario.addPlayList(playlist);
		usuario.addCancionToPlaylist("nombre", cancion);
		usuario.addCancionToPlaylist("nombre", cancion1);
		if(playlist.getCanciones().contains(cancion))
			assertTrue(true);
		else
			assertTrue(false);
	}
	@Test
	public void testRemoveCancionPlaylist() {
		usuario.addPlayList(playlist);
		usuario.addCancionToPlaylist("nombre", cancion);
		usuario.removeCancionPlaylist("nombre", cancion.getId());
		if (!playlist.getCanciones().contains(cancion))
			assertTrue(true);
		else
			assertTrue(false);
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
		usuario.addPlayList(playlist1);
		usuario.addCancionToPlaylist("nombre1", cancion);
		assertTrue(usuario.getCancionesPlaylist("nombre").isEmpty());
	}
	
	@Test
	public void testGetPlaylistExiste() {
		usuario.addPlayList(playlist);
		assertNotNull(usuario.getPlayList("nombre"));
	}
	
	@Test
	public void testGetPlaylistNoExiste() {
		usuario.addPlayList(playlist);
		assertNull(usuario.getPlayList("nombre1"));
	}
	
	@Test
	public void testEliminaPlaylist() {
		usuario.addPlayList(playlist);
		usuario.addPlayList(playlist1);
		usuario.eliminaPlaylist("nombre");
		if(!usuario.getPlayLists().contains(playlist))
			assertTrue(true);
		else
			assertTrue(false);
	}

	@Test
	public void testOtorgarDescuentoMayorIgual65() {
		usuario.otorgarDescuento();
		Descuento descuento = usuario.getDescuento();
		if((descuento != null)&&(descuento.getClass().getSimpleName().equals("DescuentoMayores")))
			assertTrue(true);
		else
			assertTrue(false);
	}
	
	@Test
	public void testOtorgarDescuentoMenor65() {
		Usuario usuario1 = new Usuario("Pedro", "Garcia", "p.garcia@um.es", "p.garcia", "pedro", new Date(0L));
		usuario1.otorgarDescuento();
		Descuento descuento = usuario1.getDescuento();
		if((descuento != null)&&(descuento.getClass().getSimpleName().equals("DescuentoTemporal")))
			assertTrue(true);
		else
			assertTrue(false);
	}

	@Test
	public void testCreaPDFNoPremium() throws FileNotFoundException, DocumentException {
		usuario.addPlayList(playlist);
		usuario.addPlayList(playlist1);
		assertNull(usuario.creaPDF("/home/raul/Escritorio/"));
	}
	
	@Test
	public void testCreaPDFSinPlaylists() throws FileNotFoundException, DocumentException {
		usuario.setPremium(true);
		assertNull(usuario.creaPDF("/home/raul/Escritorio/"));
	}
	
	@Test
	public void testCreaPDF() throws FileNotFoundException, DocumentException {
		usuario.setPremium(true);
		usuario.addPlayList(playlist);
		usuario.addPlayList(playlist1);
		assertNotNull(usuario.creaPDF("/home/raul/Escritorio/"));
	}
}

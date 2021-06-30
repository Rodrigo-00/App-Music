package umu.tds.gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.DocumentException;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Cancion;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class VentanaCrearPlaylist {

	private JFrame frame;
	private JTextField textField;
	private JTextField txtInterprete;
	private JTextField txtTitulo;
	private JTable table;
	private JTable table_1;
	private JButton btnBuscar;
	private JPanel panel_south;
	private JPanel panel__center;
	private JComboBox comboBox;
	private boolean nuevaPlaylist;
	private List<Cancion> canciones = new LinkedList<Cancion>();	//Lista donde se almacenan las canciones que se van a mostrar en la tabla izq de la ventana
	private HashMap<Integer,String> acciones = new HashMap<Integer,String>();//Almacenará todas las acciones que serán aceptadas o canceladas

	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCrearPlaylist window = new VentanaCrearPlaylist();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaCrearPlaylist() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {	
	frame = new JFrame();
	frame.setResizable(false);
	frame.setMinimumSize(new Dimension(1200, 700));
	frame.setBounds(100, 100, 1198, 700);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JPanel panel_west = new JPanel();
	panel_west.setBackground(Color.LIGHT_GRAY);
	frame.getContentPane().add(panel_west, BorderLayout.WEST);
	panel_west.setLayout(new BoxLayout(panel_west, BoxLayout.Y_AXIS));
	
	JButton btnExplorar = new JButton("Explorar");
	btnExplorar.setIcon(new ImageIcon(VentanaCrearPlaylist.class.getResource("/umu/tds/imagenes/lupa.png")));
	btnExplorar.setBorderPainted(false);
	btnExplorar.setBackground(Color.LIGHT_GRAY);
	panel_west.add(btnExplorar);
	btnExplorar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			VentanaExplorar reg = new VentanaExplorar();
			reg.getFrame().setVisible(true);
			frame.setVisible(false);
		}
	});
	
	JButton btnNewButton_2 = new JButton("Nueva lista");
	btnNewButton_2.setIcon(new ImageIcon(VentanaCrearPlaylist.class.getResource("/umu/tds/imagenes/mas-positivo-suma-simbolo-matematico.png")));
	btnNewButton_2.setBorderPainted(false);
	btnNewButton_2.setBackground(Color.LIGHT_GRAY);
	panel_west.add(btnNewButton_2);
	
	JButton btnRecientes = new JButton("Reciente");
	btnRecientes.setIcon(new ImageIcon(VentanaCrearPlaylist.class.getResource("/umu/tds/imagenes/reloj-de-pared.png")));
	btnRecientes.setBorderPainted(false);
	btnRecientes.setBackground(Color.LIGHT_GRAY);
	panel_west.add(btnRecientes);
	btnRecientes.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			VentanaRecientes reg = new VentanaRecientes();
			reg.getFrame().setVisible(true);
			frame.setVisible(false);
		}
	});
	
	JButton btnMisL = new JButton("Mis listas");
	btnMisL.setIcon(new ImageIcon(VentanaCrearPlaylist.class.getResource("/umu/tds/imagenes/lista-de-reproduccion.png")));
	btnMisL.setBorderPainted(false);
	btnMisL.setBackground(Color.LIGHT_GRAY);
	panel_west.add(btnMisL);
	btnMisL.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			VentanaMisListas reg = new VentanaMisListas();
			reg.getFrame().setVisible(true);
			frame.setVisible(false);
		}
	});
	
	JPanel panel_center = new JPanel();
	frame.getContentPane().add(panel_center, BorderLayout.CENTER);
	panel_center.setLayout(new BorderLayout(0, 0));
	
	JPanel panel__north = new JPanel();
	panel_center.add(panel__north, BorderLayout.NORTH);
	GridBagLayout gbl_panel__north = new GridBagLayout();
	gbl_panel__north.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	gbl_panel__north.rowHeights = new int[]{1, 0, 0, 0, 0};
	gbl_panel__north.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
	gbl_panel__north.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	panel__north.setLayout(gbl_panel__north);
	
	textField = new JTextField();
	GridBagConstraints gbc_textField = new GridBagConstraints();
	gbc_textField.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField.insets = new Insets(0, 0, 5, 5);
	gbc_textField.gridx = 3;
	gbc_textField.gridy = 1;
	panel__north.add(textField, gbc_textField);
	textField.setColumns(10);
	
	JButton ButCrear = new JButton("Crear");
	GridBagConstraints gbc_ButCrear = new GridBagConstraints();
	gbc_ButCrear.insets = new Insets(0, 0, 5, 5);
	gbc_ButCrear.gridx = 7;
	gbc_ButCrear.gridy = 1;
	panel__north.add(ButCrear, gbc_ButCrear);
	ButCrear.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String nombre=textField.getText();
			System.out.println("EL NOMBRE ES "+nombre);
			boolean reply = Controlador.getUnicaInstancia().crearPlaylist(nombre);
			if(reply == false) {
				nuevaPlaylist = false;
				JOptionPane.showMessageDialog(ButCrear, "Ya existe una lista con ese nombre, pasará a modificarla", "Lista existente", JOptionPane.WARNING_MESSAGE, null);
				textField.setEditable(false);
				txtInterprete.setVisible(true);
				txtTitulo.setVisible(true);
				comboBox.setVisible(true);
				btnBuscar.setVisible(true);
				panel_south.setVisible(true);
				panel__center.setVisible(true);
				ButCrear.setVisible(false);
				DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
				List<Integer> canciones_1 = new LinkedList<Integer>();
				for(Cancion c:Controlador.getUnicaInstancia().getCancionesPlaylist(textField.getText())) {
					canciones_1.add(c.getId());
					model_1.addRow(new Object[] { c.getTitulo(), c.getInterprete() });
				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				canciones.addAll(Controlador.getUnicaInstancia().getAllCanciones());
				for(Cancion c : canciones ) {
					if (!canciones_1.contains(c.getId())) {
						model.addRow(new Object[] { c.getTitulo(), c.getInterprete() });
					}
				}
			}else {
				JOptionPane.showMessageDialog(ButCrear,"Playlist creada correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				nuevaPlaylist = true;
				textField.setEditable(false);
				txtInterprete.setVisible(true);
				txtTitulo.setVisible(true);
				comboBox.setVisible(true);
				btnBuscar.setVisible(true);
				panel_south.setVisible(true);
				panel__center.setVisible(true);
				ButCrear.setVisible(false);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				canciones.addAll(Controlador.getUnicaInstancia().getAllCanciones());
				for(Cancion c : canciones ) {
					model.addRow(new Object[] { c.getTitulo(), c.getInterprete() });
				}
			}
		}
	});
	
	txtInterprete = new JTextField();
	txtInterprete.setText("Interprete");
	GridBagConstraints gbc_txtInterprete = new GridBagConstraints();
	gbc_txtInterprete.insets = new Insets(0, 0, 5, 5);
	gbc_txtInterprete.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtInterprete.gridx = 1;
	gbc_txtInterprete.gridy = 2;
	panel__north.add(txtInterprete, gbc_txtInterprete);
	txtInterprete.setColumns(10);
	txtInterprete.setVisible(false);
	
	txtTitulo = new JTextField();
	txtTitulo.setText("Titulo");
	GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
	gbc_txtTitulo.insets = new Insets(0, 0, 5, 5);
	gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtTitulo.gridx = 3;
	gbc_txtTitulo.gridy = 2;
	panel__north.add(txtTitulo, gbc_txtTitulo);
	txtTitulo.setColumns(10);
	txtTitulo.setVisible(false);
	
	comboBox = new JComboBox();
	comboBox.setModel(new DefaultComboBoxModel(Controlador.getUnicaInstancia().getEstilos().toArray()));
	GridBagConstraints gbc_comboBox = new GridBagConstraints();
	gbc_comboBox.insets = new Insets(0, 0, 5, 5);
	gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
	gbc_comboBox.gridx = 5;
	gbc_comboBox.gridy = 2;
	panel__north.add(comboBox, gbc_comboBox);
	comboBox.setVisible(false);
	
	btnBuscar = new JButton("Buscar");
	GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
	gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
	gbc_btnBuscar.gridx = 7;
	gbc_btnBuscar.gridy = 2;
	panel__north.add(btnBuscar, gbc_btnBuscar);
	btnBuscar.setVisible(false);
	btnBuscar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			//Extraemos los datos seleccionados
			String interprete = txtInterprete.getText();
			String titulo = txtTitulo.getText();
			String estilo = (String) comboBox.getSelectedItem();
			
			mostrarCanciones(interprete, titulo, estilo);	//Mostramos las canciones en la tabla
		}
	});
	
	panel_south = new JPanel();
	panel_center.add(panel_south, BorderLayout.SOUTH);
	panel_south.setVisible(false);
	
	JButton btnNewButton = new JButton("Aceptar");
	panel_south.add(btnNewButton);
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Controlador controlador = Controlador.getUnicaInstancia();
			if(nuevaPlaylist) {
				controlador.crearPlaylist(textField.getText());
			}
			for (int cancionId: acciones.keySet()) {
				Cancion cancion = controlador.getCancionPorId(cancionId);
				String accion = acciones.get(cancionId);
				if(accion!=null && accion.equals("anadeCancion")) {
					controlador.anadeCancionPlaylist(textField.getText() , cancion);
				}else if(accion!=null && accion.equals("eliminaCancion")){
					controlador.eliminaCancionPlaylist(textField.getText() , cancion.getId());
				}
			}
			canciones = Controlador.getUnicaInstancia().getCancionesPlaylist(textField.getText());
			
			VentanaPrincipal reg = new VentanaPrincipal();
			reg.getFrame().setVisible(true);
			frame.setVisible(false);
		}
	});
	
	
	JButton btnNewButton_3 = new JButton("Cancelar");
	panel_south.add(btnNewButton_3);
	btnNewButton_3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(nuevaPlaylist) {
				Controlador.getUnicaInstancia().eliminaPlaylist(textField.getText());
			}
			VentanaPrincipal reg = new VentanaPrincipal();
			reg.getFrame().setVisible(true);
			frame.setVisible(false);
		}
	});
	
	panel__center = new JPanel();
	panel__center.setPreferredSize(new Dimension(1050, 10));
	panel_center.add(panel__center, BorderLayout.WEST);
	panel__center.setVisible(false);
	
	
	String[] columns = {"Titulo","Interprete"};
	panel__center.setLayout(new BoxLayout(panel__center, BoxLayout.X_AXIS));
	table = new JTable(new DefaultTableModel(columns, 0)) {
		@Override
		public boolean isCellEditable(int row, int column) { // Evitamos que las celdas sean modificables
			return false;
		}
	};
	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	JScrollPane scrollCanciones = new JScrollPane(table);
	panel__center.add(scrollCanciones);
	
	JPanel panel = new JPanel();
	panel__center.add(panel);
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	
	JButton btnNewButton_5_1_1 = new JButton("<<");
	panel.add(btnNewButton_5_1_1);
	
	JButton btnNewButton_4_1_1 = new JButton(">>");
	panel.add(btnNewButton_4_1_1);
	
	table_1 = new JTable(new DefaultTableModel(columns, 0)) {
		@Override
		public boolean isCellEditable(int row, int column) { // Evitamos que las celdas sean modificables
			return false;
		}
	};
	table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	JScrollPane scrollCancionesPlaylist = new JScrollPane(table_1);
	panel__center.add(scrollCancionesPlaylist);
	scrollCancionesPlaylist.setBorder(new TitledBorder(null, "Playlist", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	
	
	JPanel panel_north = new JPanel();
	panel_north.setBackground(Color.LIGHT_GRAY);
	frame.getContentPane().add(panel_north, BorderLayout.NORTH);
	GridBagLayout gbl_panel_north = new GridBagLayout();
	gbl_panel_north.columnWidths = new int[]{10, 155, 50, 155, 44, 0};
	gbl_panel_north.rowHeights = new int[]{10, 0, 10, 0};
	gbl_panel_north.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	gbl_panel_north.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
	panel_north.setLayout(gbl_panel_north);
	
	boolean isPremium = Controlador.getUnicaInstancia().isUsuarioPremium();
	JButton btnPdfPremium = new JButton();
	if(isPremium)	btnPdfPremium.setText("Generar pdf");
	else btnPdfPremium.setText("Hazte premium");
	
	btnPdfPremium.setBackground(Color.YELLOW);
	btnPdfPremium.setForeground(Color.BLACK);
	GridBagConstraints gbc_btnHaztePremium = new GridBagConstraints();
	gbc_btnHaztePremium.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnHaztePremium.insets = new Insets(0, 0, 5, 5);
	gbc_btnHaztePremium.gridx = 1;
	gbc_btnHaztePremium.gridy = 1;
	panel_north.add(btnPdfPremium, gbc_btnHaztePremium);	
	
	
	btnPdfPremium.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//Si es usario premium generamos el pdf sino nos desplazamos a la ventana convertirse en premium
			if (isPremium) {	
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.showOpenDialog(frame);
				File directory = chooser.getSelectedFile();
				try {
					Controlador.getUnicaInstancia().generaPDF(directory.getAbsolutePath());
				} catch (FileNotFoundException fe) {
					System.out.println(fe.getMessage());
				} catch (DocumentException d) {
					System.out.println(d.getMessage());
				}
			} else {
				VentanaPremium reg = new VentanaPremium();
				reg.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		}
	});
	
	
	JButton btnMenuPrincipal = new JButton("Menu Principal");
	GridBagConstraints gbc_btnAtrs = new GridBagConstraints();
	gbc_btnAtrs.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnAtrs.insets = new Insets(0, 0, 5, 5);
	gbc_btnAtrs.gridx = 3;
	gbc_btnAtrs.gridy = 1;
	panel_north.add(btnMenuPrincipal, gbc_btnAtrs);
	btnMenuPrincipal.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			VentanaPrincipal reg = new VentanaPrincipal();
			reg.getFrame().setVisible(true);
			frame.setVisible(false);
		}
	});
}
	
	
private void mostrarCanciones(String interprete, String titulo, String estilo) {
	
		Cancion cancion;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
		int filas = model.getRowCount();
		for(int i = 0; i < filas; i++) {
			model.removeRow(0);    //Eliminamos todas las lineas de la tabla
		}
		int filas_1 = model_1.getRowCount();
		List<Cancion> songs = new LinkedList<Cancion>();
		for (int i = 0; i < filas_1; i++) {
			String Titulo = (String)model_1.getValueAt(i, 0);
			String Interprete = (String)model_1.getValueAt(i, 1);
			Cancion song = Controlador.getUnicaInstancia().getCancionTituloeInter(Titulo, Interprete);
			songs.add(song);
		}
		
		//canciones.clear();//Vaciamos la lista de canciones
		List<Cancion> cancionesPrev = new LinkedList<Cancion>();
		
		if(((interprete.equals("Interprete") || interprete.equals("")) && (titulo.equals("") || titulo.equals("Titulo")) && comboBox.getSelectedItem().equals("Estilo"))) {
			//Buscar todas las canciones
			canciones = Controlador.getUnicaInstancia().getAllCanciones();
			cancionesPrev.addAll(canciones);
			for(Cancion c : cancionesPrev ) {
				if(!songs.contains(c)) {
					model.addRow(new Object[] { c.getTitulo(), c.getInterprete() });
				} else {
					canciones.remove(c);
				}
			}
		}else if(((interprete.equals("Interprete") || interprete.equals("")) && (!titulo.equals("") && !titulo.equals("Titulo")) && comboBox.getSelectedItem().equals("Estilo"))) {
			//Buscar por titulo
			cancion = Controlador.getUnicaInstancia().getCancionTitulo(titulo);
			if((cancion != null)&&(!songs.contains(cancion))) {
				canciones.add(cancion);
				model.addRow(new Object[] { cancion.getTitulo(), cancion.getInterprete() });
			}
			
		}else if(((!interprete.equals("Interprete") && !interprete.equals("")) && (titulo.equals("") || titulo.equals("Titulo")) && comboBox.getSelectedItem().equals("Estilo"))) {
			//Buscar por interprete
			canciones = Controlador.getUnicaInstancia().getCancionesInterprete(interprete);
			cancionesPrev.addAll(canciones);
			for(Cancion c : cancionesPrev ) {
				if(!songs.contains(c)) {
					model.addRow(new Object[] { c.getTitulo(), c.getInterprete() });
				} else {
					canciones.remove(c);
				}
			}
			
		}else if(((interprete.equals("Interprete") || interprete.equals("")) && (titulo.equals("") || titulo.equals("Titulo")) && !comboBox.getSelectedItem().equals("Estilo"))) {
			//Buscar por estilo
			canciones = Controlador.getUnicaInstancia().getCancionesEstilo(estilo);
			cancionesPrev.addAll(canciones);
			for(Cancion c : cancionesPrev ) {
				if(!songs.contains(c)) {
					model.addRow(new Object[] { c.getTitulo(), c.getInterprete() });
				} else {
					canciones.remove(c);
				}
			}
			
		}else if(((!interprete.equals("Interprete") && !interprete.equals("")) && (titulo.equals("") || titulo.equals("Titulo")) && !comboBox.getSelectedItem().equals("Estilo"))) {
			//Buscar por estilo y autor
			canciones = Controlador.getUnicaInstancia().getCancionesEstiloInter(estilo, interprete);
			cancionesPrev.addAll(canciones);
			for(Cancion c : cancionesPrev ) {
				if(!songs.contains(c)) {
					model.addRow(new Object[] { c.getTitulo(), c.getInterprete() });
				} else {
					canciones.remove(c);
				}
			}
		}else if(((interprete.equals("Interprete") || interprete.equals("")) && (!titulo.equals("") && !titulo.equals("Titulo")) && !comboBox.getSelectedItem().equals("Estilo"))) {
			//Buscar por estilo y titulo
			cancion = Controlador.getUnicaInstancia().getCancionTituloyEsti(titulo, estilo);
			if((cancion != null)&&(!songs.contains(cancion))) {
				canciones.add(cancion);
				model.addRow(new Object[] { cancion.getTitulo(), cancion.getInterprete() });
			}
			
		}else if(((!interprete.equals("Interprete") && !interprete.equals("")) && (!titulo.equals("") && !titulo.equals("Titulo")) && !comboBox.getSelectedItem().equals("Estilo"))){	
			//Buscar por titulo, autor y estilo
			cancion = Controlador.getUnicaInstancia().getCancionTitInterEsti(titulo, interprete, estilo);
			if((cancion != null)&&(!songs.contains(cancion))) {
				canciones.add(cancion);
				model.addRow(new Object[] { cancion.getTitulo(), cancion.getInterprete() });
			}
			
		}else {
			//buscar por autor y titulo
			cancion = Controlador.getUnicaInstancia().getCancionTituloeInter(titulo, interprete);
			if((cancion != null)&&(!songs.contains(cancion))) {
				canciones.add(cancion);
				model.addRow(new Object[] { cancion.getTitulo(), cancion.getInterprete() });
			}
		}
		
	}

}

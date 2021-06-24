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

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Cancion;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class VentanaCrearPlaylist {

	private JFrame frame;
	private JTextField textField;
	private JTextField txtInterprete;
	private JTextField txtTitulo;
	private JTable table;
	private JTable table_1;
	private JButton btnBuscar;
	private JPanel panel__center;
	private JPanel panel_south;
	private JPanel panel__west;
	private JPanel panel__east;
	private JComboBox comboBox;
	private List<Cancion> canciones;	//Lista donde se almacenan las canciones que se van a mostrar en la tabla izq de la ventana
	private HashMap<Cancion,String> acciones = new HashMap<Cancion,String>();//Almacenará todas las acciones que serán aceptadas o canceladas

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
	private void initialize() {	
	frame = new JFrame();
	frame.setBounds(100, 100, 1198, 698);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JPanel panel_west = new JPanel();
	panel_west.setBackground(Color.LIGHT_GRAY);
	frame.getContentPane().add(panel_west, BorderLayout.WEST);
	panel_west.setLayout(new BoxLayout(panel_west, BoxLayout.Y_AXIS));
	
	JButton btnNewButton_1 = new JButton("Explorar");
	btnNewButton_1.setIcon(new ImageIcon(VentanaCrearPlaylist.class.getResource("/umu/tds/imagenes/lupa.png")));
	btnNewButton_1.setBorderPainted(false);
	btnNewButton_1.setBackground(Color.LIGHT_GRAY);
	panel_west.add(btnNewButton_1);
	
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
	
	JButton btnMisL = new JButton("Mis listas");
	btnMisL.setIcon(new ImageIcon(VentanaCrearPlaylist.class.getResource("/umu/tds/imagenes/lista-de-reproduccion.png")));
	btnMisL.setBorderPainted(false);
	btnMisL.setBackground(Color.LIGHT_GRAY);
	panel_west.add(btnMisL);
	
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
				JOptionPane.showMessageDialog(ButCrear, "Ya existe una lista con ese nombre", "Lista existente", JOptionPane.WARNING_MESSAGE, null);
				textField.setEditable(false);
				txtInterprete.setVisible(true);
				txtTitulo.setVisible(true);
				comboBox.setVisible(true);
				btnBuscar.setVisible(true);
				panel__center.setVisible(true);
				panel_south.setVisible(true);
				panel__west.setVisible(true);
				panel__east.setVisible(true);
				DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
				List<Cancion> canciones_1 = Controlador.getUnicaInstancia().getCancionesPlaylist(textField.getText());
				for(Cancion c : canciones_1 ) {
					model_1.addRow(new Object[] { c.getTitulo(), c.getInterprete() });
				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				canciones = Controlador.getUnicaInstancia().getAllCanciones();
				for(Cancion c : canciones ) {
					if (!canciones_1.contains(c))
						model.addRow(new Object[] { c.getTitulo(), c.getInterprete() });
				}
				
			}else {
				JOptionPane.showMessageDialog(ButCrear,"Playlist creada correctamente", "Mensaje", JOptionPane.PLAIN_MESSAGE);
				textField.setEditable(false);
				txtInterprete.setVisible(true);
				txtTitulo.setVisible(true);
				comboBox.setVisible(true);
				btnBuscar.setVisible(true);
				panel__center.setVisible(true);
				panel_south.setVisible(true);
				panel__west.setVisible(true);
				panel__east.setVisible(true);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				canciones = Controlador.getUnicaInstancia().getAllCanciones();
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
			frame.setVisible(false);
			
			//Extraemos los datos seleccionados
			String interprete = txtInterprete.getText();
			String titulo = txtTitulo.getText();
			String estilo = (String) comboBox.getSelectedItem();
			
			mostrarCanciones(interprete, titulo, estilo);	//Mostramos las canciones en la tabla

			frame.setVisible(true);
		}
	});
	
	panel__center = new JPanel();
	panel_center.add(panel__center, BorderLayout.CENTER);
	GridBagLayout gbl_panel__center = new GridBagLayout();
	gbl_panel__center.columnWidths = new int[]{15, 0, 15, 0};
	gbl_panel__center.rowHeights = new int[]{84, 78, 80, 0};
	gbl_panel__center.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
	gbl_panel__center.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
	panel__center.setLayout(gbl_panel__center);
	panel__center.setVisible(false);
	
	JButton btnNewButton_4 = new JButton(">>");
	GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
	gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
	gbc_btnNewButton_4.gridx = 1;
	gbc_btnNewButton_4.gridy = 1;
	panel__center.add(btnNewButton_4, gbc_btnNewButton_4);
	btnNewButton_4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int row = table.getSelectedRow();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
			String Titulo = model.getValueAt(row, 0).toString();
			String Interprete = model.getValueAt(row, 1).toString();
			model.removeRow(row);
			if (!acciones.containsKey(Controlador.getUnicaInstancia().getCancionTituloeInter(Titulo, Interprete))) {
				acciones.put(Controlador.getUnicaInstancia().getCancionTituloeInter(Titulo, Interprete), "añadeCancion");
			} else {
				acciones.remove(Controlador.getUnicaInstancia().getCancionTituloeInter(Titulo, Interprete), "eliminaCancion");
			}
			model_1.addRow(new Object[] { Titulo, Interprete });
		}
	});
	
	JButton btnNewButton_5 = new JButton("<<");
	GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
	gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
	gbc_btnNewButton_5.gridx = 1;
	gbc_btnNewButton_5.gridy = 2;
	panel__center.add(btnNewButton_5, gbc_btnNewButton_5);
	btnNewButton_5.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int row = table_1.getSelectedRow();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
			String Titulo = model_1.getValueAt(row, 0).toString();
			String Interprete = model_1.getValueAt(row, 1).toString();
			model_1.removeRow(row);
			if (!acciones.containsKey(Controlador.getUnicaInstancia().getCancionTituloeInter(Titulo, Interprete))) {
				acciones.put(Controlador.getUnicaInstancia().getCancionTituloeInter(Titulo, Interprete),"eliminaCancion");
			} else {
				acciones.remove(Controlador.getUnicaInstancia().getCancionTituloeInter(Titulo, Interprete), "añadeCancion");
			}
			model.addRow(new Object[] { Titulo, Interprete });
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
			for (Cancion cancion: acciones.keySet()) {
				String accion = acciones.get(cancion);
				System.out.println(accion);
				if(accion!=null && accion.equals("añadeCancion")) {
					controlador.añadeCancionPlaylist(textField.getText() , cancion);
				}else if(accion!=null && accion.equals("eliminaCancion")){
					controlador.eliminaCancionPlaylist(textField.getText() , cancion);
				}
			}
			VentanaPrincipal reg = new VentanaPrincipal();
			reg.getFrame().setVisible(true);
			frame.setVisible(false);
		}
	});
	
	
	JButton btnNewButton_3 = new JButton("Cancelar");
	panel_south.add(btnNewButton_3);
	btnNewButton_3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			VentanaPrincipal reg = new VentanaPrincipal();
			reg.getFrame().setVisible(true);
			frame.setVisible(false);
		}
	});
	
	panel__west = new JPanel();
	panel_center.add(panel__west, BorderLayout.WEST);
	panel__west.setVisible(false);
	
	
	String[] columns = {"Titulo","Interprete"};
	table = new JTable(new DefaultTableModel(columns, 0));	
	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	JScrollPane scrollCanciones = new JScrollPane(table); 
	panel__west.add(scrollCanciones);
	
	panel__east = new JPanel();
	panel_center.add(panel__east, BorderLayout.EAST);
	panel__east.setVisible(false);
	
	
	table_1 = new JTable(new DefaultTableModel(columns, 0));
	table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	table_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Playlist", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
	JScrollPane scrollCancionesPlaylist = new JScrollPane(table_1);
	panel__east.add(scrollCancionesPlaylist);
	
	
	JPanel panel_north = new JPanel();
	panel_north.setBackground(Color.LIGHT_GRAY);
	frame.getContentPane().add(panel_north, BorderLayout.NORTH);
	GridBagLayout gbl_panel_north = new GridBagLayout();
	gbl_panel_north.columnWidths = new int[]{10, 10, 10, 0, 10, 0, 0, 10, 0};
	gbl_panel_north.rowHeights = new int[]{10, 0, 10, 0};
	gbl_panel_north.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
	gbl_panel_north.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
	panel_north.setLayout(gbl_panel_north);
	
	JLabel lblNewLabel_1 = new JLabel("Hola "+ Controlador.getUnicaInstancia().getLoginUsuario());
	lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
	GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
	gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
	gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTH;
	gbc_lblNewLabel_1.gridwidth = 3;
	gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_1.gridx = 1;
	gbc_lblNewLabel_1.gridy = 1;
	panel_north.add(lblNewLabel_1, gbc_lblNewLabel_1);
	
	JButton btnHaztePremium_1 = new JButton("Hazte Premium");
	btnHaztePremium_1.setBorderPainted(false);
	btnHaztePremium_1.setBackground(Color.YELLOW);
	GridBagConstraints gbc_btnHaztePremium_1 = new GridBagConstraints();
	gbc_btnHaztePremium_1.insets = new Insets(0, 0, 5, 5);
	gbc_btnHaztePremium_1.gridx = 5;
	gbc_btnHaztePremium_1.gridy = 1;
	panel_north.add(btnHaztePremium_1, gbc_btnHaztePremium_1);
	btnHaztePremium_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(Controlador.getUnicaInstancia().isUsuarioPremium()) {
				JOptionPane.showMessageDialog(btnHaztePremium_1, "Ya eres usuaio Premium", "Error", JOptionPane.ERROR_MESSAGE, null);
			}else{
				VentanaPremium reg = new VentanaPremium();
				reg.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		}
	});
	
	JButton btnSalir_1 = new JButton("Salir");
	btnSalir_1.setBorderPainted(false);
	btnSalir_1.setBackground(SystemColor.window);
	btnSalir_1.setActionCommand("Salir");
	GridBagConstraints gbc_btnSalir_1 = new GridBagConstraints();
	gbc_btnSalir_1.insets = new Insets(0, 0, 5, 5);
	gbc_btnSalir_1.gridx = 6;
	gbc_btnSalir_1.gridy = 1;
	panel_north.add(btnSalir_1, gbc_btnSalir_1);
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
		
		canciones.clear();//Vaciamos la lista de canciones
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

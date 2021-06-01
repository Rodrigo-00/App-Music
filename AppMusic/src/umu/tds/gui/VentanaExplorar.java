package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Cancion;

import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.DropMode;
import java.awt.Cursor;
import javax.swing.JSlider;
import javax.swing.JScrollPane;

public class VentanaExplorar {

	JFrame frmVentanaExplorar;
	private JTextField txtInterprete;
	private JTextField txtTitulo;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;
	private JTable table_5;
	private JTable table_6;
	private JTable table_7;
	private JTable table_8;
	private JTable table_9;
	
	public VentanaExplorar() {
		initialize();
	}
	
	public JFrame getFrame() {
		return frmVentanaExplorar;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaExplorar frame = new VentanaExplorar();
					frame.frmVentanaExplorar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void mostrarVentana() {
		frmVentanaExplorar.setLocationRelativeTo(null);
		frmVentanaExplorar.setVisible(true);
	}
	
	public void initialize() {
		frmVentanaExplorar = new JFrame();
		frmVentanaExplorar.setTitle("AppMusic");
		frmVentanaExplorar.setBounds(100, 100, 583, 368);
		frmVentanaExplorar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel contentPane = (JPanel) frmVentanaExplorar.getContentPane();
		frmVentanaExplorar.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		frmVentanaExplorar.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{10, 0, 10, 10, 0, 0, 0, 10, 68, 10, -3, 0};
		gbl_panel.rowHeights = new int[]{10, 0, 20, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnAtras = new JButton("Atr\u00E1s");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal reg = new VentanaPrincipal();
				reg.getFrame().setVisible(true);
				frmVentanaExplorar.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalir.gridx = 2;
		gbc_btnSalir.gridy = 1;
		panel.add(btnAtras, gbc_btnSalir);
		
		JButton btnHaztePremium = new JButton("Hazte Premium");
		btnHaztePremium.setBackground(Color.YELLOW);
		btnHaztePremium.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnHaztePremium = new GridBagConstraints();
		gbc_btnHaztePremium.insets = new Insets(0, 0, 5, 5);
		gbc_btnHaztePremium.gridx = 5;
		gbc_btnHaztePremium.gridy = 1;
		panel.add(btnHaztePremium, gbc_btnHaztePremium);
		
		JButton btnSalir = new JButton("Salir");
		GridBagConstraints gbc_btnSalir_1 = new GridBagConstraints();
		gbc_btnSalir_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSalir_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalir_1.gridx = 8;
		gbc_btnSalir_1.gridy = 1;
		panel.add(btnSalir, gbc_btnSalir_1);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin reg = new VentanaLogin();
				reg.getFrame().setVisible(true);
				frmVentanaExplorar.setVisible(false);
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		frmVentanaExplorar.getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JButton btnNewButton_1 = new JButton("Explorar");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/lupa.png")));
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Nueva lista");
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/mas-positivo-suma-simbolo-matematico.png")));
		panel_1.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCrearPlaylist reg = new VentanaCrearPlaylist();
				reg.getFrame().setVisible(true);
				frmVentanaExplorar.setVisible(false);
			}
		});
		
		JButton btnRecientes = new JButton("Reciente");
		btnRecientes.setBorderPainted(false);
		btnRecientes.setBackground(Color.LIGHT_GRAY);
		btnRecientes.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/reloj-de-pared.png")));
		panel_1.add(btnRecientes);
		
		JButton btnMisL = new JButton("Mis listas");
		btnMisL.setBorderPainted(false);
		btnMisL.setBackground(Color.LIGHT_GRAY);
		btnMisL.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/lista-de-reproduccion.png")));
		panel_1.add(btnMisL);
		
		JPanel panel_2 = new JPanel();
		frmVentanaExplorar.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		
		txtInterprete = new JTextField();
		txtInterprete.setText("Interprete");
		panel_4.add(txtInterprete);
		txtInterprete.setColumns(10);
		
		txtTitulo = new JTextField();
		txtTitulo.setText("Titulo");
		panel_4.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(Controlador.getUnicaInstancia().getEstilos().toArray()));	//Obtenemos todos los estilos
		panel_4.add(comboBox);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.SOUTH);
		
		JButton btnNewButton_3 = new JButton("Cancelar");
		panel_5.add(btnNewButton_3); 
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtInterprete.setText("Interprete");
				txtTitulo.setText("Titulo");
				comboBox.setSelectedItem("Estilo");
			}
		});


		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(800, 800));
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		//Tabla
		String[] columns = {"Titulo","Interprete"};
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		JTable table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		table.setEnabled(false);
		table.setBounds(5, 5, 5, 5);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setAutoCreateColumnsFromModel(false);
		table.setEditingRow(-2);
		table.setEditingColumn(-2);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTable table = (JTable) e.getSource();
				int column = table.columnAtPoint(e.getPoint());
				int row = table.rowAtPoint(e.getPoint());
				
				System.out.println(column);
				System.out.println(row);
			}
		});
		model.addRow(new Object[] { "dasdas", "dasdasd" });
		model.addRow(new Object[] { "dasdas", "dasdasd" });
		model.addRow(new Object[] { "dasdas", "dasdasd" });
		model.addRow(new Object[] { "dasdas", "dasdasd" });
		model.addRow(new Object[] { "dasdas", "dasdasd" });
		model.addRow(new Object[] { "dasdas", "dasdasd" });
		model.addRow(new Object[] { "dasdas", "dasdasd" });
		model.addRow(new Object[] { "dasdas", "dasdasd" });
		model.addRow(new Object[] { "dasdas", "dasdasd" });
		model.addRow(new Object[] { "dasdas", "dasdasd" });

		scrollPane.setViewportView(table);
	
		
		
		JButton btnNewButton = new JButton("Buscar");
		panel_5.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmVentanaExplorar.setVisible(false);
				
				//Extraemos los datos seleccionados
				String interprete = txtInterprete.getText();
				String titulo = txtTitulo.getText();
				String estilo = (String) comboBox.getSelectedItem();
				
				int filas = model.getRowCount();
				System.out.println("Eliminamo "+filas);
				for(int i = 1; i <= filas; i++) {
					System.out.println(i);
					model.removeRow(0);    //Eliminamos todas las lineas de la tabla
				}
				
				
				if(((interprete.equals("Interprete") || interprete.equals("")) && (titulo.equals("") || titulo.equals("Titulo")) && comboBox.getSelectedItem().equals("Estilo"))) {
					//Buscar todas las canciones
					List<Cancion> canciones = Controlador.getUnicaInstancia().getAllCanciones();
					for(Cancion c : canciones ) {
						model.addRow(new Object[] { c.getTitulo(), c.getInterprete() });
					}
				}else if(((interprete.equals("Interprete") || interprete.equals("")) && (!titulo.equals("") && !titulo.equals("Titulo")) && comboBox.getSelectedItem().equals("Estilo"))) {
					//Buscar por titulo
					Cancion cancion = Controlador.getUnicaInstancia().getCancionTitulo(titulo);
					if(cancion != null) model.addRow(new Object[] { cancion.getTitulo(), cancion.getInterprete() });
					
				}else if(((!interprete.equals("Interprete") && !interprete.equals("")) && (titulo.equals("") || titulo.equals("Titulo")) && comboBox.getSelectedItem().equals("Estilo"))) {
					//Buscar por interprete
					List<Cancion> canciones = Controlador.getUnicaInstancia().getCancionesInterprete(interprete);
					for(Cancion c : canciones ) {
						model.addRow(new Object[] { c.getTitulo(), c.getInterprete() });
					}
					
				}else if(((interprete.equals("Interprete") || interprete.equals("")) && (titulo.equals("") || titulo.equals("Titulo")) && !comboBox.getSelectedItem().equals("Estilo"))) {
					//Buscar por estilo
					List<Cancion> canciones = Controlador.getUnicaInstancia().getCancionesEstilo(estilo);
					for(Cancion c : canciones ) {
						model.addRow(new Object[] { c.getTitulo(), c.getInterprete() });
					}
					
				}else if(((!interprete.equals("Interprete") && !interprete.equals("")) && (titulo.equals("") || titulo.equals("Titulo")) && !comboBox.getSelectedItem().equals("Estilo"))) {
					//Buscar por estilo y autor
					System.out.println("entra");
					List<Cancion> canciones = Controlador.getUnicaInstancia().getCancionesEstiloInter(estilo, interprete);
					for(Cancion c : canciones ) {
						System.out.println("Cancion encontrada ");
						model.addRow(new Object[] { c.getTitulo(), c.getInterprete() });
					}
				} if(((interprete.equals("Interprete") || interprete.equals("")) && (!titulo.equals("") && !titulo.equals("Titulo")) && !comboBox.getSelectedItem().equals("Estilo"))) {
					//Buscar por estilo y titulo
					Cancion cancion = Controlador.getUnicaInstancia().getCancionTituloyEsti(titulo, estilo);
					if(cancion != null) model.addRow(new Object[] { cancion.getTitulo(), cancion.getInterprete() });
					
				} if(((!interprete.equals("Interprete") && !interprete.equals("")) && (!titulo.equals("") && !titulo.equals("Titulo")) && !comboBox.getSelectedItem().equals("Estilo"))){	
					//Buscar por titulo, autor y estilo
					Cancion cancion = Controlador.getUnicaInstancia().getCancionTitInterEsti(titulo, interprete, estilo);
					if(cancion != null) model.addRow(new Object[] { cancion.getTitulo(), cancion.getInterprete() });
					
					
				}else {
					//buscar por autor y titulo
					Cancion cancion = Controlador.getUnicaInstancia().getCancionTituloeInter(titulo, interprete);
					if(cancion != null) model.addRow(new Object[] { cancion.getTitulo(), cancion.getInterprete() });
				}
				
				scrollPane.setViewportView(table);
				frmVentanaExplorar.setVisible(true);
			}
		});
		
		
		//finTabla
		
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7, BorderLayout.SOUTH);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setMaximumSize(new Dimension(92, 25));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.setPreferredSize(new Dimension(50, 50));
		btnNewButton_4.setMinimumSize(new Dimension(92, 25));
		btnNewButton_4.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/next_music_player_play_media-512alreves.png")));
		panel_7.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setPreferredSize(new Dimension(50, 50));
		btnNewButton_5.setBackground(UIManager.getColor("CheckBox.background"));
		btnNewButton_5.setForeground(Color.WHITE);
		btnNewButton_5.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/pause.png")));
		panel_7.add(btnNewButton_5);
		
		JButton btnAdelantar = new JButton("");
		btnAdelantar.setPreferredSize(new Dimension(50, 50));
		btnAdelantar.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/next_music_player_play_media-512.png")));
		panel_7.add(btnAdelantar);
		
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

	}
	
	private void mostrarCanciones(JPanel panel_6) {
		frmVentanaExplorar.setVisible(false);
		LinkedList<Cancion> canciones = (LinkedList<Cancion>) Controlador.getUnicaInstancia().getAllCanciones();
		String[] columns = {"Column 1","Column 2"};
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		JTable table = new JTable(model);
		for(Cancion c : canciones ) {
			model.addRow(new Object[] { c.getTitulo(), c.getInterprete() });
		}
			/*new Object[][] {
				{null, null},
				{"dasdas", null},
				{"asdsda", null},
			},
			new String[] {
				"New column", ""
			}
		));*/
		
		while(true) {
			frmVentanaExplorar.setVisible(false);
		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				JTable table = (JTable) e.getSource();
				int column = table.columnAtPoint(e.getPoint());
				int row = table.rowAtPoint(e.getPoint());
				
				System.out.println(column);
				System.out.println(row);
			}
			
		});
		panel_6.add(table, BorderLayout.CENTER);
		frmVentanaExplorar.setVisible(true);
		}
		/*model.addRow(new Object[] { "sdsada", "sdsada" });
		model.addRow(new Object[] { "sdsada", "sdsada" });
		model.addRow(new Object[] { "sdsada", "sdsada" });
		model.addRow(new Object[] { "sdsada", "sdsada" });
		model.addRow(new Object[] { "sdsada", "sdsada" });*/
		
	}
}
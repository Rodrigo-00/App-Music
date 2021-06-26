package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Cancion;

public class VentanaRecientes {

	JFrame frmVentanaRecientes;
	private JTextField txtInterprete;
	private JTextField txtTitulo;
	private List<Cancion> canciones;	//Lista donde se almacenan las canciones que se van a mostrar en la tabla de la ventana
	private Cancion cancActual;	//Cancion que actualmente esta en ejecución o pausada
	private int numCancion; //Alamacenamos el indice de la lista en el que se encuentra la cancion seleccionada
	private DefaultTableModel model;
	private JComboBox comboBox;
	private Boolean reproduciendo;	//Nos sirve para comprobar si se esta reproduciendo o no una cancion
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public VentanaRecientes() {
		initialize();
	}
	
	public JFrame getFrame() {
		return frmVentanaRecientes;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRecientes frame = new VentanaRecientes();
					frame.frmVentanaRecientes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void mostrarVentana() {
		frmVentanaRecientes.setLocationRelativeTo(null);
		frmVentanaRecientes.setVisible(true);
	}
	
	public void initialize() {
		frmVentanaRecientes = new JFrame();
		frmVentanaRecientes.setTitle("AppMusic");
		frmVentanaRecientes.setBounds(100, 100, 583, 368);
		frmVentanaRecientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		reproduciendo = false;
		cancActual = null;
		
		JPanel contentPane = (JPanel) frmVentanaRecientes.getContentPane();
		frmVentanaRecientes.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		frmVentanaRecientes.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{10, 0, 10, 10, 0, 0, 0, 10, 68, 10, -3, 0};
		gbl_panel.rowHeights = new int[]{10, 0, 20, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnAtras = new JButton("Menu Principal");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(reproduciendo) Controlador.getUnicaInstancia().pararCancion();   //Llamamos al controlador para pausar la cancion si se esta reproduciendo alguna
				VentanaPrincipal reg = new VentanaPrincipal();
				reg.getFrame().setVisible(true);
				frmVentanaRecientes.setVisible(false);
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
		btnHaztePremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(reproduciendo) Controlador.getUnicaInstancia().pararCancion();   //Llamamos al controlador para pausar la cancion si se esta reproduciendo alguna
				if(Controlador.getUnicaInstancia().isUsuarioPremium()) {
					JOptionPane.showMessageDialog(btnHaztePremium, "Ya eres usuaio Premium", "Error", JOptionPane.WARNING_MESSAGE, null);
				}else{
					VentanaPremium reg = new VentanaPremium();
					reg.getFrame().setVisible(true);
					frmVentanaRecientes.setVisible(false);
				}
			}
		});
		
		JButton btnSalir = new JButton("Salir");
		GridBagConstraints gbc_btnSalir_1 = new GridBagConstraints();
		gbc_btnSalir_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSalir_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalir_1.gridx = 8;
		gbc_btnSalir_1.gridy = 1;
		panel.add(btnSalir, gbc_btnSalir_1);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(reproduciendo) Controlador.getUnicaInstancia().pararCancion();   //Llamamos al controlador para pausar la cancion si se esta reproduciendo alguna
				VentanaLogin reg = new VentanaLogin();
				reg.getFrame().setVisible(true);
				frmVentanaRecientes.setVisible(false);
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		frmVentanaRecientes.getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JButton btnNewButton_1 = new JButton("Explorar");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/lupa.png")));
		panel_1.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(reproduciendo) Controlador.getUnicaInstancia().pararCancion();   //Llamamos al controlador para pausar la cancion si se esta reproduciendo alguna
				VentanaExplorar reg = new VentanaExplorar();
				reg.getFrame().setVisible(true);
				frmVentanaRecientes.setVisible(false);
			}
		});
		
		JButton btnNewButton_2 = new JButton("Nueva lista");
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/mas-positivo-suma-simbolo-matematico.png")));
		panel_1.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(reproduciendo) Controlador.getUnicaInstancia().pararCancion();   //Llamamos al controlador para pausar la cancion si se esta reproduciendo alguna
				VentanaCrearPlaylist reg = new VentanaCrearPlaylist();
				reg.getFrame().setVisible(true);
				frmVentanaRecientes.setVisible(false);
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
		btnMisL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(reproduciendo) Controlador.getUnicaInstancia().pararCancion();   //Llamamos al controlador para pausar la cancion si se esta reproduciendo alguna
				VentanaMisListas reg = new VentanaMisListas();
				reg.getFrame().setVisible(true);
				frmVentanaRecientes.setVisible(false);
			}
		});
		
		JPanel panel_2 = new JPanel();
		frmVentanaRecientes.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));


		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(800, 800));
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		//Tabla
		String[] columns = {"Titulo","Interprete"};
		model = new DefaultTableModel(columns, 0);
		
		@SuppressWarnings("serial")
		JTable table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {	//Evitamos que las celdas sean modificables
				return false;
			}
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		table.setEnabled(true);
		table.setBounds(5, 5, 5, 5);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setAutoCreateColumnsFromModel(false);
		table.setEditingRow(-2);
		table.setEditingColumn(-2);

		
		//Añadimos las canciones escuchadas recientemente por el usuario
		canciones = Controlador.getUnicaInstancia().getRecientes();
		for(Cancion c : canciones ) {
			model.addRow(new Object[] { c.getTitulo(), c.getInterprete() });
		}
		scrollPane.setViewportView(table);
		
		
	
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7, BorderLayout.SOUTH);
		
		JButton btnAtrasar = new JButton("");
		btnAtrasar.setForeground(Color.WHITE);
		btnAtrasar.setMaximumSize(new Dimension(92, 25));
		btnAtrasar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numCancion != 0 && reproduciendo) {
					cancActual = canciones.get(numCancion-1);	//Establecemos la cancion actual
					numCancion--;	
					table.setRowSelectionInterval(numCancion, numCancion);	
					Controlador.getUnicaInstancia().pararCancion();   //Llamamos al controlador para pausar la cancion si se esta reproduciendo alguna
					try {
						Controlador.getUnicaInstancia().reproducirCancion(cancActual);	//Llamamos al controlador para reproducir la cancion
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}
			}
		});
		
		btnAtrasar.setPreferredSize(new Dimension(50, 50));
		btnAtrasar.setMinimumSize(new Dimension(92, 25));
		btnAtrasar.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/next_music_player_play_media-512alreves.png")));
		panel_7.add(btnAtrasar);
		
		JButton btnReproducirPausar = new JButton("");
		btnReproducirPausar.setPreferredSize(new Dimension(50, 50));
		btnReproducirPausar.setBackground(UIManager.getColor("CheckBox.background"));
		btnReproducirPausar.setForeground(Color.WHITE);
		btnReproducirPausar.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/play.png")));
		panel_7.add(btnReproducirPausar);
		btnReproducirPausar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(reproduciendo) {	//Se pulsa el boton de pause
					reproduciendo = false;
					Controlador.getUnicaInstancia().pausarCancion();	//Llamamos al controlador para pausar la cancion
					btnReproducirPausar.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/play.png")));
					
				}else if(row != -1 && canciones.size() > 0) {	//Si hay seleccionada alguna fila de la tabla y la tabla contiene canciones 	
						System.out.println("Se ejecuta "+ canciones.get(row).getTitulo());
						btnReproducirPausar.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/pause.png")));
						
						if(cancActual!= null && cancActual.equals(canciones.get(row))){
							Controlador.getUnicaInstancia().reanudarCancion();  //Reanudamos la cancion
						}else {
							cancActual = canciones.get(row);
							try {
								Controlador.getUnicaInstancia().reproducirCancion(cancActual);	//Llamamos al controlador para reproducir la cancion
							} catch (MalformedURLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}	
							numCancion=row;
						}
						reproduciendo = true;
				}
				
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {	//Si se han producido dos clicks se ejecuta la cancion
					JTable table = (JTable) e.getSource();
					int row = table.rowAtPoint(e.getPoint());
					try {
						if((reproduciendo || !reproduciendo) && (cancActual == null || !cancActual.equals(canciones.get(row)))) {
							if(reproduciendo) Controlador.getUnicaInstancia().pararCancion();   //Llamamos al controlador para pausar la cancion si se esta reproduciendo alguna
							cancActual = canciones.get(row);
							Controlador.getUnicaInstancia().reproducirCancion(cancActual);	//Llamamos al controlador para reproducir la cancion
							numCancion=row;
						
						}else if(!reproduciendo && cancActual!= null && cancActual.equals(canciones.get(row))) {
							Controlador.getUnicaInstancia().reanudarCancion();	//Reanudamos la cancion
						}
			
						btnReproducirPausar.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/pause.png")));	//Cambiamos el icono del boton central
						reproduciendo = true;
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}   	
				}
			}
		});
		
		
		JButton btnAdelantar = new JButton("");
		btnAdelantar.setForeground(Color.WHITE);
		btnAdelantar.setPreferredSize(new Dimension(50, 50));
		btnAdelantar.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/next_music_player_play_media-512.png")));
		panel_7.add(btnAdelantar);
		btnAdelantar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numCancion != canciones.size()-1 && reproduciendo) {
					cancActual = canciones.get(numCancion+1);	//Establecemos la cancion actual
					numCancion++;	
					table.setRowSelectionInterval(numCancion, numCancion);	
					Controlador.getUnicaInstancia().pararCancion();   //Llamamos al controlador para pausar la cancion si se esta reproduciendo alguna
					try {
						Controlador.getUnicaInstancia().reproducirCancion(cancActual);	//Llamamos al controlador para reproducir la cancion
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}
			}
		});
		
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

	}
}
package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.Playlist;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import java.awt.FlowLayout;

public class VentanaMisListas{

	private JFrame frmVentanaMisListas;
	private List<Cancion> canciones;	//Lista donde se almacenan las canciones de la playlist seleccionada
	private Boolean reproduciendo = false;	//Nos sirve para comprobar si se esta reproduciendo o no una cancion
	private Cancion cancActual;	//Cancion que actualmente esta en ejecuci�n o pausada
	private int numCancion; //Alamacenamos el indice de la lista en el que se encuentra la cancion seleccionada
	private DefaultTableModel model;
	private String listaseleccionada; //Guardamos la lista que est� seleccionada
	
	public VentanaMisListas() {
		initialize();
	}
	
	public JFrame getFrame() {
		return frmVentanaMisListas;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMisListas frame = new VentanaMisListas();
					frame.frmVentanaMisListas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void mostrarVentana() {
		frmVentanaMisListas.setLocationRelativeTo(null);
		frmVentanaMisListas.setVisible(true);
	}
	
	public void initialize() {
		frmVentanaMisListas = new JFrame();
		frmVentanaMisListas.setTitle("AppMusic");
		frmVentanaMisListas.setBounds(100, 100, 641, 390);
		frmVentanaMisListas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canciones = new LinkedList<Cancion>();
		
		JPanel contentPane = (JPanel) frmVentanaMisListas.getContentPane();
		frmVentanaMisListas.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		frmVentanaMisListas.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{10, 10, 10, 0, 0, 0, 0, 26, 65, 0, 0};
		gbl_panel.rowHeights = new int[]{10, 0, 10, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnAtrs = new JButton("Menu Principal");
		GridBagConstraints gbc_btnAtrs = new GridBagConstraints();
		gbc_btnAtrs.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAtrs.insets = new Insets(0, 0, 5, 5);
		gbc_btnAtrs.gridx = 2;
		gbc_btnAtrs.gridy = 1;
		panel.add(btnAtrs, gbc_btnAtrs);
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal reg = new VentanaPrincipal();
				reg.getFrame().setVisible(true);
				frmVentanaMisListas.setVisible(false);
			}
		});
		
		JButton btnHaztePremium = new JButton("Hazte Premium");
		btnHaztePremium.setBorderPainted(false);
		btnHaztePremium.setBackground(Color.YELLOW);
		GridBagConstraints gbc_btnHaztePremium = new GridBagConstraints();
		gbc_btnHaztePremium.insets = new Insets(0, 0, 5, 5);
		gbc_btnHaztePremium.gridx = 5;
		gbc_btnHaztePremium.gridy = 1;
		panel.add(btnHaztePremium, gbc_btnHaztePremium);
		btnHaztePremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Controlador.getUnicaInstancia().isUsuarioPremium()) {
					JOptionPane.showMessageDialog(btnHaztePremium, "Ya eres usuaio Premium", "Error", JOptionPane.ERROR_MESSAGE, null);
				}else{
					VentanaPremium reg = new VentanaPremium();
					reg.getFrame().setVisible(true);
					frmVentanaMisListas.setVisible(false);
				}
			}
		});
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBackground(SystemColor.control);
		btnSalir.setBorderPainted(false);
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSalir.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalir.gridx = 8;
		gbc_btnSalir.gridy = 1;
		panel.add(btnSalir, gbc_btnSalir);
		btnSalir.setActionCommand("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin reg = new VentanaLogin();
				reg.getFrame().setVisible(true);
				frmVentanaMisListas.setVisible(false);
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		frmVentanaMisListas.getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JButton btnExplorar = new JButton("Explorar");
		btnExplorar.setBorderPainted(false);
		btnExplorar.setBackground(Color.LIGHT_GRAY);
		btnExplorar.setIcon(new ImageIcon(VentanaMisListas.class.getResource("/umu/tds/imagenes/lupa.png")));
		panel_1.add(btnExplorar);
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaExplorar reg = new VentanaExplorar();
				reg.getFrame().setVisible(true);
				frmVentanaMisListas.setVisible(false);
			}
		});
		
		JButton btnLista = new JButton("Nueva lista");
		btnLista.setBorderPainted(false);
		btnLista.setBackground(Color.LIGHT_GRAY);
		btnLista.setIcon(new ImageIcon(VentanaMisListas.class.getResource("/umu/tds/imagenes/mas-positivo-suma-simbolo-matematico.png")));
		panel_1.add(btnLista);
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int reply = JOptionPane.showConfirmDialog(null, "Desea Crear una nueva Playlist", "Crear Playlist", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					VentanaCrearPlaylist reg = new VentanaCrearPlaylist();
					reg.getFrame().setVisible(true);
					frmVentanaMisListas.setVisible(false);
				}
			}
		});
		
		JButton btnRecientes = new JButton("Reciente");
		btnRecientes.setBorderPainted(false);
		btnRecientes.setBackground(Color.LIGHT_GRAY);
		btnRecientes.setIcon(new ImageIcon(VentanaMisListas.class.getResource("/umu/tds/imagenes/reloj-de-pared.png")));
		panel_1.add(btnRecientes);
		btnRecientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRecientes reg = new VentanaRecientes();
				reg.getFrame().setVisible(true);
				frmVentanaMisListas.setVisible(false);
			}
		});
		
		JButton btnMisL = new JButton("Mis listas");
		btnMisL.setBorderPainted(false);
		btnMisL.setBackground(Color.LIGHT_GRAY);
		btnMisL.setIcon(new ImageIcon(VentanaMisListas.class.getResource("/umu/tds/imagenes/lista-de-reproduccion.png")));
		panel_1.add(btnMisL);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		frmVentanaMisListas.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(800, 800));
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		//Tabla
		String[] columns = {"Titulo","Interprete"};
		model = new DefaultTableModel(columns, 0);
		
		@SuppressWarnings("serial")
		JTable table_1 = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {	//Evitamos que las celdas sean modificables
				return false;
			}
		};
		/*
		
		table_1.setPreferredScrollableViewportSize(new Dimension(450, 200));
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setBorder(null);	
		JScrollPane scrollCancionesPlaylist = new JScrollPane(table_1);
		panel_4.add(scrollCancionesPlaylist);*/
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		table_1.setEnabled(true);
		table_1.setBounds(5, 5, 5, 5);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table_1.setAutoCreateColumnsFromModel(false);
		table_1.setEditingRow(-2);
		table_1.setEditingColumn(-2);
		scrollPane.setViewportView(table_1);
		
		
		List<String> listas = Controlador.getUnicaInstancia().nombresListas();
		JList<String> list = new JList(listas.toArray());
		list.setSelectedIndex(0);
		
		list.setBounds(0, 0, 142, 109);
		panel_3.add(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
			
				if(listaseleccionada == null || !listaseleccionada.equals(list.getSelectedValue())) {
					listaseleccionada = list.getSelectedValue();
					
					int filas = model.getRowCount();
					System.out.println("hay " + filas);
					for(int i = 1; i <= filas; i++) {
						System.out.println("BORRAMOS");
						model.removeRow(0);    //Eliminamos todas las lineas de la tabla
					}
					
					//AQUI EN LA SEGUNDA CONSULTA DICE QUE NO HAY CANCIONES
					canciones.clear();
					canciones = Controlador.getUnicaInstancia().getCancionesPlaylist(listaseleccionada);
					
					System.out.println(canciones.size());
					
					for(Cancion c : canciones ) {
						System.out.println("A�adimos la cancion tras borrar"+ c.getTitulo());
						model.addRow(new Object[] { c.getTitulo(), c.getInterprete() });
					}
					
					scrollPane.setViewportView(table_1);
				}
			}
		});
		
		
		//A�adimos a la tabla las primeras canciones de la tabla
		canciones = Controlador.getUnicaInstancia().getCancionesPlaylist(list.getSelectedValue());		
		if(canciones != null && !canciones.isEmpty()) {
			
			for(Cancion c : canciones ) {
				System.out.println("A�adimos la cancion "+ c.getTitulo());
				model.addRow(new Object[] { c.getTitulo(), c.getInterprete() });
			}
			
			scrollPane.setViewportView(table_1);
			
		}
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, BorderLayout.SOUTH);
	
		JButton btnAnterior = new JButton("");
		btnAnterior.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/next_music_player_play_media-512alreves.png")));
		panel_5.add(btnAnterior);
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numCancion != 0 && reproduciendo) {
					cancActual = canciones.get(numCancion-1);	//Establecemos la cancion actual
					numCancion--;	
					table_1.setRowSelectionInterval(numCancion, numCancion);	
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
		
		JButton btnReproducir = new JButton("");
		btnReproducir.setIcon(new ImageIcon(VentanaMisListas.class.getResource("/umu/tds/imagenes/play.png")));
		panel_5.add(btnReproducir);
		btnReproducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccionada = (String)list.getSelectedValue();
				System.out.println("Playlist a reproducir "+ seleccionada);
				
				int row = table_1.getSelectedRow();
				if(reproduciendo) {	//Se pulsa el boton de pause
					reproduciendo = false;
					Controlador.getUnicaInstancia().pausarCancion();	//Llamamos al controlador para pausar la cancion
					btnReproducir.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/play.png")));
					
				}else if(row != -1 && canciones.size() > 0) {	//Si hay seleccionada alguna fila de la tabla y la tabla contiene canciones 	
						System.out.println("Se ejecuta "+ canciones.get(row).getTitulo());
						btnReproducir.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/pause.png")));
						
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
	
		
		JButton btnPosterior = new JButton("");
		btnPosterior.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/next_music_player_play_media-512.png")));
		panel_5.add(btnPosterior);
		btnPosterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numCancion != canciones.size()-1 && reproduciendo) {
					cancActual = canciones.get(numCancion+1);	//Establecemos la cancion actual
					numCancion++;	
					table_1.setRowSelectionInterval(numCancion, numCancion);	
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
	}
}
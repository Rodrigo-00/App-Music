package umu.tds.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.List;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Cancion;

import javax.swing.border.LineBorder;
import javax.swing.ListSelectionModel;

public class VentanaPlaylistNueva {

	private JFrame frame;
	private JTextField textField;
	private JTextField txtInterprete;
	private JTextField txtTitulo;
	private JTextField txtEstilo;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPlaylistNueva window = new VentanaPlaylistNueva();
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
	public VentanaPlaylistNueva() {
		initialize();
	}
	
	public JFrame getFrame() {
		return frame;
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
		btnNewButton_1.setIcon(new ImageIcon(VentanaPlaylistNueva.class.getResource("/umu/tds/imagenes/lupa.png")));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		panel_west.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Nueva lista");
		btnNewButton_2.setIcon(new ImageIcon(VentanaPlaylistNueva.class.getResource("/umu/tds/imagenes/mas-positivo-suma-simbolo-matematico.png")));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		panel_west.add(btnNewButton_2);
		
		JButton btnRecientes = new JButton("Reciente");
		btnRecientes.setIcon(new ImageIcon(VentanaPlaylistNueva.class.getResource("/umu/tds/imagenes/reloj-de-pared.png")));
		btnRecientes.setBorderPainted(false);
		btnRecientes.setBackground(Color.LIGHT_GRAY);
		panel_west.add(btnRecientes);
		
		JButton btnMisL = new JButton("Mis listas");
		btnMisL.setIcon(new ImageIcon(VentanaPlaylistNueva.class.getResource("/umu/tds/imagenes/lista-de-reproduccion.png")));
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
		
		txtInterprete = new JTextField();
		txtInterprete.setText("Interprete");
		GridBagConstraints gbc_txtInterprete = new GridBagConstraints();
		gbc_txtInterprete.insets = new Insets(0, 0, 5, 5);
		gbc_txtInterprete.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInterprete.gridx = 1;
		gbc_txtInterprete.gridy = 2;
		panel__north.add(txtInterprete, gbc_txtInterprete);
		txtInterprete.setColumns(10);
		
		txtTitulo = new JTextField();
		txtTitulo.setText("Titulo");
		GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
		gbc_txtTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitulo.gridx = 3;
		gbc_txtTitulo.gridy = 2;
		panel__north.add(txtTitulo, gbc_txtTitulo);
		txtTitulo.setColumns(10);
		
		txtEstilo = new JTextField();
		txtEstilo.setText("Estilo");
		GridBagConstraints gbc_txtEstilo = new GridBagConstraints();
		gbc_txtEstilo.insets = new Insets(0, 0, 5, 5);
		gbc_txtEstilo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEstilo.gridx = 5;
		gbc_txtEstilo.gridy = 2;
		panel__north.add(txtEstilo, gbc_txtEstilo);
		txtEstilo.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.gridx = 7;
		gbc_btnBuscar.gridy = 2;
		panel__north.add(btnBuscar, gbc_btnBuscar);
		
		JPanel panel__center = new JPanel();
		panel_center.add(panel__center, BorderLayout.CENTER);
		GridBagLayout gbl_panel__center = new GridBagLayout();
		gbl_panel__center.columnWidths = new int[]{15, 0, 15, 0};
		gbl_panel__center.rowHeights = new int[]{84, 78, 80, 0};
		gbl_panel__center.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel__center.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel__center.setLayout(gbl_panel__center);
		
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
				Object Titulo = model.getValueAt(row, 0);
				Object Interprete = model.getValueAt(row, 1);
				model.removeRow(row);
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
				Object Titulo = model_1.getValueAt(row, 0);
				Object Interprete = model_1.getValueAt(row, 1);
				model_1.removeRow(row);
				model.addRow(new Object[] { Titulo, Interprete });
			}
		});
		
		JPanel panel_south = new JPanel();
		panel_center.add(panel_south, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Aceptar");
		panel_south.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
				for(int row = 0;row < model_1.getRowCount();row++) {
					String Titulo = model_1.getValueAt(row, 0).toString();
					String Interprete = model_1.getValueAt(row, 1).toString();
					Controlador controlador = Controlador.getUnicaInstancia();
					Cancion cancion = controlador.getCancionTituloeInter(Titulo, Interprete);
					controlador.añadeCancionPlaylist(textField.getText() , cancion);
				}
			}
		});
		
		
		JButton btnNewButton_3 = new JButton("Cancelar");
		panel_south.add(btnNewButton_3);
		
		JPanel panel__west = new JPanel();
		panel_center.add(panel__west, BorderLayout.WEST);
		
		
		String[] columns = {"Titulo","Interprete"};
		table = new JTable(new DefaultTableModel(columns, 0));	
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollCanciones = new JScrollPane(table); 
		panel__west.add(scrollCanciones);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		//A�adimos inicialmente todas las canciones a la tabla
			List<Cancion> canciones = Controlador.getUnicaInstancia().getAllCanciones();
			for(Cancion c : canciones ) {
				model.addRow(new Object[] { c.getTitulo(), c.getInterprete() });
			}
		
		JPanel panel__east = new JPanel();
		panel_center.add(panel__east, BorderLayout.EAST);
		
		
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
		
		JLabel lblNewLabel_1 = new JLabel("Hola <dynamic>");
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

}

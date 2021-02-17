package umu.tds;

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

import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaExplorar {

	private JFrame frmVentanaPrincipal;
	private JTextField txtInterprete;
	private JTextField txtTitulo;
	private JTable table;
	
	public VentanaExplorar() {
		initialize();
	}
	
	public JFrame getJFrame() {
		return frmVentanaPrincipal;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaExplorar frame = new VentanaExplorar();
					frame.frmVentanaPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void mostrarVentana() {
		frmVentanaPrincipal.setLocationRelativeTo(null);
		frmVentanaPrincipal.setVisible(true);
	}
	
	public void initialize() {
		frmVentanaPrincipal = new JFrame();
		frmVentanaPrincipal.setTitle("AppMusic");
		frmVentanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel contentPane = (JPanel) frmVentanaPrincipal.getContentPane();
		frmVentanaPrincipal.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		frmVentanaPrincipal.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{10, 10, 10, 0, 10, 0, 0, 10, 0};
		gbl_panel.rowHeights = new int[]{10, 0, 20, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Hola "+ Controlador.getUnicaInstancia().getUsuarioActual().getLogin());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnHaztePremium = new JButton("Hazte Premium");
		GridBagConstraints gbc_btnHaztePremium = new GridBagConstraints();
		gbc_btnHaztePremium.insets = new Insets(0, 0, 5, 5);
		gbc_btnHaztePremium.gridx = 5;
		gbc_btnHaztePremium.gridy = 1;
		panel.add(btnHaztePremium, gbc_btnHaztePremium);
		
		JButton btnSalir = new JButton("Salir");
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalir.gridx = 6;
		gbc_btnSalir.gridy = 1;
		panel.add(btnSalir, gbc_btnSalir);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		frmVentanaPrincipal.getContentPane().add(panel_1, BorderLayout.WEST);
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
		frmVentanaPrincipal.getContentPane().add(panel_2, BorderLayout.CENTER);
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
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Estilo","POP","JAZZ","ROCK"}));
		panel_4.add(comboBox);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Buscar");
		panel_5.add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("Cancelar");
		panel_5.add(btnNewButton_3);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6, BorderLayout.CENTER);
		
		table = new JTable();
		panel_6.add(table);
		
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

		//frmVentanaPrincipal.pack();
	}
}
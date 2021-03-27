package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import umu.tds.controlador.Controlador;

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
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal {

	private JFrame frmVentanaPrincipal;
	
	public VentanaPrincipal() {
		initialize();
	}
	
	public JFrame getFrame() {
		return frmVentanaPrincipal;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
		frmVentanaPrincipal.setBounds(100, 100, 483, 368);
		frmVentanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel contentPane = (JPanel) frmVentanaPrincipal.getContentPane();
		frmVentanaPrincipal.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		frmVentanaPrincipal.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{10, 10, 10, 0, 10, 0, 0, 10, 0};
		gbl_panel.rowHeights = new int[]{10, 0, 10, 0};
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
		btnHaztePremium.setBorderPainted(false);
		btnHaztePremium.setBackground(SystemColor.control);
		GridBagConstraints gbc_btnHaztePremium = new GridBagConstraints();
		gbc_btnHaztePremium.insets = new Insets(0, 0, 5, 5);
		gbc_btnHaztePremium.gridx = 5;
		gbc_btnHaztePremium.gridy = 1;
		panel.add(btnHaztePremium, gbc_btnHaztePremium);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBackground(SystemColor.control);
		btnSalir.setBorderPainted(false);
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalir.gridx = 6;
		gbc_btnSalir.gridy = 1;
		panel.add(btnSalir, gbc_btnSalir);
		btnSalir.setActionCommand("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin reg = new VentanaLogin();
				reg.getFrame().setVisible(true);
				frmVentanaPrincipal.setVisible(false);
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		frmVentanaPrincipal.getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JButton btnExplorar = new JButton("Explorar");
		btnExplorar.setBorderPainted(false);
		btnExplorar.setBackground(Color.LIGHT_GRAY);
		btnExplorar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/imagenes/lupa.png")));
		panel_1.add(btnExplorar);
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaExplorar reg = new VentanaExplorar();
				reg.getFrame().setVisible(true);
				frmVentanaPrincipal.setVisible(false);
			}
		});
		
		JButton btnLista = new JButton("Nueva lista");
		btnLista.setBorderPainted(false);
		btnLista.setBackground(Color.LIGHT_GRAY);
		btnLista.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/imagenes/mas-positivo-suma-simbolo-matematico.png")));
		panel_1.add(btnLista);
		
		JButton btnRecientes = new JButton("Reciente");
		btnRecientes.setBorderPainted(false);
		btnRecientes.setBackground(Color.LIGHT_GRAY);
		btnRecientes.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/imagenes/reloj-de-pared.png")));
		panel_1.add(btnRecientes);
		
		JButton btnMisL = new JButton("Mis listas");
		btnMisL.setBorderPainted(false);
		btnMisL.setBackground(Color.LIGHT_GRAY);
		btnMisL.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/imagenes/lista-de-reproduccion.png")));
		panel_1.add(btnMisL);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

		//frmVentanaPrincipal.pack();
	}
	

}
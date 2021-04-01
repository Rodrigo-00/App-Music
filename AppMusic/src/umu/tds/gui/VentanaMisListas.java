package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Playlist;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JList;

public class VentanaMisListas{

	private JFrame frmVentanaMisListas;
	
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
		frmVentanaMisListas.setBounds(100, 100, 483, 368);
		frmVentanaMisListas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
		JButton btnAtrs = new JButton("Atr\u00E1s");
		GridBagConstraints gbc_btnAtrs = new GridBagConstraints();
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
		
		JButton btnMisL = new JButton("Mis listas");
		btnMisL.setBorderPainted(false);
		btnMisL.setBackground(Color.LIGHT_GRAY);
		btnMisL.setIcon(new ImageIcon(VentanaMisListas.class.getResource("/umu/tds/imagenes/lista-de-reproduccion.png")));
		panel_1.add(btnMisL);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		/*JList list_1 = new JList();
		list_1.setBounds(0, 0, 113, 109);
		panel_3.add(list_1);*/
		
		JPanel panel_2 = new JPanel();
		frmVentanaMisListas.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		List<String> listas = Controlador.getUnicaInstancia().nombresListas();
		JList<String> list = new JList(listas.toArray());
		list.setSelectedIndex(0);
		
		list.setBounds(0, 0, 186, 109);
		panel_3.add(list);
		
		JButton btnReproducir = new JButton("Reproducir");
		btnReproducir.setBounds(115, 177, 105, 23);
		panel_2.add(btnReproducir);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		btnReproducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccionada = (String)list.getSelectedValue();
				System.out.println("Playlist a reproducir "+ seleccionada);
				//Faltaría llamar al controlador para reproducir la playlist seleccionada
			}
		});

	
	}
}
package umu.tds.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;

import umu.tds.controlador.Controlador;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;

public class VentanaPremium {

	private JFrame frmVentanaPremium;
	private JTextField txtTexto;

	public JFrame getFrame() {
		return frmVentanaPremium;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPremium window = new VentanaPremium();
					window.frmVentanaPremium.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPremium() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVentanaPremium = new JFrame();
		frmVentanaPremium.setResizable(false);
		frmVentanaPremium.setBounds(100, 100, 583, 368);
		frmVentanaPremium.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmVentanaPremium.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		Object[] anos = {"Años", "1", "2", "3"};
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(173, 255, 110, 23);
		panel.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal reg = new VentanaPrincipal();
				reg.getFrame().setVisible(true);
				frmVentanaPremium.setVisible(false);
			}
		});
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setPreferredSize(new Dimension(75, 23));
		btnConfirmar.setMinimumSize(new Dimension(75, 23));
		btnConfirmar.setMaximumSize(new Dimension(75, 23));
		btnConfirmar.setBounds(333, 255, 110, 23);
		panel.add(btnConfirmar);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaConfirmacionPremium reg = new VentanaConfirmacionPremium();
				reg.getFrame().setVisible(true);
				frmVentanaPremium.setVisible(false);
			}
		});
		
		txtTexto = new JTextField();
		txtTexto.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		txtTexto.setHorizontalAlignment(SwingConstants.CENTER);
		txtTexto.setEditable(false);
		txtTexto.setFocusable(false);
		txtTexto.setSelectionColor(Color.BLACK);
		txtTexto.setForeground(Color.BLACK);
		txtTexto.setCaretColor(Color.WHITE);
		txtTexto.setBackground(Color.YELLOW);
		txtTexto.setText("¡Conviertete en PREMIUM " + Controlador.getUnicaInstancia().getLoginUsuario()+ "!");
		txtTexto.setBounds(114, 64, 383, 33);
		panel.add(txtTexto);
		txtTexto.setColumns(10);
		
		JLabel lblAppMusic = new JLabel("APP MUSIC");
		lblAppMusic.setHorizontalAlignment(SwingConstants.CENTER);
		lblAppMusic.setFont(new Font("Viner Hand ITC", Font.BOLD, 22));
		lblAppMusic.setBounds(173, 17, 270, 36);
		panel.add(lblAppMusic);
		
		JLabel lblNewLabel_1 = new JLabel("Precio premium: "+ Controlador.PRECIO + "€");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel_1.setBounds(173, 150, 270, 57);
		panel.add(lblNewLabel_1);
		
		double descuento = Controlador.getUnicaInstancia().consultarDescuento();
		if(descuento > 0) {
			int des = (int) (descuento *100);
			JLabel lblNewLabel = new JLabel("¡Si decides pagar ahora dipondrá de un " + des + "% de DESCUENTO!");
			lblNewLabel.setFont(new Font("Nirmala UI", Font.PLAIN, 14));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(61, 108, 491, 20);
			panel.add(lblNewLabel);
		}
		
	}
}

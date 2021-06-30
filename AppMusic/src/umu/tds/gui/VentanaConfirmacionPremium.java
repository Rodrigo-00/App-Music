package umu.tds.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import umu.tds.controlador.Controlador;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class VentanaConfirmacionPremium {

	private JFrame frame;
	private JTextField txtgracias;
	private int anos;	//Guardamos los a�os de premium seleccionados

	/**
	 * Create the application.
	 */
	
	public JFrame getFrame() {
		return frame;
	}
	
	public VentanaConfirmacionPremium(int anos) {
		this.anos = anos;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblAppMusic = new JLabel("APP MUSIC");
		lblAppMusic.setHorizontalAlignment(SwingConstants.CENTER);
		lblAppMusic.setFont(new Font("Viner Hand ITC", Font.BOLD, 22));
		lblAppMusic.setBounds(82, 11, 270, 36);
		panel.add(lblAppMusic);
		
		txtgracias = new JTextField();
		txtgracias.setHorizontalAlignment(SwingConstants.CENTER);
		txtgracias.setFont(new Font("Sitka Heading", Font.PLAIN, 20));
		txtgracias.setEditable(false);
		txtgracias.setFocusable(false);
		txtgracias.setBackground(Color.CYAN);
		txtgracias.setText("¡GRACIAS!");
		txtgracias.setBounds(106, 58, 204, 48);
		panel.add(txtgracias);
		txtgracias.setColumns(10);
		
		double cantidad = Controlador.getUnicaInstancia().convertirPremium(anos);
		JLabel lblCantidadPagada = new JLabel("Cantidad pagada: "+ cantidad + "€");
		lblCantidadPagada.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCantidadPagada.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidadPagada.setBounds(106, 144, 204, 14);
		panel.add(lblCantidadPagada);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(167, 190, 89, 23);
		panel.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal reg = new VentanaPrincipal();
				reg.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		
		
	}
}

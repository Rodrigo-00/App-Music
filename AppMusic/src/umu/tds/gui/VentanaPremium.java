package umu.tds.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
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

public class VentanaPremium {

	private JFrame frmVentanaPremium;
	private JTextField txtTexto;
	private JComboBox comboBox;

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
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(173, 205, 110, 23);
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
		btnConfirmar.setBounds(333, 205, 110, 23);
		panel.add(btnConfirmar);
		
		txtTexto = new JTextField();
		txtTexto.setHorizontalAlignment(SwingConstants.CENTER);
		txtTexto.setSelectionColor(Color.BLACK);
		txtTexto.setForeground(Color.BLACK);
		txtTexto.setCaretColor(Color.WHITE);
		txtTexto.setBackground(Color.YELLOW);
		txtTexto.setText("\u00BFCuanto tiempo deseas ser PREMIUM?");
		txtTexto.setBounds(173, 64, 270, 20);
		panel.add(txtTexto);
		txtTexto.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(256, 108, 99, 22);
		Object[] años = {"Años", "1 año", "2 años", "3 años"};
		comboBox.setModel(new DefaultComboBoxModel(años));
		panel.add(comboBox);
		
		/*
		panel.add(comboBox);*/
		
	}
}

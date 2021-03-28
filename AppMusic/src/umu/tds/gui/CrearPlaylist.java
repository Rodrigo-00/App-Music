package umu.tds.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import umu.tds.controlador.Controlador;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class CrearPlaylist {

	private JFrame frmCrearPlaylist;
	private JTextField textField;

	public JFrame getFrame() {
		return frmCrearPlaylist;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearPlaylist window = new CrearPlaylist();
					window.frmCrearPlaylist.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CrearPlaylist() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrearPlaylist = new JFrame();
		frmCrearPlaylist.setBounds(100, 100, 450, 300);
		frmCrearPlaylist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmCrearPlaylist.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 23, 0, 72, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.gridx = 3;
		gbc_lblNombre.gridy = 2;
		panel.add(lblNombre, gbc_lblNombre);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 2;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 4;
		gbc_btnCancelar.gridy = 7;
		panel.add(btnCancelar, gbc_btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal reg = new VentanaPrincipal();
				reg.getFrame().setVisible(true);
				frmCrearPlaylist.setVisible(false);
			}
		});
		
		JButton btnCrear = new JButton("Crear");
		GridBagConstraints gbc_btnCrear = new GridBagConstraints();
		gbc_btnCrear.insets = new Insets(0, 0, 0, 5);
		gbc_btnCrear.gridx = 5;
		gbc_btnCrear.gridy = 7;
		panel.add(btnCrear, gbc_btnCrear);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre=textField.getText();
				boolean reply = Controlador.getUnicaInstancia().crearPlaylist(nombre);
				if(reply == false) {
					JOptionPane.showMessageDialog(btnCrear, "Ya existe una lista con ese nombre", "Lista existente", JOptionPane.ERROR_MESSAGE, null);
				}else {
					JOptionPane.showMessageDialog(btnCrear,"Playlist creada correctamente", "Mensaje", JOptionPane.PLAIN_MESSAGE);
					VentanaPrincipal reg = new VentanaPrincipal();
					reg.getFrame().setVisible(true);
					frmCrearPlaylist.setVisible(false);
				}
			}
		});
	}
}

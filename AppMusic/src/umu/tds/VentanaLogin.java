package umu.tds;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import umu.tds.controlador.Controlador;
import umu.tds.dominio.CatalogoUsuarios;
import umu.tds.modelo.AppMusic;

import java.awt.Font;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;

public class VentanaLogin {

	private JFrame frmAppmusic;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin window = new VentanaLogin();
					window.frmAppmusic.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAppmusic = new JFrame();
		frmAppmusic.setTitle("AppMusic");
		frmAppmusic.setBounds(100, 100, 450, 300);
		frmAppmusic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblAppMusic = new JLabel("APP MUSIC");
		lblAppMusic.setFont(new Font("Viner Hand ITC", Font.BOLD, 22));
		lblAppMusic.setHorizontalAlignment(SwingConstants.CENTER);
		frmAppmusic.getContentPane().add(lblAppMusic, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frmAppmusic.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(300, 200));
		panel.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{20, 0, 153, 153, 0, 0};
		gbl_panel_1.rowHeights = new int[]{20, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblUsuario = new JLabel("Usuario");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 1;
		panel_1.add(lblUsuario, gbc_lblUsuario);
		
		textUsuario = new JTextField();
		GridBagConstraints gbc_textUsuario = new GridBagConstraints();
		gbc_textUsuario.gridwidth = 2;
		gbc_textUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textUsuario.gridx = 2;
		gbc_textUsuario.gridy = 1;
		panel_1.add(textUsuario, gbc_textUsuario);
		textUsuario.setColumns(20);
		
		JLabel lblClave = new JLabel("Clave:");
		GridBagConstraints gbc_lblClave = new GridBagConstraints();
		gbc_lblClave.anchor = GridBagConstraints.EAST;
		gbc_lblClave.insets = new Insets(0, 0, 5, 5);
		gbc_lblClave.gridx = 1;
		gbc_lblClave.gridy = 2;
		panel_1.add(lblClave, gbc_lblClave);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 2;
		panel_1.add(passwordField, gbc_passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario=textUsuario.getText();
				String clave = new String(passwordField.getPassword());
				 
				boolean logueado = AppMusic.getInstancia().login(usuario, clave);

				if(!logueado) {
					JOptionPane.showMessageDialog(btnLogin, "Identificador " + usuario +" incorrecto", "Login incorrecto", JOptionPane.ERROR_MESSAGE, null);
				}
				else {
					VentanaPrincipal reg = new VentanaPrincipal();
					reg.getFrmVentanaPrincipal().setVisible(true);
					frmAppmusic.setVisible(false);
				}
				
			}
		});
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 2;
		gbc_btnLogin.gridy = 3;
		panel_1.add(btnLogin, gbc_btnLogin);
		
		//implementado 
		JButton btnRegister = new JButton("Registrarse");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventanaregistro reg = new Ventanaregistro();
				reg.setVisible(true);
				frmAppmusic.setVisible(false);
			}
		});
		//hasta aqui
		
		GridBagConstraints gbc_btnRegister = new GridBagConstraints();
		gbc_btnRegister.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRegister.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegister.gridx = 3;
		gbc_btnRegister.gridy = 3;
		panel_1.add(btnRegister, gbc_btnRegister);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	public JFrame getFrame() {
		return frmAppmusic;
	}
	
}

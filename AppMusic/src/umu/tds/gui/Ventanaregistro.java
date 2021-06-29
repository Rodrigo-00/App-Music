package umu.tds.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import umu.tds.controlador.Controlador;

import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class Ventanaregistro extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textUsuario;
	private JPasswordField textClave;
	private JPasswordField textClave2;
	private JTextField textEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventanaregistro frame = new Ventanaregistro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventanaregistro() {
		setTitle("AppMusic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{20, 0, 0, 0, 20, 20, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		panel.add(lblNombre, gbc_lblNombre);
		
		textNombre = new JTextField();
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.gridx = 2;
		gbc_textNombre.gridy = 1;
		panel.add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblApellidos.gridx = 1;
		gbc_lblApellidos.gridy = 2;
		panel.add(lblApellidos, gbc_lblApellidos);
		
		textApellidos = new JTextField();
		GridBagConstraints gbc_textApellidos = new GridBagConstraints();
		gbc_textApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_textApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellidos.gridx = 2;
		gbc_textApellidos.gridy = 2;
		panel.add(textApellidos, gbc_textApellidos);
		textApellidos.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		GridBagConstraints gbc_lblFechaDeNacimiento = new GridBagConstraints();
		gbc_lblFechaDeNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeNacimiento.gridx = 1;
		gbc_lblFechaDeNacimiento.gridy = 3;
		panel.add(lblFechaDeNacimiento, gbc_lblFechaDeNacimiento);
		
		JDateChooser dateNacim = new JDateChooser();
		GridBagConstraints gbc_dateNacim = new GridBagConstraints();
		gbc_dateNacim.insets = new Insets(0, 0, 5, 5);
		gbc_dateNacim.fill = GridBagConstraints.BOTH;
		gbc_dateNacim.gridx = 2;
		gbc_dateNacim.gridy = 3;
		panel.add(dateNacim, gbc_dateNacim);
		
		JLabel textlUsuario = new JLabel("Usuario");
		GridBagConstraints gbc_textlUsuario = new GridBagConstraints();
		gbc_textlUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textlUsuario.anchor = GridBagConstraints.EAST;
		gbc_textlUsuario.gridx = 1;
		gbc_textlUsuario.gridy = 4;
		panel.add(textlUsuario, gbc_textlUsuario);
		
		textUsuario = new JTextField();
		GridBagConstraints gbc_textUsuario = new GridBagConstraints();
		gbc_textUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textUsuario.gridx = 2;
		gbc_textUsuario.gridy = 4;
		panel.add(textUsuario, gbc_textUsuario);
		textUsuario.setColumns(15);
		
		JLabel sss = new JLabel("Clave");
		GridBagConstraints gbc_sss = new GridBagConstraints();
		gbc_sss.anchor = GridBagConstraints.EAST;
		gbc_sss.insets = new Insets(0, 0, 5, 5);
		gbc_sss.gridx = 1;
		gbc_sss.gridy = 5;
		panel.add(sss, gbc_sss);
		
		textClave = new JPasswordField();
		GridBagConstraints gbc_textClave = new GridBagConstraints();
		gbc_textClave.fill = GridBagConstraints.HORIZONTAL;
		gbc_textClave.insets = new Insets(0, 0, 5, 5);
		gbc_textClave.gridx = 2;
		gbc_textClave.gridy = 5;
		panel.add(textClave, gbc_textClave);
		
		JLabel ssss2 = new JLabel("Repetir");
		GridBagConstraints gbc_ssss2 = new GridBagConstraints();
		gbc_ssss2.anchor = GridBagConstraints.EAST;
		gbc_ssss2.insets = new Insets(0, 0, 5, 5);
		gbc_ssss2.gridx = 3;
		gbc_ssss2.gridy = 5;
		panel.add(ssss2, gbc_ssss2);
		
		textClave2 = new JPasswordField();
		GridBagConstraints gbc_textClave2 = new GridBagConstraints();
		gbc_textClave2.insets = new Insets(0, 0, 5, 5);
		gbc_textClave2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textClave2.gridx = 4;
		gbc_textClave2.gridy = 5;
		panel.add(textClave2, gbc_textClave2);
		
		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 6;
		panel.add(lblEmail, gbc_lblEmail);
		
		textEmail = new JTextField();
		GridBagConstraints gbc_textEmail = new GridBagConstraints();
		gbc_textEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEmail.gridx = 2;
		gbc_textEmail.gridy = 6;
		panel.add(textEmail, gbc_textEmail);
		textEmail.setColumns(10);
		
		JButton btnAtrs = new JButton("Atr\u00E1s");
		GridBagConstraints gbc_btnAtrs = new GridBagConstraints();
		gbc_btnAtrs.anchor = GridBagConstraints.EAST;
		gbc_btnAtrs.insets = new Insets(0, 0, 5, 5);
		gbc_btnAtrs.gridx = 2;
		gbc_btnAtrs.gridy = 8;
		panel.add(btnAtrs, gbc_btnAtrs);
		btnAtrs.setActionCommand("Atras");
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin reg = new VentanaLogin();
				reg.getFrame().setVisible(true);
				setVisible(false);
			}
		});
		
		JButton btnRegistrar = new JButton("Aceptar");
		btnRegistrar.setActionCommand("Aceptar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String usuario = textUsuario.getText();
				String clave1 = new String(textClave.getPassword());
				String clave2 = new String(textClave2.getPassword());
				
				String nombre = new String(textNombre.getText());
				String email = new String(textEmail.getText());
				String apellidos = new String(textApellidos.getText());
				
				
				boolean correcto = entradaCorrecta(nombre, apellidos,dateNacim.getDate(), clave1, clave2, email, btnRegistrar);
				if(!correcto) return;
				
				
						
				//Convertimos la fecha al formato deseado
				String fechaNacim = new SimpleDateFormat("dd/MM/yyyy").format(dateNacim.getDate());
				Date date = null;
				try {
					date = new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacim);	
					System.out.println(date.getTime());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//Comprobamos que el nombre de usuario no sea un campo vacío
				if(usuario.equals("")) {
					JOptionPane.showMessageDialog(btnRegistrar, "Inserte un usuario válido", "Usuario incorrecto", JOptionPane.ERROR_MESSAGE, null);
					return;
				}
				
				
				//Faltan mas cosas
				if(!Controlador.getUnicaInstancia().registrarUsuario(nombre, apellidos, email, usuario, clave1, date)) {
					JOptionPane.showMessageDialog(btnRegistrar, "Nombre de usuario ya registrado", "Registro incorrecto", JOptionPane.ERROR_MESSAGE, null);
					return;
				}
				VentanaLogin reg = new VentanaLogin();
				reg.getFrame().setVisible(true);
				setVisible(false);
			}
		});
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.anchor = GridBagConstraints.WEST;
		gbc_btnRegistrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistrar.gridx = 4;
		gbc_btnRegistrar.gridy = 8;
		panel.add(btnRegistrar, gbc_btnRegistrar);
		
		JLabel lblBienvenidoAApp = new JLabel("BIENVENIDO A APP MUSIC");
		lblBienvenidoAApp.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenidoAApp.setFont(new Font("Viner Hand ITC", Font.BOLD, 22));
		contentPane.add(lblBienvenidoAApp, BorderLayout.NORTH);
	}
	
	private boolean entradaCorrecta(String nombre, String apellidos, Date fecha, String clave1, String clave2, String email, JButton btnRegistrar) {
		
		//Comprobamos que el campo nombre no esta vacío
		if(nombre.equals("")) {
			JOptionPane.showMessageDialog(btnRegistrar, "Inserte un nombre", "Nombre incorrecto", JOptionPane.ERROR_MESSAGE, null);
			return false;
		}
		
		
		//Comprobamos que el campo apellidos no esta vacío
		if(apellidos.equals("")) {
			JOptionPane.showMessageDialog(btnRegistrar, "Inserte sus apellidos", "Apellido incorrecto", JOptionPane.ERROR_MESSAGE, null);
			return false;
		}
		
		
		//Comprobamos que la fecha indicada es la correcta
		if(fecha == null || fecha.after(Calendar.getInstance().getTime())) {
			JOptionPane.showMessageDialog(btnRegistrar, "Inserte una fecha de nacimiento correcta", "Fecha incorrecta", JOptionPane.ERROR_MESSAGE, null);
			return false;
		}

		//Comprobamos que la contraseña no este vacía
		if(clave1.equals("")) {
			JOptionPane.showMessageDialog(btnRegistrar, "Inserte una contraseña válida", "Contraseña incorrecta", JOptionPane.ERROR_MESSAGE, null);
			return false;
		}
		
		
		//Comprobamos que las contraseñas sean iguales
		if(!clave1.equals(clave2)) {
			JOptionPane.showMessageDialog(btnRegistrar, "La contraseña no coincide", "Registro incorrecto", JOptionPane.ERROR_MESSAGE, null);
			return false;
		}
		
		//Comprobamos que email no este vacío
		if(email.equals("")) {
			JOptionPane.showMessageDialog(btnRegistrar, "Inserte una email válido", "Email incorrecto", JOptionPane.ERROR_MESSAGE, null);
			return false;
		}		
		
		return true;
	}

}

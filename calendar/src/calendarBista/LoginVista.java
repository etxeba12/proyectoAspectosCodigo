package calendarBista;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JSeparator;
import javax.swing.ImageIcon;

import calendarModelo.ConsultasDBModelo;
import exceptions.ExceptionModificable;


public class LoginVista extends JFrame {
	
		
	private JPanel login; //para crear el panel principal
	private Color colorBlanco = new Color(255,255,255); //definir color de atras
	private Color color1 = new Color(231, 231, 231); //definir color de panel atras
	private Color azulCielo = new Color(31, 197, 203);
	private JLabel bienvenidaLbl = null;
	private JLabel usuarioLbl = null;
	private JTextField usuarioTF;
	private JLabel contrase�aLbl = null;
	private JTextField contrase�aTF;
	private JSeparator separador;
	private JSeparator separador_1;
	private JButton botonRegistro;
	private JButton botonLogin;
	private JPanel parteArriba;
	
	private ConsultasDBModelo r = new ConsultasDBModelo();
	
	private static LoginVista miSesionlogin;
	private JPanel panelBotones;

	//Eraikitzailea
	
	private LoginVista() {
		setTitle("login"); //titulo de la pagina
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //que hacer en caso de cerrar la pesta�a
		setBounds(120, 120, 500, 300); // definimos tama�o del panel a mano
		login = new JPanel();
		this.login.setBackground(this.color1); //definimos color de fondo
		login.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(login); //colocamos el panel dentro de la ventana principal.
		this.login.setLayout(null); //asi colocamos las cosas donde nosotros queremos
		login.add(getMeterUsuario());
		this.login.add(getUsuarioTF());
		this.login.add(getSeparador());
		login.add(getContrase�a());
		login.add(getContrase�aTF());
		{
			
			this.parteArriba = new JPanel();
			this.parteArriba.setBounds(0, 5, 500, 35);
			Border borde = BorderFactory.createLineBorder(Color.black, 1);
			this.parteArriba.setBorder(borde);
			this.parteArriba.setBackground(azulCielo);
			this.parteArriba.setLayout(null);
			this.parteArriba.add(getBienvenida());
			this.login.add(parteArriba);
			
			//// boton registrarse y registro ////
			
			this.panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT)); // crear un panel horizontal para poner botones seguidos
			this.panelBotones.setBackground(this.color1); // ponemos mismo color para que no se note el panel
			this.panelBotones.setBounds(150, 213, 200, 52);
			this.login.add(this.panelBotones); // a�adimos el Panel al principal panel
			this.panelBotones.add(getBotonRegistrarse());
			Component espacio = Box.createRigidArea(new Dimension(6, 0)); //creamos el espacio
			this.panelBotones.add(espacio);
			this.panelBotones.add(getBotonLogin());
			
		}
		this.login.add(getSeparador1()); //poner linea debajo del TF		
		setLocationRelativeTo(null);
	}
	
	public static LoginVista getLogin() {
		if(miSesionlogin == null) {
			miSesionlogin = new LoginVista();
		}
		return miSesionlogin;
	}
	
	
	public static void main(String[] args) {
		LoginVista lg = getLogin();
		lg.setVisible(true);
	}
	
	////////////// creamos los labels y los TF //////////////
	
	public JLabel getBienvenida() { //si no se ha creado la etiqueta todavia, la creamos
		if(bienvenidaLbl == null) {
			bienvenidaLbl = new JLabel("�LOGEATE!");
			bienvenidaLbl.setBounds(195, -40, 105, 120);
			bienvenidaLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
			bienvenidaLbl.setForeground(colorBlanco);
			
		}
		return bienvenidaLbl;
	}
	
	public JLabel getMeterUsuario() { //si no se ha creado la etiqueta todavia, la creamos
		if(usuarioLbl == null) {
			usuarioLbl = new JLabel("Nombre de usuario:");
			usuarioLbl.setBounds(180, 64, 150, 14); //x,y, longitud, altura
			usuarioLbl.setForeground(azulCielo);
		}
		return usuarioLbl;
	}
	
	private JTextField getUsuarioTF() {
		if(usuarioTF == null) {
			usuarioTF = new JTextField();
			usuarioTF.setBounds(110, 93, 250, 14);
			usuarioTF.setToolTipText(""); //para que este vacio
			usuarioTF.setForeground(Color.BLACK);
			usuarioTF.setBorder(null);
			usuarioTF.setBackground(this.colorBlanco);
			 
		}
		return usuarioTF;
	}
	
	public JLabel getContrase�a() { //si no se ha creado la etiqueta todavia, la creamos
		if(contrase�aLbl == null) {
			contrase�aLbl = new JLabel("Introducir contrase�a:");
			contrase�aLbl.setBounds(175, 122, 150, 14);
			contrase�aLbl.setForeground(azulCielo);
		}
		return contrase�aLbl;
	}
	
	private JTextField getContrase�aTF() {
		if(contrase�aTF == null) {
			contrase�aTF = new JTextField();
			contrase�aTF.setBounds(110, 151, 250, 14);
			contrase�aTF.setToolTipText(""); //para que este vacio
			contrase�aTF.setForeground(Color.BLACK);
			contrase�aTF.setBorder(null);
			contrase�aTF.setBackground(this.colorBlanco);
		}
		return contrase�aTF;
	}
	
	////////////// creamos los labels y los TF //////////////
	
	////////////// ponemos rayas debajo de los TF //////////////
	
	private JSeparator getSeparador() {
		if (separador == null) {
			separador = new JSeparator();
			separador.setBounds(110, 109, 250, 14);
			separador.setForeground(this.azulCielo);
			separador.setBackground(Color.BLACK);
		}
		return separador;
	}
	
	private JSeparator getSeparador1() {
		if (separador_1 == null) {
			separador_1 = new JSeparator();
			separador_1.setBounds(110, 167, 250, 14);
			separador_1.setForeground(this.azulCielo);
			separador_1.setBackground(Color.BLACK);
		}
		return separador_1;
	}
	
	//////////////ponemos rayas debajo de los TF //////////////
	
	////////////// botones //////////////
	
	private JButton getBotonLogin() {
		if(botonLogin == null) {
			botonLogin = new JButton();
			botonLogin.setBackground(this.azulCielo);
			botonLogin.setForeground(colorBlanco);
			Border borde = BorderFactory.createLineBorder(Color.black, 1);
			botonLogin.setBorder(borde);
			botonLogin.setText(" ENTRAR ");
			botonLogin.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						if(usuarioTF.getText().length() != 0 && contrase�aTF.getText().length() != 0) {
							if(r.loginValido(usuarioTF.getText(),contrase�aTF.getText())){
								System.out.println("accediendo");
								setVisible(false);
								EntrenoDiarioVista ev = EntrenoDiarioVista.getMiEntreno("2023-09-22");
								ev.setVisible(true);
							}else {
								throw new ExceptionModificable(login, "�El usuario y/o contrase�a incorrectos!");
							}
						}else {
							throw new ExceptionModificable(login, "�El usuario y/o la contrase�a no pueden estar vacios!");
						}
					}catch(ExceptionModificable se) {
						usuarioTF.setText("");
						contrase�aTF.setText("");
						se.imprimirMensaje();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return botonLogin;
	}
	
	private JButton getBotonRegistrarse() {
		if(botonRegistro == null) {
			botonRegistro = new JButton();
			botonRegistro.setBackground(this.azulCielo);
			botonRegistro.setForeground(colorBlanco);
			Border borde = BorderFactory.createLineBorder(Color.black, 1); // creamos el borde del boton
			botonRegistro.setBorder(borde);
			botonRegistro.setText(" REGISTRATE ");
			botonRegistro.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					setVisible(false);
					RegistroVista rg = RegistroVista.getMiregistro();
					rg.setVisible(true);
				}
			});
		}
		return botonRegistro;
	}
	
	//////////////botones //////////////
}

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


public class RegistroVista extends JFrame {
	
		
	private JPanel registro; //para crear el panel principal
	private Color colorBlanco = new Color(255,255,255); //definir color de atras
	private Color color1 = new Color(231, 231, 231); //definir color de panel atras
	private JLabel bienvenidaLbl = null;
	private JLabel usuarioLbl = null;
	private JTextField usuarioTF;
	private JLabel contrase�aLbl = null;
	private JTextField contrase�aTF;
	private JLabel contrase�a_2_Lbl = null;
	private JTextField contrase�a_2_TF; // para repetir la contrase�a
	private JSeparator separador;
	private JSeparator separador_1;
	private JSeparator separador_2;
	private JButton botonRegistro;
	private JButton botonLogin;
	
	private ConsultasDBModelo r = new ConsultasDBModelo();
	
	private static RegistroVista miSesionregistro;
	private JPanel panelBotones;

	//Eraikitzailea
	
	private RegistroVista() {
		setTitle("registro"); //titulo de la pagina
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //que hacer en caso de cerrar la pesta�a
		setBounds(120, 120, 500, 300); // definimos tama�o del panel a mano
		registro = new JPanel();
		this.registro.setBackground(this.color1); //definimos color de fondo
		registro.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(registro); //colocamos el panel dentro de la ventana principal.
		this.registro.setLayout(null); //asi colocamos las cosas donde nosotros queremos
		registro.add(getBienvenida());
		registro.add(getMeterUsuario());
		this.registro.add(getUsuarioTF());
		this.registro.add(getSeparador());
		registro.add(getContrase�a());
		registro.add(getContrase�aTF());
		{
			//// boton registrarse y registro ////
			
			this.panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT)); // crear un panel horizontal para poner botones seguidos
			this.panelBotones.setBackground(this.color1); // ponemos mismo color para que no se note el panel
			this.panelBotones.setBounds(150, 213, 200, 52);
			this.registro.add(this.panelBotones); // a�adimos el Panel al principal panel
			this.panelBotones.add(getBotonLogearse());
			Component espacio = Box.createRigidArea(new Dimension(6, 0)); //creamos el espacio
			this.panelBotones.add(espacio);
			this.panelBotones.add(getBotonRegistro());
			
		}
		this.registro.add(getSeparador1()); //poner linea debajo del TF		
		registro.add(getContrase�a_2());
		this.registro.add(getContrase�aTF_2());
		this.registro.add(getSeparador2());
		setLocationRelativeTo(null);
	}
	
	public static RegistroVista getMiregistro() {
		if(miSesionregistro == null) {
			miSesionregistro = new RegistroVista();
		}
		return miSesionregistro;
	}
	
	
	////////////// creamos los labels y los TF //////////////
	
	public JLabel getBienvenida() { //si no se ha creado la etiqueta todavia, la creamos
		if(bienvenidaLbl == null) {
			bienvenidaLbl = new JLabel("�REGISTRATE!");
			bienvenidaLbl.setBounds(185, -40, 105, 120);
			bienvenidaLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
			
		}
		return bienvenidaLbl;
	}
	
	public JLabel getMeterUsuario() { //si no se ha creado la etiqueta todavia, la creamos
		if(usuarioLbl == null) {
			usuarioLbl = new JLabel("Nombre de usuario:");
			usuarioLbl.setBounds(180, 39, 150, 14); //x,y, longitud, altura
		}
		return usuarioLbl;
	}
	
	private JTextField getUsuarioTF() {
		if(usuarioTF == null) {
			usuarioTF = new JTextField();
			usuarioTF.setBounds(110, 68, 250, 14);
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
			contrase�aLbl.setBounds(175, 97, 150, 14);
		}
		return contrase�aLbl;
	}
	
	private JTextField getContrase�aTF() {
		if(contrase�aTF == null) {
			contrase�aTF = new JTextField();
			contrase�aTF.setBounds(110, 126, 250, 14);
			contrase�aTF.setToolTipText(""); //para que este vacio
			contrase�aTF.setForeground(Color.BLACK);
			contrase�aTF.setBorder(null);
			contrase�aTF.setBackground(this.colorBlanco);
		}
		return contrase�aTF;
	}
	
	public JLabel getContrase�a_2() { //si no se ha creado la etiqueta todavia, la creamos
		if(contrase�a_2_Lbl == null) {
			contrase�a_2_Lbl = new JLabel("Repetir contrase�a:");
			contrase�a_2_Lbl.setBounds(180, 155, 150, 14);
		}
		return contrase�a_2_Lbl;
	}
	
	private JTextField getContrase�aTF_2() {
		if(contrase�a_2_TF == null) {
			contrase�a_2_TF = new JTextField();
			contrase�a_2_TF.setBounds(110, 184, 250, 14);
			contrase�a_2_TF.setToolTipText(""); //para que este vacio
			contrase�a_2_TF.setForeground(Color.BLACK);
			contrase�a_2_TF.setBorder(null);
			contrase�a_2_TF.setBackground(this.colorBlanco);
		}
		return contrase�a_2_TF;
	}
	
	////////////// creamos los labels y los TF //////////////
	
	////////////// ponemos rayas debajo de los TF //////////////
	
	private JSeparator getSeparador() {
		if (separador == null) {
			separador = new JSeparator();
			separador.setBounds(110, 84, 250, 14);
			separador.setForeground(this.colorBlanco);
			separador.setBackground(Color.BLACK);
		}
		return separador;
	}
	
	private JSeparator getSeparador1() {
		if (separador_1 == null) {
			separador_1 = new JSeparator();
			separador_1.setBounds(110, 142, 250, 14);
			separador_1.setForeground(this.colorBlanco);
			separador_1.setBackground(Color.BLACK);
		}
		return separador_1;
	}
	
	private JSeparator getSeparador2() {
		if (separador_2 == null) {
			separador_2 = new JSeparator();
			separador_2.setBounds(110, 200, 250, 14);
			separador_2.setForeground(this.colorBlanco);
			separador_2.setBackground(Color.BLACK);
		}
		return separador_2;
	}
	
	//////////////ponemos rayas debajo de los TF //////////////
	
	////////////// botones //////////////
	
	private JButton getBotonRegistro() {
		if(botonRegistro == null) {
			botonRegistro = new JButton();
			botonRegistro.setBackground(this.colorBlanco);
			Border borde = BorderFactory.createLineBorder(Color.black, 1);
			botonRegistro.setBorder(borde);
			botonRegistro.setText(" REGISTRARSE ");
			botonRegistro.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						if(usuarioTF.getText().length() != 0 && contrase�aTF.getText().length() != 0 && contrase�a_2_TF.getText().length() != 0) {
							if(!r.comprobarUsuario(usuarioTF.getText())){
								if (contrase�aTF.getText().equals(contrase�a_2_TF.getText())){
									int a = r.Guardar((String) usuarioTF.getText(),(String) contrase�aTF.getText());
								}else {
									throw new ExceptionModificable(registro, "Las contrase�as no coinciden");
								}
							}else {
								throw new ExceptionModificable(registro, "El usuario ya existe!");
							}
						}else {
							throw new ExceptionModificable(registro, "El usuario y/o contrase�as no pueden estar vacios!");
						}
					}catch(ExceptionModificable se) {
						se.imprimirMensaje();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return botonRegistro;
	}
	
	private JButton getBotonLogearse() {
		if(botonLogin == null) {
			botonLogin = new JButton();
			botonLogin.setBackground(this.colorBlanco);
			Border borde = BorderFactory.createLineBorder(Color.black, 1); // creamos el borde del boton
			botonLogin.setBorder(borde);
			botonLogin.setText(" LOGEARSE ");
			botonLogin.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					setVisible(false);
					LoginVista lg = LoginVista.getLogin();
					lg.setVisible(true);
				}
			});
		}
		return botonLogin;
	}
	
	//////////////botones //////////////
}

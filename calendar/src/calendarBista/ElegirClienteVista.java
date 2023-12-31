package calendarBista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import calendarModelo.ConsultasDBModelo;

public class ElegirClienteVista extends JFrame {

	private JPanel contentPane;

	private Color colorBlanco = new Color(255,255,255); //definir color de atras
	private Color color1 = new Color(231, 231, 231); //definir color de panel atras
	private Color azulCielo = new Color(31, 197, 203);
	private JLabel bienvenidaLbl;
	private JPanel panelRadioBotones;
	private ConsultasDBModelo db = new ConsultasDBModelo();
	private static Map<String, ElegirClienteVista>  hashSingleton = new HashMap<>();
	private JButton logoutButton;
	private String usuarioNombre;
	private JPanel elegirCliente;
	private JPanel tabla;

	private JPanel parteArriba;
	private JRadioButton rdbtnNewRadioButton;
	
	private static ElegirClienteVista e;

	public static ElegirClienteVista getElegirClienteVista(String pUsuario) throws SQLException {
		if(hashSingleton.get(pUsuario) == null){
        	e= new ElegirClienteVista(pUsuario);
        	hashSingleton.put(pUsuario, e );
		}
		e = hashSingleton.get(pUsuario);
		return e;
	}
	
	private  ElegirClienteVista(String pUsuario) throws SQLException {
		setTitle("ELEGIR CLIENTE"); //titulo de la pagina
		this.usuarioNombre = pUsuario;
		System.out.println(pUsuario);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //que hacer en caso de cerrar la pestana
		setBounds(120, 120, 500, 300); // definimos tamano del panel a mano
		elegirCliente = new JPanel();
		this.elegirCliente.setBackground(this.color1); //definimos color de fondo
		elegirCliente.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(elegirCliente); //colocamos el panel dentro de la ventana principal.
		this.elegirCliente.setLayout(null); //asi colocamos las cosas donde nosotros queremos
		{
			
			this.parteArriba = new JPanel();
			this.parteArriba.setBounds(0, 5, 500, 35);
			Border borde = BorderFactory.createLineBorder(Color.black, 1);
			this.parteArriba.setBorder(borde);
			this.parteArriba.setBackground(azulCielo);
			this.parteArriba.setLayout(null);
			this.parteArriba.add(getBienvenida());
			this.parteArriba.add(getlogoutButton());
			
			
			//// boton registrarse y registro ////
			
			
			
		}
		this.elegirCliente.add(parteArriba);
		this.elegirCliente.add(getClientes());
		setLocationRelativeTo(null);
	}
	
	public JLabel getBienvenida() { //si no se ha creado la etiqueta todavia, la creamos
		if(bienvenidaLbl == null) {
			bienvenidaLbl = new JLabel("Selecciona un cliente");
			bienvenidaLbl.setBounds(160, -40, 220, 120);
			bienvenidaLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
			bienvenidaLbl.setForeground(colorBlanco);
			
		}
		return bienvenidaLbl;
	}
	
	   private JButton getlogoutButton() {
			if(logoutButton == null) {
				logoutButton = new JButton();
				logoutButton.setBackground(this.azulCielo);
				logoutButton.setBorder(null);
				logoutButton.setBounds(430, 5, 50, 25);
				ImageIcon Original = new  ImageIcon(ElegirClienteVista.class.getResource("/imagenes/logOut.png"));
				Image imagenRedimensionada = Original.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
				logoutButton.setIcon(new ImageIcon(imagenRedimensionada));
				logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				logoutButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						LoginVista lv = LoginVista.getLogin();
						lv.setVisible(true);
						
			
						 
					}
				});
				return logoutButton;
			}
			return logoutButton;
	}
			
	private JPanel getClientes() throws SQLException {
		this.tabla = new JPanel();
		tabla.setLayout(new BoxLayout(tabla, BoxLayout.Y_AXIS));
		this.tabla.setBackground(this.color1); // ponemos mismo color para que no se note el panel
		this.tabla.setBounds(0, 45, 500, 220);
		Border borde = BorderFactory.createLineBorder(Color.black, 1);
		this.tabla.setBorder(borde);
		ResultSet clientes = db.conseguirClientes(this.usuarioNombre);
		
		
		while(clientes.next()) {
			String nombre = clientes.getString("nombre");
			String cliente = clientes.getString("nombre");
			JButton boton  = new JButton();
			boton.setText(cliente);
			boton.setHorizontalAlignment(SwingConstants.LEFT);
			boton.setFont(new Font("Tahoma", Font.PLAIN, 17));
			boton.setForeground(this.azulCielo);
	        boton.setBackground(new Color(217, 217, 217)); // Color de fondo personalizado  
	        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Espaciado interno
	        boton.setOpaque(true); // Permitir el pintado del color de fondo
	        boton.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
	        
	        boton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JButton botonClicado = (JButton) e.getSource();
					String nombreCliente = botonClicado.getText();
					setVisible(false);
					CalendariVista cv;
					try {
						ConsultasDBModelo db = new ConsultasDBModelo();
						cv = CalendariVista.getCalendario(LocalDate.now().getYear(),LocalDate.now().getMonth().toString(),nombreCliente,true,usuarioNombre);
						cv.setEsEntrenador(true);
						cv.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					

	            }
	        });
	        
	        tabla.add(Box.createRigidArea(new Dimension(0, 5)));
			boton.setMaximumSize(new Dimension(Short.MAX_VALUE, boton.getPreferredSize().height));
			this.tabla.add(boton);
			
		}
		db.desconexion();
		return tabla;
		
	}
		
	
}

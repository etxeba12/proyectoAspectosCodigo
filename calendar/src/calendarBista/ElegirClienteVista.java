package calendarBista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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

	private JPanel elegirCliente;
	private JPanel tabla;

	private JPanel parteArriba;
	private JRadioButton rdbtnNewRadioButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ElegirClienteVista frame = new ElegirClienteVista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ElegirClienteVista() throws SQLException {
		setTitle("Elegir Cliente"); //titulo de la pagina
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
	
	private JPanel getClientes() throws SQLException {
		this.tabla = new JPanel();
		tabla.setLayout(new BoxLayout(tabla, BoxLayout.Y_AXIS));
		this.tabla.setBackground(this.color1); // ponemos mismo color para que no se note el panel
		this.tabla.setBounds(0, 45, 500, 220);
		Border borde = BorderFactory.createLineBorder(Color.black, 1);
		this.tabla.setBorder(borde);
		ResultSet clientes = db.conseguirClientes("Iker");
		
		
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
						cv = CalendariVista.getCalendario(LocalDate.now().getYear(),LocalDate.now().getMonth().toString(),nombreCliente,true);
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

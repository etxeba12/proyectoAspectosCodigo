package calendarBista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import calendarModelo.ConsultasDBModelo;

public class EntrenoInfoVista  extends JFrame{
	
	private JLabel nombreEjercicio;
	private Color colorBlanco = new Color(255,255,255); //definir color de atras
	private Color color1 = new Color(231, 231, 231); //definir color de panel atras
	private Color azulCielo = new Color(31, 197, 203);
	private static EntrenoInfoVista miEntrenoInfo;
	private JPanel entrenoInfo ;
	private JPanel parteArriba;
	private String nombreEjer;
	private ConsultasDBModelo cd = new ConsultasDBModelo();
	private JPanel tabla;
	private JPanel linea;
	private JButton botonGuardar;
	private String series;
	
	
	//preguntar xq nos pide que cambiemos la visibilidad
	EntrenoInfoVista(String pEjer) throws SQLException {
		nombreEjer = pEjer;
		setTitle("Información entreno"); //titulo de la pagina
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //que hacer en caso de cerrar la pestaña
		setBounds(120, 120, 500, 300); // definimos tamaño del panel a mano
		entrenoInfo = new JPanel();
		this.entrenoInfo.setBackground(this.color1); //definimos color de fondo
		entrenoInfo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(entrenoInfo); //colocamos el panel dentro de la ventana principal.
		this.entrenoInfo.setLayout(null); //asi colocamos las cosas donde nosotros queremos
		{
			this.parteArriba = new JPanel();
			this.parteArriba.setBackground(this.color1); // ponemos mismo color para que no se note el panel
			this.parteArriba.setBounds(0, 5, 500, 35);
			Border borde = BorderFactory.createLineBorder(Color.black, 1);
			this.parteArriba.setBorder(borde);
			this.parteArriba.setBackground(azulCielo);
			this.entrenoInfo.add(this.parteArriba); 
			this.parteArriba.add(getNombreEjercicio());
		}
		this.entrenoInfo.add(getEntrenamientos());
		 
		setLocationRelativeTo(null);
	}
	
	public static EntrenoInfoVista getMiEntreno(String ejer) throws SQLException {
		if( miEntrenoInfo == null) {
			miEntrenoInfo = new EntrenoInfoVista(ejer);
		}
		return miEntrenoInfo;
	}
	
	
	//////////////creamos los labels y los TF //////////////
		
	public JLabel getNombreEjercicio() { //si no se ha creado la etiqueta todavia, la creamos
		if(nombreEjercicio == null) {
			nombreEjercicio = new JLabel(nombreEjer);
			nombreEjercicio.setBounds(0, 0, 140, 120);
			nombreEjercicio.setForeground(Color.white);
			nombreEjercicio.setFont(new Font("Tahoma", Font.PLAIN, 20));	
		}
		return nombreEjercicio;
	}
	
	//////////////creamos los labels y los TF //////////////
	
	private JPanel getEntrenamientos() throws SQLException {
		ResultSet respuesta = cd.conseguirInfoEntreno(nombreEjer);
		respuesta.next();
		series = respuesta.getString("series");
		this.tabla = new JPanel(new GridLayout(Integer.parseInt(series) +  1 ,3));
		tabla.setLayout(new BoxLayout(tabla, BoxLayout.Y_AXIS));
		this.tabla.setBackground(this.color1); // ponemos mismo color para que no se note el panel
		this.tabla.setBounds(0, 45, 500, 220);
		Border borde = BorderFactory.createLineBorder(Color.black, 1);
		this.tabla.setBorder(borde);
		Integer i = 1;
		JLabel kgTitulo = new JLabel("Kilos");
		kgTitulo.setBounds(58, 0 ,100 , 25);
		kgTitulo.setForeground(azulCielo);
		JLabel repesTitulo = new JLabel("Repeticiones");
		repesTitulo.setBounds(210, 0 ,100 , 25);
		repesTitulo.setForeground(azulCielo);
		JLabel RPETitulo = new JLabel("RPE");
		RPETitulo.setBounds(393, 0 ,100 , 25);
		RPETitulo.setForeground(azulCielo);
		this.linea = new JPanel(new FlowLayout(FlowLayout.LEFT)); // crear un panel horizontal para poner componentes seguidos
		this.linea.setBackground(new Color(217, 217, 217)); // Color de fondo personalizado ); // ponemos mismo color para que no se note el panel
		this.linea.setBounds(0, 0, 100, 25);
		this.linea.setLayout(null);
		this.tabla.add(this.linea); // añadimos el Panel al principal panel
		this.linea.add(kgTitulo);
		this.linea.add(repesTitulo);
		this.linea.add(RPETitulo);
		tabla.add(Box.createRigidArea(new Dimension(0, 5)));
		linea.setMaximumSize(new Dimension(Short.MAX_VALUE, linea.getPreferredSize().height));
		Component espacio = Box.createRigidArea(new Dimension(6, 0)); //creamos el espacio
		while(i <= Integer.parseInt(series)) {
			String reps = respuesta.getString("repes");
			JLabel repes = new JLabel(reps);
			repes.setBounds(240, 0 ,100 , 25);
			JTextField kilos = new JTextField();
			repes.setForeground(azulCielo);
			kilos.setBounds(25, 0 , 100, 25);
			JTextField RPE = new JTextField();
			RPE.setBounds(357, 0 , 100, 25);
			this.linea = new JPanel(new FlowLayout(FlowLayout.LEFT)); // crear un panel horizontal para poner componentes seguidos
			this.linea.setBackground(new Color(217, 217, 217)); // Color de fondo personalizado ); // ponemos mismo color para que no se note el panel
			this.linea.setBounds(0, 0, 100, 25);
			this.linea.setLayout(null);
			this.tabla.add(this.linea); // añadimos el Panel al principal panel
			this.linea.add(kilos);
			this.linea.add(espacio);
			this.linea.add(repes);
			this.linea.add(espacio);
			this.linea.add(RPE);
			tabla.add(Box.createRigidArea(new Dimension(0, 5))); //crear espacio entre jpanels
			linea.setMaximumSize(new Dimension(Short.MAX_VALUE, linea.getPreferredSize().height)); // que ocupe toda la ventana cada linea
			this.tabla.add(linea);
			i++;
		}
		return tabla;
	}
	
	/*
	private JButton getBotonGuardar() {
		if(botonGuardar == null) {
			botonGuardar = new JButton();
			botonGuardar.setBackground(this.azulCielo);
			botonGuardar.setForeground(colorBlanco);
			Border borde = BorderFactory.createLineBorder(Color.black, 1); // creamos el borde del boton
			botonGuardar.setBorder(borde);
			botonGuardar.setText(" ACTUALIZAR ");
			botonGuardar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int i = 1;
					while(i <= Integer.parseInt(series)) {
						cd.actualizarInfo(nombreEjer, nombreEjer);
					}
				}
			});
		}
		return botonGuardar;
	}
	*/

}

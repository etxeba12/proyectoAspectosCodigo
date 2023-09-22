package calendarBista;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import calendarModelo.ConsultasDBModelo;

public class EntrenoInfoVista  extends JFrame{
	
	private JLabel nombreEjercicio;
	private Color azulCielo = new Color(31, 197, 203);
	private Color color1 = new Color(231, 231, 231); //definir color de panel atras
	private static EntrenoInfoVista miEntrenoInfo;
	private JPanel entrenoInfo ;
	private JPanel parteArriba;
	private String nombreEjer;
	private ConsultasDBModelo cd = new ConsultasDBModelo();
	
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

}

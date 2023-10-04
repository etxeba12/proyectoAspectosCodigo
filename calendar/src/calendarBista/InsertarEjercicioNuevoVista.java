package calendarBista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import calendarModelo.ConsultasDBModelo;

public class InsertarEjercicioNuevoVista extends JFrame {

	private JPanel contentPane;

	private Color colorBlanco = new Color(255,255,255); //definir color de atras
	private Color color1 = new Color(231, 231, 231); //definir color de panel atras
	private Color azulCielo = new Color(31, 197, 203);
	private JPanel entrenoInfo ;
	private JPanel parteArriba;
	private JPanel tabla;
	private JPanel linea;
	private JButton botonGuardar;
	private JLabel titulo;
		
		
	//preguntar xq nos pide que cambiemos la visibilidad
	public InsertarEjercicioNuevoVista() throws SQLException {
		setTitle("Inserta un ejercicio"); //titulo de la pagina
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //que hacer en caso de cerrar la pesta�a
		setBounds(120, 120, 518, 309); // definimos tama�o del panel a mano
		entrenoInfo = new JPanel();
		this.entrenoInfo.setBackground(this.color1); //definimos color de fondo
		entrenoInfo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(entrenoInfo); //colocamos el panel dentro de la ventana principal.
		this.entrenoInfo.setLayout(null); //asi colocamos las cosas donde nosotros queremos
		{
			this.parteArriba = new JPanel();
			this.parteArriba.setBackground(this.color1); // ponemos mismo color para que no se note el panel
			this.parteArriba.setBounds(1, 5, 500, 35);
			Border borde = BorderFactory.createLineBorder(Color.black, 1);
			this.parteArriba.setBorder(borde);
			this.parteArriba.setBackground(azulCielo);
			this.entrenoInfo.add(this.parteArriba); 
			this.parteArriba.add(getTitulo());
			
		}
		this.entrenoInfo.add(getEntrenamientos());
		 
		setLocationRelativeTo(null);
	}
	
	public JLabel getTitulo() { //si no se ha creado la etiqueta todavia, la creamos
		if(titulo == null) {
			titulo = new JLabel("Inserta un ejercicio");
			titulo.setBounds(0, 0, 140, 120);
			titulo.setForeground(Color.white);
			titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));	
		}
		return titulo;
	}
	
	
	//////////////creamos los labels y los TF //////////////
	
	private JPanel getEntrenamientos() throws SQLException {
		this.tabla = new JPanel(new GridLayout(1 ,4));
		tabla.setLayout(new BoxLayout(tabla, BoxLayout.Y_AXIS)); //para que se ponga una linea debajo de la otra
		this.tabla.setBackground(this.color1); // ponemos mismo color para que no se note el panel
		this.tabla.setBounds(1, 45, 500, 220);
		Border borde = BorderFactory.createLineBorder(Color.black, 1);
		this.tabla.setBorder(borde);
		
		
		JLabel nombreLbl = new JLabel("Nombre");
		nombreLbl.setBounds(45, 0 ,100 , 25);
		nombreLbl.setForeground(azulCielo);
		JLabel kgTitulo = new JLabel("Kilos");
		kgTitulo.setBounds(170, 0 ,100 , 25);
		kgTitulo.setForeground(azulCielo);
		JLabel repesTitulo = new JLabel("Repeticiones");
		repesTitulo.setBounds(255, 0 ,100 , 25);
		repesTitulo.setForeground(azulCielo);
		JLabel RPETitulo = new JLabel("RPE");
		RPETitulo.setBounds(405, 0 ,100 , 25);
		RPETitulo.setForeground(azulCielo);
		this.linea = new JPanel(new FlowLayout(FlowLayout.LEFT)); // crear un panel horizontal para poner componentes seguidos
		this.linea.setBackground(new Color(217, 217, 217)); // Color de fondo personalizado ); // ponemos mismo color para que no se note el panel
		this.linea.setBounds(0, 0, 100, 25);
		this.linea.setLayout(null);
		this.tabla.add(this.linea); // a�adimos el Panel al principal panel
		this.linea.add(nombreLbl);
		this.linea.add(kgTitulo);
		this.linea.add(repesTitulo);
		this.linea.add(RPETitulo);
		tabla.add(Box.createRigidArea(new Dimension(0, 5)));
		linea.setMaximumSize(new Dimension(Short.MAX_VALUE, linea.getPreferredSize().height));
		Component espacio = Box.createRigidArea(new Dimension(6, 0)); //creamos el espacio
		
		for (int i = 0; i < 5; i++) {
			
			
			JTextField nombre = new JTextField();
			nombre.setBounds(25, 0, 100, 25);
			
			JTextField kilos = new JTextField();
			kilos.setBounds(140, 0, 100, 25);
			
			JTextField repes = new JTextField();
			repes.setBounds(255, 0, 100, 25);
			
			JTextField RPE = new JTextField();
			RPE.setBounds(370, 0, 100, 25);
			
		
			
			
			this.linea = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
			this.linea.setBackground(new Color(217, 217, 217)); // Color de fondo personalizado ); // ponemos mismo color para que no se note el panel
			this.linea.setBounds(0, 0, 100, 25);
			this.linea.setLayout(null);
			this.tabla.add(this.linea); // Añadimos el Panel al panel principal
			this.linea.add(nombre);
			this.linea.add(kilos);
			this.linea.add(espacio);
			this.linea.add(repes);
			this.linea.add(espacio);
			this.linea.add(RPE);
			tabla.add(Box.createRigidArea(new Dimension(0, 5)));
			this.linea.setMaximumSize(new Dimension(Short.MAX_VALUE, this.linea.getPreferredSize().height));
			
		
		}
		this.linea = new JPanel(new FlowLayout(FlowLayout.LEFT)); // crear un panel horizontal para poner componentes seguidos
		this.linea.setBackground(new Color(217, 217, 217)); // Color de fondo personalizado ); // ponemos mismo color para que no se note el panel
		this.linea.setBounds(0, 0, 100, 25);
		this.linea.setLayout(null);
		this.tabla.add(this.linea);

		tabla.add(Box.createRigidArea(new Dimension(0, 5)));
		linea.setMaximumSize(new Dimension(Short.MAX_VALUE, linea.getPreferredSize().height));
		this.linea.add(getBotonGuardar());
		return tabla;
	}
	
	
	private JButton getBotonGuardar() {
		if(botonGuardar == null) {
			botonGuardar = new JButton();
			botonGuardar.setBackground(this.azulCielo);
			botonGuardar.setForeground(colorBlanco);
			botonGuardar.setBounds(200, 0, 100, 25);
			Border borde = BorderFactory.createLineBorder(Color.black, 1); // creamos el borde del boton
			botonGuardar.setBorder(borde);
			botonGuardar.setText(" Guardar ");
			botonGuardar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					}
				
			});
		}
		return botonGuardar;
	}
	

}




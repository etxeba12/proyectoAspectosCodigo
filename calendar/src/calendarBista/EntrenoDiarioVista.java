package calendarBista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import exceptions.ExceptionModificable;
import calendarModelo.ConsultasDBModelo;

public class EntrenoDiarioVista extends JFrame {

		private JLabel fechaTitulo;
		private JButton flechaAtras;
		private JButton flechaDelante;
		private Color azulCielo = new Color(31, 197, 203);
		private Color colorBlanco = new Color(255,255,255); //definir color de atras
		private Color color1 = new Color(231, 231, 231); //definir color de panel atras
		private static EntrenoDiarioVista miEntrenoDiario;
		private JPanel entrenoDiario ;
		private JPanel parteArriba;
		private JPanel tabla;
		private ConsultasDBModelo cd = new ConsultasDBModelo();
		
		private EntrenoDiarioVista() throws SQLException {
			setTitle("Entreno diario"); //titulo de la pagina
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //que hacer en caso de cerrar la pestaña
			setBounds(120, 120, 500, 300); // definimos tamaño del panel a mano
			entrenoDiario = new JPanel();
			this.entrenoDiario.setBackground(this.color1); //definimos color de fondo
			entrenoDiario.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(entrenoDiario); //colocamos el panel dentro de la ventana principal.
			this.entrenoDiario.setLayout(null); //asi colocamos las cosas donde nosotros queremos
			{
				this.parteArriba = new JPanel();
				this.parteArriba.setBackground(this.color1); // ponemos mismo color para que no se note el panel
				this.parteArriba.setBounds(0, 5, 500, 35);
				Border borde = BorderFactory.createLineBorder(Color.black, 1);
				this.parteArriba.setBorder(borde);
				this.parteArriba.setBackground(azulCielo);
				this.entrenoDiario.add(this.parteArriba); 
				this.parteArriba.add(getFlechaAtras());
				this.parteArriba.add(getFecha());
				this.parteArriba.add(getFlechaDelante());
			}
			this.entrenoDiario.add(getEntrenamientos()); 
			setLocationRelativeTo(null);
		}
		
		public static EntrenoDiarioVista getMiEntreno() throws SQLException {
			if( miEntrenoDiario == null) {
				miEntrenoDiario = new EntrenoDiarioVista();
			}
			return miEntrenoDiario;
		}
		
		public static void main(String[] args) throws SQLException {
			EntrenoDiarioVista lg = getMiEntreno();
			lg.setVisible(true);
		}
		
		//////////////creamos los labels y los TF //////////////
		
		public JLabel getFecha() { //si no se ha creado la etiqueta todavia, la creamos
			java.util.Date fecha = new Date();
			String date = fecha.toString();
			String[] palabra = date.split(" ");
			System.out.println(date);
			if(fechaTitulo == null) {
				fechaTitulo = new JLabel(palabra[2]+" "+palabra[1]+" " +palabra[5]);
				fechaTitulo.setBounds(0, 0, 140, 120);
				fechaTitulo.setForeground(Color.white);
				fechaTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
				
			}
			return fechaTitulo;
		}
		
		//////////////creamos los labels y los TF //////////////
		
		
		//////////////botones //////////////
				
		private JButton getFlechaAtras() {
			if(flechaAtras == null) {
				flechaAtras = new JButton();
				flechaAtras.setBackground(this.azulCielo);
				flechaAtras.setBorder(null);
				ImageIcon Original = new  ImageIcon(EntrenoDiarioVista.class.getResource("/imagenes/atras.png"));
				Image imagenRedimensionada = Original.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	            flechaAtras.setIcon(new ImageIcon(imagenRedimensionada));
				flechaAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
				flechaAtras.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
			return flechaAtras;
		}
		
		private JButton getFlechaDelante() {
			if(flechaDelante == null) {
				flechaDelante = new JButton();
				flechaDelante.setBackground(this.azulCielo);
				flechaDelante.setBorder(null);
				ImageIcon Original = new  ImageIcon(EntrenoDiarioVista.class.getResource("/imagenes/flecha-correcta.png"));
				Image imagenRedimensionada = Original.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
				flechaDelante.setIcon(new ImageIcon(imagenRedimensionada));
				flechaDelante.setCursor(new Cursor(Cursor.HAND_CURSOR));
				flechaDelante.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
			return flechaDelante;
		}
		
		private JPanel getEntrenamientos() throws SQLException {
			this.tabla = new JPanel();
			tabla.setLayout(new BoxLayout(tabla, BoxLayout.Y_AXIS));
			this.tabla.setBackground(this.color1); // ponemos mismo color para que no se note el panel
			this.tabla.setBounds(0, 45, 500, 220);
			Border borde = BorderFactory.createLineBorder(Color.black, 1);
			this.tabla.setBorder(borde);
			ResultSet respuesta = cd.conseguirEntreno();
			Integer i = 1;
			while(respuesta.next()) {
				String ejer = respuesta.getString("ejercicio");
				String botoiIZena = "boton" + i.toString();
				JButton boton  = new JButton(botoiIZena);
				boton.setText(ejer);
				boton.setHorizontalAlignment(SwingConstants.LEFT);
				boton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				boton.setForeground(this.azulCielo);
		        boton.setBackground(new Color(217, 217, 217)); // Color de fondo personalizado  
		        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Espaciado interno
		        boton.setOpaque(true); // Permitir el pintado del color de fondo
		        boton.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
		        tabla.add(Box.createRigidArea(new Dimension(0, 5)));
				boton.setMaximumSize(new Dimension(Short.MAX_VALUE, boton.getPreferredSize().height));
				this.tabla.add(boton);
				i++;
			}
			return tabla;
		}
		
}

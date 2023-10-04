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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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
import calendarBista.EntrenoInfoVista;
import calendarBista.CalendariVista;


public class EntrenoDiarioVista extends JFrame {

		private JLabel fechaTitulo;
		private JButton flechaAtras;
		private JButton flechaDelante;
		private JButton volverCalendar;
		private JButton insertarEjercicios;
		private Color azulCielo = new Color(31, 197, 203);
		private Color color1 = new Color(231, 231, 231); //definir color de panel atras
		private static EntrenoDiarioVista miEntrenoDiario;
		private JPanel entrenoDiario ;
		private JPanel parteArriba;
		private JPanel tabla;
		private ConsultasDBModelo cd = new ConsultasDBModelo();
		private String fecha;
		private String nombre;
		private Boolean esEntrenador;
		
		EntrenoDiarioVista(String pFecha,String pNombre, Boolean pEsEntrenador) throws SQLException { //HAY QUE MIRAR ESTO
			fecha = pFecha;
			nombre = pNombre;
			this.esEntrenador = pEsEntrenador;
			setTitle("Entreno diario"); //titulo de la pagina
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //que hacer en caso de cerrar la pesta�a
			setBounds(120, 120, 518, 309); // definimos tama�o del panel a mano
			entrenoDiario = new JPanel();
			this.entrenoDiario.setBackground(this.color1); //definimos color de fondo
			entrenoDiario.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(entrenoDiario); //colocamos el panel dentro de la ventana principal.
			this.entrenoDiario.setLayout(null); //asi colocamos las cosas donde nosotros queremos
			{
				this.parteArriba = new JPanel();
				this.parteArriba.setBackground(this.color1); // ponemos mismo color para que no se note el panel
				this.parteArriba.setBounds(1, 5, 500, 35);
				Border borde = BorderFactory.createLineBorder(Color.black, 1);
				this.parteArriba.setBorder(borde);
				this.parteArriba.setLayout(null);
				this.parteArriba.setBackground(azulCielo);
				this.entrenoDiario.add(this.parteArriba);
				this.parteArriba.add(getVolverCalendario());
				this.parteArriba.add(getFlechaAtras());
				this.parteArriba.add(getFecha());
				this.parteArriba.add(getFlechaDelante());
				if(esEntrenador) {
					this.parteArriba.add(getInsertarEntrenamientos());
				}
			}
			this.entrenoDiario.add(getEntrenamientos()); 
			setLocationRelativeTo(null);
		}
		
		public static EntrenoDiarioVista getMiEntreno(String pFecha, String pNombre, Boolean pEsEntrenador) throws SQLException {
			if( miEntrenoDiario == null) {
				miEntrenoDiario = new EntrenoDiarioVista(pFecha,pNombre,pEsEntrenador);
			}
			return miEntrenoDiario;
		}
		
		
		//////////////creamos los labels y los TF //////////////
		
		public JLabel getFecha() { //si no se ha creado la etiqueta todavia, la creamos
			if(fechaTitulo == null) {
				fechaTitulo = new JLabel(fecha);
				fechaTitulo.setBounds(190, 5, 100, 25);
				fechaTitulo.setForeground(Color.white);
				fechaTitulo.setFont(new Font("Tahoma", Font.PLAIN, 17));
				
			}
			return fechaTitulo;
		}
		
		//////////////creamos los labels y los TF //////////////
		
		
		//////////////botones //////////////
		
		private JButton getVolverCalendario() {
			if(volverCalendar == null) {
				volverCalendar = new JButton();
				volverCalendar.setBackground(this.azulCielo);
				volverCalendar.setBorder(null);
				volverCalendar.setBounds(445, 5, 50, 25);
				ImageIcon Original = new  ImageIcon(EntrenoDiarioVista.class.getResource("/imagenes/home.png"));
				Image imagenRedimensionada = Original.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
				volverCalendar.setIcon(new ImageIcon(imagenRedimensionada));
				volverCalendar.setCursor(new Cursor(Cursor.HAND_CURSOR));
				volverCalendar.setFont(new Font("Tahoma", Font.PLAIN, 16));
				volverCalendar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						CalendariVista ed;
						try {
							ed = CalendariVista.getCalendario(Integer.parseInt(fecha.substring(0, 4)),fecha.substring(5,7),nombre,esEntrenador);
							ed.setVisible(true);
							dispose();
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
			}
			return volverCalendar;
		}
				
		private JButton getFlechaAtras() {
			if(flechaAtras == null) {
				flechaAtras = new JButton();
				flechaAtras.setBackground(this.azulCielo);
				flechaAtras.setBorder(null);
				flechaAtras.setBounds(115, 5, 50, 25);
				ImageIcon Original = new  ImageIcon(EntrenoDiarioVista.class.getResource("/imagenes/atras.png"));
				Image imagenRedimensionada = Original.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	            flechaAtras.setIcon(new ImageIcon(imagenRedimensionada));
				flechaAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
				flechaAtras.setFont(new Font("Tahoma", Font.PLAIN, 16));
				flechaAtras.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {
							String fechaAnterior = calcularDiaAnterior(fecha);
		                    EntrenoDiarioVista ed = new EntrenoDiarioVista(fechaAnterior,nombre,esEntrenador);
		                    ed.setVisible(true);
		                    dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
				});
			}
			return flechaAtras;
		}
		
		private JButton getFlechaDelante() {
			if(flechaDelante == null) {
				flechaDelante = new JButton();
				flechaDelante.setBackground(this.azulCielo);
				flechaDelante.setBorder(null);
				flechaDelante.setBounds(305, 5, 50, 25);
				ImageIcon Original = new  ImageIcon(EntrenoDiarioVista.class.getResource("/imagenes/flecha-correcta.png"));
				Image imagenRedimensionada = Original.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
				flechaDelante.setIcon(new ImageIcon(imagenRedimensionada));
				flechaDelante.setCursor(new Cursor(Cursor.HAND_CURSOR));
				flechaDelante.setFont(new Font("Tahoma", Font.PLAIN, 16));
				flechaDelante.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// falta mirar que pasa si un entreno que busques no existe	 
						try {
							String fechaSiguiente = calcularDiaSiguiente(fecha);
		                    EntrenoDiarioVista ed = new EntrenoDiarioVista(fechaSiguiente,nombre,esEntrenador);
		                    ed.setVisible(true);
		                    dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
				});
			}
			return flechaDelante;
		}
		
		private String crearFormatoFecha(Date pFecha) {
			String date = pFecha.toString();
			String[] palabra = date.split(" ");
			String[] meses = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
			int mes = 0;
			boolean encontrado = false;
			System.out.println(palabra[1]);
			while(!encontrado) {
				if(palabra[1].equals(meses[mes])) {
					encontrado = true;
				}
				else {
					mes = mes + 1;
				}
			}
			mes = mes + 1;
			String resultado = palabra[5]+"-0"+mes+"-"+palabra[2];
			System.out.println(resultado);
			return resultado;
			
		}
		
		private JPanel getEntrenamientos() throws SQLException {
			this.tabla = new JPanel();
			tabla.setLayout(new BoxLayout(tabla, BoxLayout.Y_AXIS));
			this.tabla.setBackground(this.color1); // ponemos mismo color para que no se note el panel
			this.tabla.setBounds(1, 45, 500, 220);
			Border borde = BorderFactory.createLineBorder(Color.black, 1);
			this.tabla.setBorder(borde);
			ResultSet respuesta = cd.conseguirEntreno(fecha,nombre);
			Integer i = 1;
			while(respuesta.next()) {
				String ejer = respuesta.getString("nombreEjercicio");
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
		        boton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                try {
		                	JButton botonClicado = (JButton) e.getSource();
			                String nombreEjer = botonClicado.getText();
							EntrenoInfoVista ei = new EntrenoInfoVista(nombreEjer);
							ei.setVisible(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

		            }
		        });
		        tabla.add(Box.createRigidArea(new Dimension(0, 5)));
				boton.setMaximumSize(new Dimension(Short.MAX_VALUE, boton.getPreferredSize().height));
				this.tabla.add(boton);
				i++;
			}
			return tabla;
		}
		private JButton getInsertarEntrenamientos() {
			if(insertarEjercicios == null) {
				insertarEjercicios = new JButton();
				insertarEjercicios.setBackground(this.azulCielo);
				insertarEjercicios.setBorder(null);
				insertarEjercicios.setBounds(5, 5, 50, 25);
				ImageIcon Original = new  ImageIcon(EntrenoDiarioVista.class.getResource("/imagenes/insertar.png"));
				Image imagenRedimensionada = Original.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
				insertarEjercicios.setIcon(new ImageIcon(imagenRedimensionada));
				insertarEjercicios.setCursor(new Cursor(Cursor.HAND_CURSOR));
				insertarEjercicios.setFont(new Font("Tahoma", Font.PLAIN, 16));
				insertarEjercicios.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {
							InsertarEjercicioNuevoVista ie = new InsertarEjercicioNuevoVista();
							
							ie.setVisible(true);
							dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
			}
			return insertarEjercicios;
		}
		
		private String calcularDiaSiguiente(String fechaStr) throws java.text.ParseException {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        Date fecha = dateFormat.parse(fechaStr);
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(fecha);
	        calendar.add(Calendar.DAY_OF_MONTH, 1);
	        String diaSiguienteStr = dateFormat.format(calendar.getTime());
	        
	        return diaSiguienteStr;
	    }
		
		private String calcularDiaAnterior(String fechaStr) throws java.text.ParseException {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        Date fecha = dateFormat.parse(fechaStr);
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(fecha);
	        calendar.add(Calendar.DAY_OF_MONTH, -1);
	        String diaSiguienteStr = dateFormat.format(calendar.getTime());
	        
	        return diaSiguienteStr;
	    }
		
}

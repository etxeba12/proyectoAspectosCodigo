package calendarBista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Position.Bias;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import calendarModelo.ConsultasDBModelo;
import exceptions.ExceptionModificable;

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
	private String fecha;
	private String user;
	private String nombreEntrenador;
	private JTextField nombre;
	private JTextField kilos;
	private JTextField series;
	private JTextField repes;
	private JTextField RPE;
	private String n;
	private int k;
	private int s;
	private int rep;
	private int rp;
	private JPanel linea1;
	private JButton home;
	private String name;

	ConsultasDBModelo cons = new ConsultasDBModelo();

	
	
	

		
		
	//preguntar xq nos pide que cambiemos la visibilidad
	public InsertarEjercicioNuevoVista(String nombre,String pNombreEntrenador) throws SQLException {
		setTitle("INSERTA UN EJERCICIO"); //titulo de la pagina
		nombreEntrenador = pNombreEntrenador;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //que hacer en caso de cerrar la pesta�a
		setBounds(120, 120, 618, 309); // definimos tama�o del panel a mano
		entrenoInfo = new JPanel();
		this.entrenoInfo.setBackground(this.color1); //definimos color de fondo
		entrenoInfo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(entrenoInfo); //colocamos el panel dentro de la ventana principal.
		this.entrenoInfo.setLayout(null); //asi colocamos las cosas donde nosotros queremos
		{
			name = nombre;
			this.parteArriba = new JPanel();
			this.parteArriba.setBackground(this.color1); // ponemos mismo color para que no se note el panel
			this.parteArriba.setBounds(1, 5, 700, 35);
			Border borde = BorderFactory.createLineBorder(Color.black, 1);
			this.parteArriba.setBorder(borde);
			this.parteArriba.setLayout(null);
			this.parteArriba.setBackground(azulCielo);
			this.entrenoInfo.add(this.parteArriba); 
			this.parteArriba.add(getTitulo());
			this.parteArriba.add(getHome());
			
		}
		this.entrenoInfo.add(getEntrenamientos());
		setLocationRelativeTo(null);
	}
	
	public JLabel getTitulo() { //si no se ha creado la etiqueta todavia, la creamos
		if(titulo == null) {
			titulo = new JLabel("Inserta un ejercicio");
			titulo.setBounds(200, 5, 300, 25);
			titulo.setForeground(Color.white);
			titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));	
		}
		return titulo;
	}
	
	
	//////////////creamos los labels y los TF //////////////
	
	private JPanel getEntrenamientos() throws SQLException {
		this.tabla = new JPanel(new GridLayout(1 ,5));
		tabla.setLayout(new BoxLayout(tabla, BoxLayout.Y_AXIS)); //para que se ponga una linea debajo de la otra
		this.tabla.setBackground(this.color1); // ponemos mismo color para que no se note el panel
		this.tabla.setBounds(1, 45, 600, 220);
		Border borde = BorderFactory.createLineBorder(Color.black, 1);
		this.tabla.setBorder(borde);
		
		
		JLabel nombreLbl = new JLabel("Nombre");
		nombreLbl.setBounds(45, 0 ,100 , 25);
		nombreLbl.setForeground(azulCielo);
		JLabel kgTitulo = new JLabel("Kilos");
		kgTitulo.setBounds(170, 0 ,100 , 25);
		kgTitulo.setForeground(azulCielo);
		JLabel seriestitulo = new JLabel("Series");
		seriestitulo.setBounds(255,0,100,25);
		seriestitulo.setForeground(azulCielo);
		JLabel repesTitulo = new JLabel("Repeticiones");
		repesTitulo.setBounds(380, 0 ,100 , 25);
		repesTitulo.setForeground(azulCielo);
		JLabel RPETitulo = new JLabel("RPE");
		RPETitulo.setBounds(505, 0 ,100 , 25);
		RPETitulo.setForeground(azulCielo);

		
		this.linea = new JPanel(new FlowLayout(FlowLayout.LEFT)); // crear un panel horizontal para poner componentes seguidos
		this.linea.setBackground(new Color(217, 217, 217)); // Color de fondo personalizado ); // ponemos mismo color para que no se note el panel
		this.linea.setBounds(0, 0, 100, 25);
		this.linea.setLayout(null);
		this.tabla.add(this.linea); // a�adimos el Panel al principal panel
		this.linea.add(nombreLbl);
		this.linea.add(kgTitulo);
		this.linea.add(seriestitulo);
		this.linea.add(repesTitulo);
		this.linea.add(RPETitulo);
		tabla.add(Box.createRigidArea(new Dimension(0, 5)));
		linea.setMaximumSize(new Dimension(Short.MAX_VALUE, linea.getPreferredSize().height));
		Component espacio = Box.createRigidArea(new Dimension(6, 0)); //creamos el espacio
		
		for (int i = 0; i < 5; i++) {
			
			nombre = new JTextField();
			nombre.setBounds(25, 0, 100, 25);
			
			kilos = new JTextField();
			kilos.setBounds(140, 0, 100, 25);
			
			series = new JTextField();
			series.setBounds(255,0,100,25);
			
			repes = new JTextField();
			repes.setBounds(370, 0, 100, 25);
			
			RPE = new JTextField();
			RPE.setBounds(485, 0, 100, 25);
			
		
		
		
			
			
			this.linea = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
			this.linea.setBackground(new Color(217, 217, 217)); // Color de fondo personalizado ); // ponemos mismo color para que no se note el panel
			this.linea.setBounds(0, 0, 100, 25);
			this.linea.setLayout(null);
			this.tabla.add(this.linea); // Añadimos el Panel al panel principal
			this.linea.add(nombre);
			this.linea.add(kilos);
			this.linea.add(series);
			this.linea.add(espacio);
			this.linea.add(repes);
			this.linea.add(espacio);
			this.linea.add(RPE);
			
			
		
			tabla.add(Box.createRigidArea(new Dimension(0, 5)));
			this.linea.setMaximumSize(new Dimension(Short.MAX_VALUE, this.linea.getPreferredSize().height));
			
		}
		
		this.linea = new JPanel(new FlowLayout(FlowLayout.LEFT)); // crear un panel horizontal para poner componentes seguidos
		this.linea.setBackground(new Color(217, 217, 217)); // Color de fondo personalizado ); // ponemos mismo color para que no se note el panel
		this.linea.setBounds(0, 0, 600, 25);		
		this.linea.setLayout(null);
		this.linea.add(getBotonGuardar());
		this.tabla.add(this.linea);
		
		tabla.add(Box.createRigidArea(new Dimension(0, 6)));
		linea.setMaximumSize(new Dimension(Short.MAX_VALUE, linea.getPreferredSize().height));
		
		
		
		return tabla;
	}
	
	private JButton getHome() {
		if(home == null) {
			home = new JButton();
			home.setBackground(this.azulCielo);
			home.setBorder(null);
			home.setBounds(550, 5, 50, 25);
			ImageIcon Original = new  ImageIcon(EntrenoDiarioVista.class.getResource("/imagenes/home.png"));
			Image imagenRedimensionada = Original.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
			home.setIcon(new ImageIcon(imagenRedimensionada));
			home.setCursor(new Cursor(Cursor.HAND_CURSOR));
			home.setFont(new Font("Tahoma", Font.PLAIN, 16));
			
			home.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						EntrenoDiarioVista ed = EntrenoDiarioVista.getMiEntreno(fecha, name, true,nombreEntrenador);
						ed.setVisible(true);
						dispose();
						
					}catch (Exception exce) {
						// TODO: handle exception
					}
				}
			});
			
		}
		return home;
		
	}
			
	
	private JButton getBotonGuardar() throws SQLException {
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
					boolean primera = true;
					int ind = 0;
					
					for(Component c : tabla.getComponents()) {
						if(primera) {
							primera = false;
							continue;
						}else if(ind==5){
							break;
						}
						if(c instanceof JPanel) {
							ind++;
							JPanel line = (JPanel) c;
							Component a =  line.getComponent(0);
							if(a instanceof JButton) {break;}
							if(a instanceof JTextField) {
								JTextField a1 = (JTextField) a;
								if(a1.getText()!=null) {
								n = a1.getText();
								}
							}
							Component b =  line.getComponent(1);
							if(b instanceof JTextField) {
								JTextField b1 = (JTextField) b;
								if(b1.getText().length()!=0) {
								k = (int)Integer.parseInt(b1.getText());
								}
								else {
									k = 0;
								}
							}
							
							Component d =  line.getComponent(2);
							if(d instanceof JTextField) {
								JTextField c1 = (JTextField) d;
								if(c1.getText().length()!=0) {
								s = (int)Integer.parseInt(c1.getText());
								}else {
									s = 3;
								}
							}
							
							Component f =  line.getComponent(3);
							if(f instanceof JTextField) {
								JTextField f1 = (JTextField) f;
								if(f1.getText().length()!=0) {
								rep = (int)Integer.parseInt(f1.getText());
								}else {
									rep = 10;
								}
							}
							
							Component g =  line.getComponent(4);
							if(g instanceof JTextField) {
								JTextField g1 = (JTextField) g;
								if(g1.getText().length()!=0) {
								rp = (int) Integer.parseInt(g1.getText());
								}else {
									rp = 5;
								}
							}
							
							if(n.length()!=0 && n!=null) {
								int w = cons.guardaEntreno(user, fecha, n, k, s, rep,rp);
							}
						}
							
						}
						//if(nombre.getText().length()!=0) {
							//int w = cons.guardaEntreno(user, fecha, nombre.getText(), Integer.parseInt(kilos.getText()), Integer.parseInt(series.getText()), Integer.parseInt(repes.getText()), Integer.parseInt(RPE.getText()));
						//}
						
						try {
							dispose();
							EntrenoDiarioVista entrenoVista = EntrenoDiarioVista.getMiEntreno(fecha, user, true,nombreEntrenador);
							entrenoVista.setVisible(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						}
							
							
					

				
						
					
					
					
		
			});
		}
		cons.desconexion();
		return botonGuardar;
	}
	
	public void setFecha(String f) {
		fecha = f;
	}
	
	public void setNombre(String n) {
		user = n;
	}

}




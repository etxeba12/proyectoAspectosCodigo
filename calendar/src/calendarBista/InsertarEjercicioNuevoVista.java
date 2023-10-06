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
import java.util.Iterator;

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

	
	
	

		
		
	//preguntar xq nos pide que cambiemos la visibilidad
	public InsertarEjercicioNuevoVista(String nombre) throws SQLException {
		setTitle("Inserta un ejercicio"); //titulo de la pagina
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //que hacer en caso de cerrar la pesta�a
		setBounds(120, 120, 618, 309); // definimos tama�o del panel a mano
		entrenoInfo = new JPanel();
		this.entrenoInfo.setBackground(this.color1); //definimos color de fondo
		entrenoInfo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(entrenoInfo); //colocamos el panel dentro de la ventana principal.
		this.entrenoInfo.setLayout(null); //asi colocamos las cosas donde nosotros queremos
		{
			this.parteArriba = new JPanel();
			this.parteArriba.setBackground(this.color1); // ponemos mismo color para que no se note el panel
			this.parteArriba.setBounds(1, 5, 600, 35);
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
			titulo.setBounds(0, 0, 600, 120);
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
		this.tabla.add(this.linea);
		
		tabla.add(Box.createRigidArea(new Dimension(0, 6)));
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
					ConsultasDBModelo cons = new ConsultasDBModelo();
					boolean primera = true;
					int ind = 0;
					
					for(Component c : tabla.getComponents()) {
						if(primera) {
							primera = false;
							continue;
						}else if(ind==4){
							break;
						}
						if(c instanceof JPanel) {
							ind++;
							JPanel line = (JPanel) c;
							//System.out.println(line.getComponent(0));
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
							}
							
							Component d =  line.getComponent(2);
							if(d instanceof JTextField) {
								JTextField c1 = (JTextField) d;
								if(c1.getText().length()!=0) {
								s = (int)Integer.parseInt(c1.getText());
								}
							}
							
							Component f =  line.getComponent(3);
							if(f instanceof JTextField) {
								JTextField f1 = (JTextField) f;
								if(f1.getText().length()!=0) {
								rep = (int)Integer.parseInt(f1.getText());
								}
							}
							
							Component g =  line.getComponent(4);
							if(g instanceof JTextField) {
								JTextField g1 = (JTextField) g;
								if(g1.getText().length()!=0) {
								rp = (int) Integer.parseInt(g1.getText());
								}
							}
							
							if(n.length()!=0 && n!=null) {
							int w = cons.guardaEntreno(user, fecha, n, k, s, rep,rp);
							}
							//System.out.println(user+"\n"+fecha+"\n"+n+"\n"+k+"\n"+s+"\n"+rep+"\n"+rp);
							//System.out.println(line.getComponent(0));
						}
							
						}
					//System.out.println(user+"\n"+fecha+"\n"+nombre.getText()+"\n"+kilos.getText()+"\n"+series.getText()+"\n"+repes.getText()+"\n"+RPE.getText());
						if(nombre.getText().length()!=0) {
						int w = cons.guardaEntreno(user, fecha, nombre.getText(), Integer.parseInt(kilos.getText()), Integer.parseInt(series.getText()), Integer.parseInt(repes.getText()), Integer.parseInt(RPE.getText()));
						}
						try {
							EntrenoDiarioVista edv = EntrenoDiarioVista.getMiEntreno(fecha, user, true);
							edv.setVisible(true);
							dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							
							
					
					}
						
					
					
					
		
			});
		}
		return botonGuardar;
	}
	
	public void setFecha(String f) {
		fecha = f;
	}
	
	public void setNombre(String n) {
		user = n;
	}

}




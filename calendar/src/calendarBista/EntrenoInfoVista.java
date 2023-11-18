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
	private JPanel parteDeAbajo;
	private JButton botonGuardar;
	private String series;
	private String fecha;
	private String usuario;
	private int kilosRellenar;
	private int rpeRellenar;
		
	
	
	//preguntar xq nos pide que cambiemos la visibilidad
	EntrenoInfoVista(String pEjer, String pFecha, String pUsuario) throws SQLException {
		nombreEjer = pEjer;
		fecha = pFecha;
		usuario = pUsuario;
		setTitle("INFORMACION ENTRENO"); //titulo de la pagina
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
			this.parteArriba.add(getNombreEjercicio());
			
			
		}
		this.entrenoInfo.add(getEntrenamientos());
		ponerDatosGuardados();
		 
		setLocationRelativeTo(null);
	}
	
	public static EntrenoInfoVista getMiEntreno(String ejer, String fech, String usu) throws SQLException {
		if( miEntrenoInfo == null) {
			miEntrenoInfo = new EntrenoInfoVista(ejer, fech, usu);
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
		this.tabla = new JPanel(new GridLayout(Integer.parseInt(series) +  2,3));
		tabla.setLayout(new BoxLayout(tabla, BoxLayout.Y_AXIS)); //para que se ponga una linea debajo de la otra
		this.tabla.setBackground(this.color1); // ponemos mismo color para que no se note el panel
		this.tabla.setBounds(1, 45, 500, 220);
		Border borde = BorderFactory.createLineBorder(Color.black, 1);
		this.tabla.setBorder(borde);
		Integer i = 1;
		JLabel kgTitulo = new JLabel("Kilos");
		kgTitulo.setBounds(40, 0 ,100 , 25);
		kgTitulo.setForeground(azulCielo);
		JLabel repesTitulo = new JLabel("Repeticiones");
		repesTitulo.setBounds(110, 0 ,100 , 25);
		repesTitulo.setForeground(azulCielo);
		JLabel RPETitulo = new JLabel("RPE");
		RPETitulo.setBounds(230, 0 ,100 , 25);
		RPETitulo.setForeground(azulCielo);
		JLabel kgObjetivo = new JLabel("KilosObjetivo");
		kgObjetivo.setBounds(300, 0 ,100 , 25);
		kgObjetivo.setForeground(azulCielo);
		JLabel RPEObjetivo = new JLabel("RPEObjetivo");
		RPEObjetivo.setBounds(400, 0 ,100 , 25);
		RPEObjetivo.setForeground(azulCielo);
		this.linea = new JPanel(new FlowLayout(FlowLayout.LEFT)); // crear un panel horizontal para poner componentes seguidos
		this.linea.setBackground(new Color(217, 217, 217)); // Color de fondo personalizado ); // ponemos mismo color para que no se note el panel
		this.linea.setBounds(0, 0, 100, 25);
		this.linea.setLayout(null);
		this.tabla.add(this.linea); // a�adimos el Panel al principal panel
		this.linea.add(kgTitulo);
		this.linea.add(kgObjetivo);
		this.linea.add(repesTitulo);
		this.linea.add(RPETitulo);
		this.linea.add(RPEObjetivo);	    
		tabla.add(Box.createRigidArea(new Dimension(0, 5)));
		linea.setMaximumSize(new Dimension(Short.MAX_VALUE, linea.getPreferredSize().height));
		Component espacio = Box.createRigidArea(new Dimension(6, 0)); //creamos el espacio
		
		
		
		while(i <= Integer.parseInt(series)) {
			String reps = respuesta.getString("repeticiones");
			JLabel repes = new JLabel(reps);
			repes.setBounds(140, 0 ,100 , 25);
			repes.setForeground(azulCielo);
			
			JTextField kilos = new JTextField();
			kilos.setBounds(30, 0 , 50, 25);
			JTextField RPE = new JTextField();
			RPE.setBounds(220, 0 , 50, 25);
			
			JTextField kilosCliente = new JTextField();
		    kilos.setName("Kilos"); // Establece el nombre
		    kilos.setBounds(30, 0, 50, 25);
			String kgsObjetivo = respuesta.getString("kilos");
			JLabel kiloObjetivo = new JLabel(kgsObjetivo);
			kiloObjetivo.setBounds(330, 0 ,100 , 25);
			kiloObjetivo.setForeground(azulCielo);
			
			JTextField RPECliente = new JTextField();
		    RPE.setName("RPE"); // Establece el nombre
		    RPE.setBounds(220, 0, 50, 25);
			String rpeObjetivo = respuesta.getString("RPE");
			JLabel RPObjetivo = new JLabel(rpeObjetivo);
			RPObjetivo.setBounds(430, 0 ,100 , 25);
			RPObjetivo.setForeground(azulCielo);
			
			this.linea = new JPanel(new FlowLayout(FlowLayout.LEFT)); // crear un panel horizontal para poner componentes seguidos
			this.linea.setBackground(new Color(217, 217, 217)); // Color de fondo personalizado ); // ponemos mismo color para que no se note el panel
			this.linea.setBounds(0, 0, 100, 25);
			this.linea.setLayout(null);
			this.tabla.add(this.linea); // a�adimos el Panel al principal panel
			this.linea.add(kilos);
			this.linea.add(espacio);
			this.linea.add(repes);
			this.linea.add(espacio);
			this.linea.add(RPE);
			this.linea.add(kiloObjetivo);
			this.linea.add(RPObjetivo);
			tabla.add(Box.createRigidArea(new Dimension(0, 5))); //crear espacio entre jpanels
			linea.setMaximumSize(new Dimension(Short.MAX_VALUE, linea.getPreferredSize().height)); // que ocupe toda la ventana cada linea
			this.tabla.add(linea);
			i++;
		}
		this.linea = new JPanel(new FlowLayout(FlowLayout.LEFT)); // crear un panel horizontal para poner componentes seguidos
		this.linea.setBackground(new Color(217, 217, 217)); // Color de fondo personalizado ); // ponemos mismo color para que no se note el panel
		this.linea.setBounds(0, 0, 100, 25);
		this.linea.setLayout(null);
		tabla.add(Box.createRigidArea(new Dimension(0, 5))); //crear espacio entre jpanels
		linea.setMaximumSize(new Dimension(Short.MAX_VALUE, linea.getPreferredSize().height)); // que ocupe toda la ventana cada linea
		
		this.linea = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		this.linea.setBackground(new Color(217, 217, 217)); 
		this.linea.setBounds(0, 0, 100, 25);
		this.linea.setLayout(null);
		this.linea.add(getBotonGuardar());
		this.tabla.add(this.linea);
		
		tabla.add(Box.createRigidArea(new Dimension(0, 5)));
		linea.setMaximumSize(new Dimension(Short.MAX_VALUE, linea.getPreferredSize().height));
	    
		
		
		
		return tabla;
	}
	
	private JButton getBotonGuardar() {
	    if (botonGuardar == null) {
	        botonGuardar = new JButton();
	        botonGuardar.setBackground(azulCielo);
	        botonGuardar.setForeground(colorBlanco);
	        botonGuardar.setBounds(195, 0, 100, 25);
	        Border borde = BorderFactory.createLineBorder(Color.black, 1);
	        botonGuardar.setBorder(borde);
	        botonGuardar.setText(" GUARDAR ");
	        botonGuardar.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                guardarDatosEnBaseDeDatos();
	            }
	        });
	    }
	    return botonGuardar;
	}

	private void guardarDatosEnBaseDeDatos() {
	    try {
	        // Obt�n el nombre del ejercicio
	        String nombreEjercicio = getNombreEjercicio().getText();
	        int kilos = 0;
	        int rpe = 0;

	        // Itera sobre los paneles para obtener los valores de kilos y RPE
	        Component[] componentes = tabla.getComponents();
	        for (Component componente : componentes) {
	            if (componente instanceof JPanel) {
	                JPanel panel = (JPanel) componente;
	                Component[] lineComponents = panel.getComponents();
	                for (Component lineComponent : lineComponents) {
	                    if (lineComponent instanceof JTextField) {
	                        JTextField textField = (JTextField) lineComponent;

	                        // Obt�n el valor del JTextField
	                        String valor = textField.getText();

	                        // Actualiza la base de datos con el valor
	                        if(!valor.isEmpty()) {
	                        	if (textField.getName().equals("Kilos")) {
	                        		kilos = Integer.parseInt(valor);
	                        	} else if (textField.getName().equals("RPE")) {
	                        		rpe = Integer.parseInt(valor);
	                        	}
	                        }
	                    }
	                }
	            }
	        }
	        cd.actualizarKilosRPECliente(usuario, fecha, nombreEjercicio, kilos, rpe);
	        dispose();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	private void ponerDatosGuardados() throws SQLException {
		ResultSet datosCliente = cd.comprobarKilosRPECliente(usuario, fecha, nombreEjer);
		while (datosCliente.next()) {
			this.kilosRellenar = datosCliente.getInt("kilosCliente");
			this.rpeRellenar = datosCliente.getInt("RPECliente");
		}
		
		Component[] componentes = tabla.getComponents();
        for (Component componente : componentes) {
            if (componente instanceof JPanel) {
                JPanel panel = (JPanel) componente;
                Component[] lineComponents = panel.getComponents();
                for (Component lineComponent : lineComponents) {
                    if (lineComponent instanceof JTextField) {
                        JTextField textField = (JTextField) lineComponent;
                        if (textField.getName().equals("Kilos")) {
                    		textField.setText(Integer.toString(kilosRellenar));
                    	} else if (textField.getName().equals("RPE")) {
                    		textField.setText(Integer.toString(rpeRellenar));
                    	}

                    }
                }
            }
        }
		
		
        
	}
	
	

}

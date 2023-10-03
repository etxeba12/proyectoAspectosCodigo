package calendarBista;

import calendarModelo.ConsultasDBModelo;
import calendarModelo.Gestor;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;



public class CalendariVista extends JFrame implements Observer {
       
	
        private static CalendariVista calendario ;
        private Gestor g = Gestor.getGestor();
        private int ano ;
        private String m ;
        private Color azulCielo = new Color(31, 197, 203);
        private Color color1 = new Color(231, 231, 231);
        private Color blanco = new Color(255,255,255);
        private JPanel window;
        Controlador contr = new Controlador();
        private JButton previo ;
        private JButton siguiente ;
        private JButton logOut;
        private JLabel mes;
        private JLabel labelano;
        private JLabel nombreUsu;
        private JButton b;
        private JPanel parteArriba;
        private JPanel CalendarioDias;
        private String nombre;
        private ConsultasDBModelo db = new ConsultasDBModelo();


        public static CalendariVista getCalendario(int ano,String mes,String pNombre) throws SQLException{
            if(calendario==null){
            	calendario = new CalendariVista(ano,mes,pNombre);
            }else {
            	calendario.setAno(ano);
            	calendario.setMes(mes);
            }
            
            return calendario;
        }
        

    private CalendariVista(int ano, String mes,String pNombre) throws SQLException{
	    	this.ano = ano;
			this.m = mes;
			this.nombre = pNombre;
	    	setTitle("Calendario entreno"); //titulo de la pagina
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //que hacer en caso de cerrar la pesta�a
			setBounds(120, 120, 518, 309);
            window = new JPanel();
            this.window.setBackground(this.color1); //definimos color de fondo
            window.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(window); //colocamos el panel dentro de la ventana principal.
			window.setLayout(null);
			{
				this.parteArriba = new JPanel();
				this.parteArriba.setBackground(this.color1); // ponemos mismo color para que no se note el panel
				this.parteArriba.setBounds(1, 5, 500, 35);
				Border borde = BorderFactory.createLineBorder(Color.black, 1);
				this.parteArriba.setBorder(borde);
				this.parteArriba.setBackground(azulCielo);
				this.window.add(this.parteArriba); 
				this.parteArriba.setLayout(null);
				parteArriba.add(getUsuario());
				parteArriba.add(getLogOut());
				parteArriba.add(getPrevio());
		        parteArriba.add(getAno());
		        parteArriba.add(getMes());
		        parteArriba.add(getSiguiente());
			}
			window.add(getMesTabla());
            setLocationRelativeTo(null);
            Gestor.getGestor().addObserver(this);

    }


    private JButton getLogOut() {
		if(logOut == null) {
			logOut = new JButton();
			logOut.setBackground(this.azulCielo);
			logOut.setBorder(null);
			logOut.setBounds(445, 5, 50, 25);
			ImageIcon Original = new  ImageIcon(CalendariVista.class.getResource("/imagenes/logOut.png"));
			Image imagenRedimensionada = Original.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
			logOut.setIcon(new ImageIcon(imagenRedimensionada));
			logOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
			logOut.setFont(new Font("Tahoma", Font.PLAIN, 16));
			logOut.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					LoginVista lv = LoginVista.getLogin();
					lv.setVisible(true);
					 
				}
			});
		}
		return logOut;
	}


    private JButton getPrevio(){
        if(previo==null){
            previo = new JButton("");
            previo.setBackground(azulCielo);
            previo.setBorder(null);
            previo.setBounds(115, 5, 50, 25);
            ImageIcon icono = new ImageIcon(CalendariVista.class.getResource("/imagenes/atras.png"));
            Image imagenRedimensionada = icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            previo.setIcon(new ImageIcon(imagenRedimensionada));
            previo.setCursor(new Cursor(Cursor.HAND_CURSOR));
            previo.setFont(new Font("Tahoma", Font.PLAIN, 16));
            previo.addActionListener(contr);

        }
        return previo;
    }
    
    private JButton getSiguiente(){
        if(siguiente==null){
            
            siguiente = new JButton();
            siguiente.setBackground(azulCielo);
            siguiente.setBorder(null);
            siguiente.setBounds(305, 5, 50, 25);
            ImageIcon icono = new ImageIcon(CalendariVista.class.getResource("/imagenes/flecha-correcta.png"));
            Image imagenRedimensionada = icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            siguiente.setIcon(new ImageIcon(imagenRedimensionada));
            siguiente.setCursor(new Cursor(Cursor.HAND_CURSOR));
            siguiente.setFont(new Font("Tahoma", Font.PLAIN, 16));
            siguiente.addActionListener(contr);
        }
        return siguiente;
    }
    
    private JLabel getUsuario(){
        if (nombreUsu==null){
        	nombreUsu = new JLabel(this.nombre);
        	nombreUsu.setBounds(-10, 5, 100, 25);
        	nombreUsu.setFont(new Font("Tahoma", Font.PLAIN, 17));
        	nombreUsu.setForeground(blanco);
        	nombreUsu.setVerticalAlignment(SwingConstants.CENTER);
        	nombreUsu.setHorizontalAlignment(SwingConstants.CENTER);

        }
        return nombreUsu;
    }

    private JLabel getMes(){
        if(mes==null){
            mes = new JLabel();
            mes.setText(m);
            mes.setBounds(208, 5, 105, 25);
            mes.setForeground(blanco);
            mes.setFont(new Font("Tahoma", Font.PLAIN, 17));
            mes.setVerticalAlignment(SwingConstants.CENTER);
            mes.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return mes;
    }

    private JLabel getAno(){
        if (labelano==null){
        	labelano = new JLabel(Integer.toString(ano));
        	labelano.setBounds(138, 5, 100, 25);
        	labelano.setForeground(blanco);
        	labelano.setFont(new Font("Tahoma", Font.PLAIN, 17));
        	labelano.setVerticalAlignment(SwingConstants.CENTER);
        	labelano.setHorizontalAlignment(SwingConstants.CENTER);

        }
        return labelano;
    }

    public  void setAno(int a){
        this.ano = a;
    }
    public void setMes(String mes) {
    	this.m = mes;
    }
    
    public JPanel getMesTabla() throws SQLException {

    	CalendarioDias = new JPanel(new GridLayout(5 ,7));
		this.CalendarioDias.setBackground(this.color1); // ponemos mismo color para que no se note el panel
		this.CalendarioDias.setBounds(1, 45, 500, 220);
		Border borde = BorderFactory.createLineBorder(Color.black, 1);
		this.CalendarioDias.setBorder(borde);
		
		Calendar calen = Calendar.getInstance();
		calen.set(Calendar.YEAR, this.ano);
		calen.set(Calendar.MONTH, g.numeroDeMes(m) );
		int maxDias = calen.getActualMaximum(Calendar.DAY_OF_MONTH);
		ImageIcon icono = new ImageIcon(CalendariVista.class.getResource("/imagenes/pesa.png"));
        for (int i = 1; i <= maxDias; i++) {
        	 if(db.hayEntreno(crearFormatoFecha(String.valueOf(ano), m, String.valueOf(i) ), nombre)) {
        		 b = new JButton(icono);
        		 
        		 Image imagenRedimensionada = icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                 b.setIcon(new ImageIcon(imagenRedimensionada));
                 b.setText(Integer.toString(i));

             }
        	 else {
        		 b = new JButton(Integer.toString(i));
        		 
        	 }
        	 b.setVerticalTextPosition(SwingConstants.TOP);
        	 b.setHorizontalTextPosition(SwingConstants.CENTER);
        	 b.setHorizontalAlignment(SwingConstants.CENTER);
        	 b.setVerticalAlignment(SwingConstants.CENTER);
             b.setContentAreaFilled(false);
             b.addActionListener(contr);
             CalendarioDias.add(b);

        }   
    	
    	return CalendarioDias;
    	
    }
    

    public class Controlador implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

            if(e.getSource() == previo){
                String m = mes.getText();
                g.pasaAlAnterior(m,ano);

            }else if(e.getSource() == siguiente){
                String m = mes.getText();
                String a = labelano.getText();

                g.pasaAlSiguiente(m,ano);

            }else{
 
                String a = labelano.getText();
                String m = mes.getText();
                String d = ((AbstractButton) e.getSource()).getText();
                try {
                	calendario.dispose();
					EntrenoDiarioVista dl = new EntrenoDiarioVista(crearFormatoFecha(a, m, d),nombre);
					dl.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

            }

        }
    }

    private String crearFormatoFecha(String ano,String mes,String dia) {
    	String fecha ;
    	int numMes = g.numeroDeMes(mes) + 1;
    	if(numMes < 10) {
    		mes = "0" + numMes;    	
    	}else {
    		mes = "" + numMes;
    	} 
    	fecha = ano + "-" + mes + "-" + dia;
    	return fecha;
    	
    }


    @Override
    public void update(Observable o, Object arg) {
        m = g.getMes();
        ano = g.getAno();
        
        if (calendario != null) {
            g.deleteObserver(calendario); // Desregistra la instancia anterior
            calendario.dispose();
        }

        try {
			calendario = new CalendariVista(ano, m,this.nombre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        calendario.setVisible(true);
        g.addObserver(calendario); // Registra la nueva instancia
        
    }
}

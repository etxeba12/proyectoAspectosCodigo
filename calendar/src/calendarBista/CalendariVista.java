package calendarBista;

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
        private JPanel window;
        Controlador contr = new Controlador();
        private JButton previo ;
        private JButton siguiente ;
        private JLabel mes;
        private JLabel labelano;
        private JButton b;
        private JPanel parteArriba;
        private JPanel CalendarioDias;


        public static CalendariVista getCalendario(int ano,String mes){
            if(calendario==null){
            	calendario = new CalendariVista(ano,mes);
            }else {
            	calendario.setAno(ano);
            	calendario.setMes(mes);
            }
            
            return calendario;
        }
        

    private CalendariVista(int ano, String mes){
	    	this.ano = ano;
			this.m = mes;
	    	setTitle("Calendario entreno"); //titulo de la pagina
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //que hacer en caso de cerrar la pesta�a
			setBounds(120, 120, 500, 300);
            window = new JPanel();
            this.window.setBackground(this.color1); //definimos color de fondo
            window.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(window); //colocamos el panel dentro de la ventana principal.
			{
				this.parteArriba = new JPanel();
				this.parteArriba.setBackground(this.color1); // ponemos mismo color para que no se note el panel
				this.parteArriba.setBounds(0, 5, 500, 300);
				Border borde = BorderFactory.createLineBorder(Color.black, 1);
				this.parteArriba.setBorder(borde);
				this.parteArriba.setBackground(azulCielo);
				this.window.add(this.parteArriba); 
				 parteArriba.add(getPrevio());
		         parteArriba.add(getAno());
		         parteArriba.add(getMes());
		         parteArriba.add(getSiguiente());
			}
			window.add(getMesTabla());
            setLocationRelativeTo(null);
            Gestor.getGestor().addObserver(this);

    }





    private JButton getPrevio(){
        if(previo==null){
            previo = new JButton("");
            previo.setBackground(azulCielo);
            previo.setBorder(null);
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
            ImageIcon icono = new ImageIcon(CalendariVista.class.getResource("/imagenes/flecha-correcta.png"));
            Image imagenRedimensionada = icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            siguiente.setIcon(new ImageIcon(imagenRedimensionada));
            siguiente.setCursor(new Cursor(Cursor.HAND_CURSOR));
            siguiente.setFont(new Font("Tahoma", Font.PLAIN, 16));
            siguiente.addActionListener(contr);
        }
        return siguiente;
    }

    private JLabel getMes(){
        if(mes==null){
            mes = new JLabel();
            mes.setText(m);
            mes.setVerticalAlignment(SwingConstants.CENTER);
            mes.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return mes;
    }

    private JLabel getAno(){
        if (labelano==null){
        	labelano = new JLabel(Integer.toString(ano));
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
    
    // tablero de meses y actualizacion del tablero
    
    public JPanel getMesTabla() {

    	CalendarioDias = new JPanel(new GridLayout(4 ,7));
		this.CalendarioDias.setBackground(this.color1); // ponemos mismo color para que no se note el panel
		this.CalendarioDias.setBounds(0, 45, 500, 220);
		Border borde = BorderFactory.createLineBorder(Color.black, 1);
		this.CalendarioDias.setBorder(borde);
		
		Calendar calen = Calendar.getInstance();
		calen.set(Calendar.YEAR, this.ano);
		calen.set(Calendar.MONTH, g.numeroDeMes(m) );
		int maxDias = calen.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= maxDias; i++) {
            b = new JButton(Integer.toString(i));
            b.setContentAreaFilled(false);
            b.addActionListener(contr);
            CalendarioDias.add(b);

        }   
    	
    	return CalendarioDias;
    	
    }
    
    // tablero de meses y actualizacion del tablero

    public class Controlador implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

            if(e.getSource() == previo){
                String m = mes.getText();
                g.pasaAlAnterior(m,ano);

                //System.out.println("PREVIO");
            }else if(e.getSource() == siguiente){
                String m = mes.getText();
                String a = labelano.getText();

               // String fecha = a+"-"+m+"-"+d;
                g.pasaAlSiguiente(m,ano);
                //g.calcularDiaSiguiente(f);

            }else{
                //window.setVisible(false);
                String a = labelano.getText();
                String m = mes.getText();
                String d = ((AbstractButton) e.getSource()).getText();
                try {
                	setVisible(false);
					EntrenoDiarioVista dl = new EntrenoDiarioVista(crearFormatoFecha(a, m, d));
					dl.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                //g.abreEntrenamiento("");

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
            calendario.dispose();
        }
        // Crear una nueva instancia de CalendariVista 
        setVisible(false);
        
        calendario = new CalendariVista(ano, m);
        calendario.setVisible(true);
        

    }
}

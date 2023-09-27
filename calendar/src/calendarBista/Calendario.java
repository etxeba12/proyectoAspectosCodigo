package calendarBista;

import calendarModelo.Gestor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import javax.swing.border.Border;



public class Calendario extends JFrame implements Observer {
       /* String[] meses = {"JANUARY","FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST",
            "SEPTEMBER", "OCTOBER", "NOVEMBER", "DICEMBER"};*/
        private static Calendario c = new Calendario();

        private int year = LocalDate.now().getYear();
        private Date fecha = new Date();
        private String f = fecha.toString();
        private int ano = year;
        private String month = LocalDate.now().getMonth().toString();
        private int mesnumero = LocalDate.now().getMonthValue();
        private int mesn = mesnumero;
        private String m = month;
        JFrame window;
        Controlador contr = new Controlador();
        private JButton previo ;
        private JButton siguiente ;
        private JLabel mes;
        private JLabel labelaño;
        private JButton b;
        private JPanel panel2;

        public static Calendario getCalendario(){
            if(c==null){
                c = new Calendario();
            }
            return c;
        }


    private Calendario(){

            window = new JFrame("INIT");
            window.setSize(600, 400);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setLayout(new GridLayout(2, 1, 0, 0));

            JPanel panel1 = new JPanel();
            window.add(panel1);
            panel1.setLayout(new GridLayout(1, 4, 5, 0));

            this.getPrevio();
            this.getAño();
            this.getMes();
            this.getSiguiente();


            panel1.add(previo);
            panel1.add(labelaño);
            panel1.add(mes);
            panel1.add(siguiente);

            panel2 = new JPanel();
            panel2.setLayout(new GridLayout(4, 7, 0, 0));
            window.add(panel2);



            for (int i = 1; i < 32; i++) {
                b = new JButton(Integer.toString(i));
                b.setContentAreaFilled(false);
                b.addActionListener(contr);
                panel2.add(b);
            }


            window.setVisible(true);
            Gestor.getGestor().addObserver(this);







       // Border borde = BorderFactory.createLineBorder(Color.BLACK,2);
        /*for(int i=1;i<=12;i++){
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(4,7,0,0));
            panel.setBorder(borde);


            for(int j=1;j<32;j++) {
                JLabel label = new JLabel(Integer.toString(j));
                panel.add(label);
                window.add(panel);
            }

        }*/
       /*if(m.equals(LocalDate.now().getMonth().toString())){
            window.setVisible(true);*/
    }





    private JButton getPrevio(){
        if(previo==null){
            previo = new JButton("");
            ImageIcon icono = new ImageIcon("src/imágenes/atrás.png");
            previo.addActionListener(contr);
            previo.setOpaque(false);
            previo.setContentAreaFilled(false);
            previo.setPreferredSize(new Dimension(37, 25));
            previo.setIcon(icono);

        }
        return previo;
    }

    private JLabel getMes(){
        if(mes==null){
            mes = new JLabel();
            //mes.setText(m);
            mes.setText(m);
            mes.setVerticalAlignment(SwingConstants.CENTER);
            mes.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return mes;
    }

    private JLabel getAño(){
        if (labelaño==null){
            labelaño = new JLabel(Integer.toString(ano));
            labelaño.setVerticalAlignment(SwingConstants.CENTER);
            labelaño.setHorizontalAlignment(SwingConstants.CENTER);

        }
        return labelaño;
    }
    private JButton getSiguiente(){
        if(siguiente==null){
            ImageIcon icon1 = new ImageIcon("src/imágenes/adelante.png");
            siguiente = new JButton();
            siguiente.addActionListener(contr);
            siguiente.setPreferredSize(new Dimension(37, 25));
            siguiente.setContentAreaFilled(false);
            siguiente.setIcon(icon1);
            siguiente.setOpaque(false);
        }
        return siguiente;
    }

   /* public void crea(){
        for(String m : meses){
            Calendario c = new Calendario(m);
        }
    }*/

    public  void setAno(int a){
        this.ano = a;
    }

    public class Controlador implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            Gestor g =  Gestor.getGestor();

            if(e.getSource() == previo){
                String m = mes.getText();
                g.pasaAlAnterior(m,ano);

                //System.out.println("PREVIO");
            }else if(e.getSource() == siguiente){
                String m = mes.getText();
                String a = labelaño.getText();

               // String fecha = a+"-"+m+"-"+d;
                g.pasaAlSiguiente(m,ano);
                //g.calcularDiaSiguiente(f);

            }else{
                //window.setVisible(false);
                String a = labelaño.getText();
                String m = mes.getText();
                String d = b.getText();
                System.out.println(d);
                g.abreEntrenamiento("");

            }



        }
    }





    @Override
    public void update(Observable o, Object arg) {
        Gestor g = Gestor.getGestor();
        getMes().setText(g.getMes());
        getAño().setText(Integer.toString(g.getAno()));

        //System.out.println(g.getMes());
        //System.out.println(g.getAno());



    }
}

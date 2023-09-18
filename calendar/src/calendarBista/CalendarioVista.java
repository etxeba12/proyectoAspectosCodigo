package calendarBista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class CalendarioVista extends JFrame {

    private JPanel contentPane;
    private JLabel lblNewLabel;
    private JPanel panelCentral;
    private JPanel panelSiguiente;
    private JPanel panelAnterior;
    private String[] meses = {"ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"};
    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	CalendarioVista frame = new CalendarioVista();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CalendarioVista() {
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.add(getLblNewLabel(), BorderLayout.NORTH);
        
        // Añade los paneles Anterior y Siguiente usando GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(getPanelAnterior(), BorderLayout.WEST);
        contentPane.add(getPanelSiguiente(), BorderLayout.EAST);
        
        contentPane.add(getPanelCentral(), BorderLayout.CENTER);
    }

    private JLabel getLblNewLabel() {
        if (lblNewLabel == null) {
            lblNewLabel = new JLabel("SEPTIEMBRE");
            lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
            lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
        }
        return lblNewLabel;
    }

    private JPanel getPanelAnterior() {
        if (panelAnterior == null) {
        	 panelAnterior = new JPanel();
             panelAnterior.setLayout(new GridBagLayout());
             String mesAnterior = calcularMesAnterior(lblNewLabel.getText());
             JButton btnAnterior = new JButton(mesAnterior);
             panelAnterior.add(btnAnterior, new GridBagConstraints());
         }
        return panelAnterior;
    }

    private JPanel getPanelSiguiente() {
        if (panelSiguiente == null) {
        	panelSiguiente = new JPanel();
        	panelSiguiente.setLayout(new GridBagLayout());
        	String mesSiguiente = calcularMesSiguiente(lblNewLabel.getText());
            JButton btnSiguiente = new JButton(mesSiguiente);
            panelSiguiente.add(btnSiguiente, new GridBagConstraints());
        }
        return panelSiguiente;
    }

    private JPanel getPanelCentral() {
        if (panelCentral == null) {
            panelCentral = new JPanel();
            panelCentral.setLayout(new GridLayout(5, 7)); // 5 filas y 7 columnas
            // Agregar 30 botones (puedes ajustar esto según el mes)
            int j = 1;
            for (int i = 1; i <= 35; i++) {
            	if(i > 30) {
            		JButton button = new JButton(Integer.toString(j));
                    panelCentral.add(button);
                    button.setEnabled(false);
                    j++;
            	}else {
            		JButton button = new JButton(Integer.toString(i));
            		panelCentral.add(button);
            	}
               
                
                
            }
        }
        return panelCentral;
    }
    private String calcularMesAnterior(String mes) {
    	String mesAnterior= "";
    	for (String mess : meses) {
    		
    		if(mess.equals(mes)) {
    			return mesAnterior;
    		}
    		mesAnterior = mess;
    		
			
		}
    	return null;
    	
    }
    private String calcularMesSiguiente(String mes) {
    	String mesSiguiente= "";
    	for (int i = meses.length - 1; i >= 0; i--) {
    		if(mes.equals(meses[i])) {
    			
    			mesSiguiente = meses[i+1];
    			return mesSiguiente;
    	}
			
		}
    	return null;
    	
    }
}

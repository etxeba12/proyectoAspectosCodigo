package exceptions;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ExceptionModificable extends Exception{
	//Atributuak
	private JPanel ventana;
	private String mensaje;
	
	
	//Eraikitzailea
	public ExceptionModificable(JPanel pVentana,String pMensaje) {
		super();
		this.ventana = pVentana;
		this.mensaje = pMensaje;
	}
	
	
	//Metodoak
	public void imprimirMensaje() {
		JOptionPane.showMessageDialog(ventana, this.mensaje);
	}

}

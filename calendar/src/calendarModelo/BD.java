package calendarModelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class BD {

	private Connection conexion = null;
    private Statement consulta = null;
    private ResultSet rs = null;
    
    public void Conectar() {
    	
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/gymcalendar","root","");
            
        } catch(SQLException e) {
        	
            JOptionPane.showMessageDialog(null, "Error al cargar la base de datos");
            e.printStackTrace();
            
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar driver");
        }
    }
    
    public int SetInformacion(String instrucción) {
    	
    	Conectar();
    	int validar = 0;
    	
    	try {
    		if(conexion == null) {
    			JOptionPane.showMessageDialog(null, "conexión nula");
    		}
    		consulta = conexion.createStatement();
    		validar = consulta.executeUpdate(instrucción);
    		
    		return validar;
    				
    	}catch (Exception e) {
    		JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
    		e.printStackTrace();
    		
    		return validar;
			// TODO: handle exception
		}
    }
	
}

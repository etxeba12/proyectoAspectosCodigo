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
            //conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gymcalendar", "root", "");

            
        } catch(SQLException e) {
        	
            JOptionPane.showMessageDialog(null, "Error al cargar la base de datos");
            e.printStackTrace();
            
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar driver");
        }
    }
    
    public int SetInformacion(String instruccion) {
    	
    	Conectar();
    	int validar = 0;
    	
    	try {
    		if(conexion == null) {
    			JOptionPane.showMessageDialog(null, "conexion nula");
    		}
    		consulta = conexion.createStatement();
    		validar = consulta.executeUpdate(instruccion);
    		
    		return validar;
    				
    	}catch (Exception e) {
    		JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
    		e.printStackTrace();
    		
    		return validar;
			// TODO: handle exception
		}
    }
    
    public ResultSet obtenerDatos(String instruccion) {
    	
    	Conectar();
    	ResultSet validar = null;
    	
    	try {
    		if(conexion == null) {
    			JOptionPane.showMessageDialog(null, "conexion nula");
    		}
    		consulta = conexion.createStatement();
    		validar = consulta.executeQuery(instruccion);
    		
    		return validar;
    				
    	}catch (Exception e) {
    		JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
    		e.printStackTrace();
    		
    		return validar;
			// TODO: handle exception
		}
    	
    }
    
	
}

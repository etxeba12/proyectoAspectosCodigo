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
            //conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/gymcalendar", "root", "");

            
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
    public void desconexion() throws SQLException {
    	if(this.conexion != null) {
    		this.conexion.close();
    	}
    	if(this.consulta != null) {
    		this.consulta.close();
    	}
    	if(this.rs != null) {
    		this.rs.close();
    	}
    	
    }
    public void cantidadConexiones() throws SQLException {
    	
    	Conectar();
    	int i= 0;
    	Statement statement = conexion.createStatement();

        String sql = "SELECT COUNT(*) AS connection_count FROM information_schema.processlist";
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            int connectionCount = resultSet.getInt("connection_count");
            System.out.println("Número de conexiones actuales: " + connectionCount);
            //System.out.println("La conexion es: " + resultSet.getString(i));
            i++;
        }
    }
    
    
	
}

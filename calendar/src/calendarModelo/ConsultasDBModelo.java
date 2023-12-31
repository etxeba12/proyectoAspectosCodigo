package calendarModelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ConsultasDBModelo extends BD{

	public int Guardar(String Usuario, String Contrasena) {
		
		return SetInformacion("INSERT INTO `gymcalendar`.`clientes`(`nombre`,`contrasena`) VALUES ('"+Usuario +"','"+Contrasena+"');");
	}
	
	public int guardaEntreno(String user, String fecha, String nombre, int kilos, int series, int repes, int rpe) {
		return SetInformacion("INSERT INTO `gymcalendar`.`calendario`(`fecha`,`nombreEjercicio`,`series`,`repeticiones`,`RPE`,`kilos`,`cliente_nombre`) VALUES ('"+fecha+"','"+nombre+"','"+series+"','"+repes+"','"+rpe+"','"+kilos+"','"+user+"');");
	}
	
	public int actualizarKilosRPECliente(String usuario, String fecha, String nombreEjercicio, String kilosCliente,String rpeCliente) throws SQLException {
	    return SetInformacion("UPDATE `calendario` SET `KilosCliente` = '"+kilosCliente+"', `RPECliente` = '"+rpeCliente+"' WHERE cliente_nombre = '"+usuario+"' AND `nombreEjercicio` = '"+nombreEjercicio+"' AND `fecha` = '"+fecha+"'");
	}
	
	public ResultSet comprobarKilosRPECliente(String usuario, String fecha, String nombreEjercicio) throws SQLException {
		ResultSet respuesta = obtenerDatos("SELECT KilosCliente, RPECliente FROM `calendario` WHERE cliente_nombre = '"+usuario+"' AND fecha = '"+fecha+"' AND nombreEjercicio = '"+nombreEjercicio+"';");
		return respuesta;
	}

	
	public boolean comprobarUsuario(String usuario) throws SQLException {
		
		ResultSet respuesta = obtenerDatos("SELECT * FROM `clientes` WHERE nombre = '"+usuario+"';");
		if(respuesta.next()) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean comprobarEntrenador(String pnombre, String pContrasena) throws SQLException {
		ResultSet respuesta = obtenerDatos("SELECT * FROM `entrenadores` WHERE nombre = '"+pnombre+"' AND contrasena = '"+pContrasena+"' ;");
		if(respuesta.next()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean loginValido(String usuario, String Contrasena) throws SQLException {

		ResultSet respuesta = obtenerDatos("SELECT * FROM `clientes` WHERE nombre = '"+usuario+"' AND contrasena = '"+Contrasena+"';");
		if(respuesta.next()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public ResultSet conseguirEntreno(String fecha,String pNombre) throws SQLException {
		ResultSet respuesta = obtenerDatos("SELECT * FROM `calendario` WHERE fecha='"+fecha+"' AND cliente_nombre = '"+pNombre+"' ; ");
		return respuesta;
	}
	
	public ResultSet conseguirInfoEntreno(String pNombre,String pFecha, String pNombreUsuario) throws SQLException{
		ResultSet respuesta = obtenerDatos("SELECT `series`, `repeticiones`, `RPE`, `kilos` FROM `calendario` WHERE nombreEjercicio = '"+pNombre+"' AND fecha = '"+pFecha+"' AND cliente_nombre = '"+pNombreUsuario+"'; ");
		return respuesta;
	}

	public ResultSet conseguirClientes(String pNombre) throws SQLException{
		ResultSet respuesta = obtenerDatos("SELECT `nombre` FROM `clientes` WHERE entrenador_nombre = '"+pNombre+"'; ");
		return respuesta;
	}

	
	
	public boolean hayEntreno(String fecha,String pNombre) throws SQLException {
		
		ResultSet respuesta = obtenerDatos("SELECT * FROM `calendario` WHERE fecha='"+fecha+"' AND cliente_nombre = '"+pNombre+"' ; ");
		if(respuesta.next()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int setMaximasConexiones() {
		
		return SetInformacion("SET GLOBAL max_connections = 1500;");

		
	}
	
}

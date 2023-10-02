package calendarModelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ConsultasDBModelo extends BD{

	public int Guardar(String Usuario, String Contrasena) {
		
		return SetInformacion("INSERT INTO `gymcalendar`.`usuarios`(`nombre`,`contraseña`) VALUES ('"+Usuario +"','"+Contrasena+"');");
	}
	
	public boolean comprobarUsuario(String usuario) throws SQLException {
		
		ResultSet respuesta = obtenerDatos("SELECT * FROM `usuarios` WHERE nombre = '"+usuario+"';");
		if(respuesta.next()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean loginValido(String usuario, String Contrasena) throws SQLException {
		ResultSet respuesta = obtenerDatos("SELECT * FROM `usuarios` WHERE nombre = '"+usuario+"' AND contraseña = '"+Contrasena+"';");
		if(respuesta.next()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public ResultSet conseguirEntreno(String fecha) throws SQLException {
		ResultSet respuesta = obtenerDatos("SELECT * FROM `ejercicios` WHERE fecha='"+fecha+"'; ");
		return respuesta;
	}
	
	public ResultSet conseguirInfoEntreno(String pNombre) throws SQLException{
		ResultSet respuesta = obtenerDatos("SELECT `series`, `repes` FROM `ejercicios` WHERE nombre = '"+pNombre+"'; ");
		return respuesta;
	}
	
	/*
	public int actualizarInfo(String kilos, String RPE,String pNombre) {
		
		return SetInformacion("INSERT INTO `gymcalendar`.`ejercicios`(`kilos`,`RPE`) VALUES ('"+kilos +"','"+RPE+"') WHERE nombre = '"+pNombre+"';");
	}
	*/
	
}

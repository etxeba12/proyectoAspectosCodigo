package calendarModelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ConsultasDBModelo extends BD{

	public int Guardar(String Usuario, String Contraseņa) {
		
		return SetInformacion("INSERT INTO `gymcalendar`.`usuarios`(`nombre`,`contraseņa`) VALUES ('"+Usuario +"','"+Contraseņa+"');");
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
	
	public boolean loginValido(String usuario, String contraseņa) throws SQLException {
		ResultSet respuesta = obtenerDatos("SELECT * FROM `usuarios` WHERE nombre = '"+usuario+"' AND contraseņa = '"+contraseņa+"';");
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
}

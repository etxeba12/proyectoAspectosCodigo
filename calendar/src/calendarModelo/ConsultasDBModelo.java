package calendarModelo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasDBModelo extends BD{

	public int Guardar(String Usuario, String Contraseña) {
		
		return SetInformacion("INSERT INTO `gymcalendar`.`usuarios`(`nombre`,`contraseña`) VALUES ('"+Usuario +"','"+Contraseña+"');");
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
	
	public boolean loginValido(String usuario, String contraseña) throws SQLException {
		ResultSet respuesta = obtenerDatos("SELECT * FROM `usuarios` WHERE nombre = '"+usuario+"' AND contraseña = '"+contraseña+"';");
		if(respuesta.next()) {
			return true;
		}
		else {
			return false;
		}
		
	}
}

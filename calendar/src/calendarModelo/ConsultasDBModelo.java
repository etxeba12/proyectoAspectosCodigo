package calendarModelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ConsultasDBModelo extends BD{

	public int Guardar(String Usuario, String Contrase�a) {
		
		return SetInformacion("INSERT INTO `gymcalendar`.`usuarios`(`nombre`,`contrase�a`) VALUES ('"+Usuario +"','"+Contrase�a+"');");
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
	
	public boolean loginValido(String usuario, String contrase�a) throws SQLException {
		ResultSet respuesta = obtenerDatos("SELECT * FROM `usuarios` WHERE nombre = '"+usuario+"' AND contrase�a = '"+contrase�a+"';");
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
}

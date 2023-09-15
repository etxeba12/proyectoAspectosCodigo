package calendarModelo;

import java.sql.ResultSet;

public class RegistroModelo extends BD{

	public int Guardar(String Usuario, String Contraseña) {
		
		return SetInformacion("INSERT INTO `gymcalendar`.`usuarios`(`nombre`,`contraseña`) VALUES ('"+Usuario +"','"+Contraseña+"');");
	}
	
	
}

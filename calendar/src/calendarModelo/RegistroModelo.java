package calendarModelo;

import java.sql.ResultSet;

public class RegistroModelo extends BD{

	public int Guardar(String Usuario, String Contrase�a) {
		
		return SetInformacion("INSERT INTO `gymcalendar`.`usuarios`(`nombre`,`contrase�a`) VALUES ('"+Usuario +"','"+Contrase�a+"');");
	}
	
	
}

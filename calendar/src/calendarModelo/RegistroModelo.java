package calendarModelo;

public class RegistroModelo extends BD{

	public int Guardar(String Usuario, String Contraseņa) {
		
		return SetInformacion("INSERT INTO `gymcalendar`.`usuarios`(`nombre`,`contraseņa`) VALUES ('"+Usuario +"','"+Contraseņa+"');");
	}
}

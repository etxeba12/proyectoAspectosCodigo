README

1. Link de la documentaci�n de nuestro proyecto

	wikidot: http://wikical.wikidot.com/blog:_start/tag/inicio/category/blog

2. Pasos para poner en marcha nuestro proyecto

	Lo primero que tendr�is que hacer es ir a este enlace de GitHub que os dejamos aqu�: https://github.com/etxeba12/proyectoAspectosCodigo . En �l encontrar�is el c�digo que tendr�is que importar en eclipse y la base de datos (el archivo SQL) que
	tendr�is que importar en phpMyAdmin. Aqu� os dejamos los enlaces para descargar el XAMPP (aplicaci�n que te permite usar phpMyAdmin) y eclipse.
	
		XAMPP:  https://www.apachefriends.org/es/index.html
	
		Eclipse: https://www.eclipse.org/downloads/
	
	Tras instalar el XAMPP tendr�is que ejecutarlo, e inicializar los siguientes m�dulos: Apache y MySQL. Despu�s, tendr�is que clicar en el bot�n de �Admin� del m�dulo MySQL que os abrir� una pesta�a en internet de phpMyAdmin(el puerto utilizado, deber� ser el 3306). Ah� tendr�is que crear una base de datos llamada �gymcalendar�, y tendr�is que importar la base de datos, clicando en el bot�n importar situado en la parte superior central de la pantalla.
	
	Una vez importada la base de datos, ejecutaremos la aplicaci�n de eclipse. En �l tendr�is que hacer los siguientes pasos, para poder importar la aplicaci�n desde GitHub. File -> import -> git -> Projects from Git (with smart import) -> clone URI -> y metemos los siguientes datos:
	
		URI: https://github.com/etxeba12/proyectoAspectosCodigo.git
		
		User: tu_usuario_de_git
	
		Contrase�a: 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
	
	Tras estos pasos, solo faltara ejecutar la clase LoginVista.java. El caso de que el puerto ese est� ocupado, deber�is usar estos dos comandos en el CMD en modo administrador:
	
		1. netstat -ano | find "3306"
	
		2. taskkill /F /PID el_PID_logrado_en_el_comando_anterior
	

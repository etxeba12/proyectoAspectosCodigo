README

1. Link de la documentación de nuestro proyecto

	wikidot: http://wikical.wikidot.com/blog:_start/tag/inicio/category/blog

2. Pasos para poner en marcha nuestro proyecto

	Lo primero que tendréis que hacer es ir a este enlace de GitHub que os dejamos aquí: https://github.com/etxeba12/proyectoAspectosCodigo . En él encontraréis el código que tendréis que importar en eclipse y la base de datos (el archivo SQL) que
	tendréis que importar en phpMyAdmin. Aquí os dejamos los enlaces para descargar el XAMPP (aplicación que te permite usar phpMyAdmin) y eclipse.
	
		XAMPP:  https://www.apachefriends.org/es/index.html
	
		Eclipse: https://www.eclipse.org/downloads/
	
	Tras instalar el XAMPP tendréis que ejecutarlo, e inicializar los siguientes módulos: Apache y MySQL. Después, tendréis que clicar en el botón de “Admin” del módulo MySQL que os abrirá una pestaña en internet de phpMyAdmin(el puerto utilizado, deberá ser el 3306). Ahí tendréis que crear una base de datos llamada “gymcalendar”, y tendréis que importar la base de datos, clicando en el botón importar situado en la parte superior central de la pantalla.
	
	Una vez importada la base de datos, ejecutaremos la aplicación de eclipse. En él tendréis que hacer los siguientes pasos, para poder importar la aplicación desde GitHub. File -> import -> git -> Projects from Git (with smart import) -> clone URI -> y metemos los siguientes datos:
	
		URI: https://github.com/etxeba12/proyectoAspectosCodigo.git
		
		User: tu_usuario_de_git
	
		Contraseña: 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
	
	Tras estos pasos, solo faltara ejecutar la clase LoginVista.java. El caso de que el puerto ese esté ocupado, deberéis usar estos dos comandos en el CMD en modo administrador:
	
		1. netstat -ano | find "3306"
	
		2. taskkill /F /PID el_PID_logrado_en_el_comando_anterior
	

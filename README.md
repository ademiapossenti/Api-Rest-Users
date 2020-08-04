# API Rest-Users

La siguiente aplicacion Rest presenta una aplicacion de usuarios.
La misma posee seguridad basica y verificacion mediante Json Web Token (JWT).

La aplicacion es una aplicacion realizada en Java utilizando el framework de spring boot y pruebas unitarias con Junit y Mockito (utilzando las librerias internas del framework mayoritariamente).


INICIAR LA APLICACION
=====================


Para probar la aplicacion se debe realizar los siguientes pasos

--> Ir a la carpeta Api-Rest-Users/binarios/
--> Ejecutar el jar con java -jar
--> Probar la apliacion.

PROBAR LA APLICACION
=====================

Para probar la aplicacion se deben seguir los siguientes pasos:

--> Abrir el Postman ubicado en Api-Rest-Users/documentacion
--> Ejecutar POST /login con user y password habilitados.
--> Tomar el token que devuelve la operacion
--> Utilizar el token en cualquiera de las otras operaciones tanto para obtener usuarios, crear etc y enviar el token en el header llamado "Authorization" y el valor agregarle Bearer al token, quedando Bearer token_mock



CONFIGURACIONES Y VALORES PREDETERMINADOS
==========================================
Para poder probar la aplicacion se definieron dos usuarios con distintos roles uno admin y el otro user. 
Actualmente la aplicacion solo puede trabajar con roles user. Por lo que el user que tiene ese rol es "ademia" y su password es 123
Si se necesitan mas roles solo se deben insertar en la base de datos manualmente.


DOCUMENTACION Y DIAGRAMAS ADJUNTOS DE LA APLICACION
===================================================

La documentacion adjunta se encuentra en Api-Rest-Users/documentacion






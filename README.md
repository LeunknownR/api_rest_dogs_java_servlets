# API REST SOBRE PERROS CON JAVA SERVLETS

Una API REST sobre perros desarrollada en Java Servlets con base de datos MySQL.

## Sobre el proyecto

* La api tiene 2 endpoints /api/dogs y /api/login
/api/dogs: El cual permite realizar 5 tareas: traer registros, buscar un registro, crear un registro, actualizar un registro o eliminar un registro,
a trav�s del uso sem�ntico de los m�todos HTTP, previamente autenticado con un token.
/api/login: Para poder conseguir el token que permitir� consumir los dem�s recursos de la API.
* Se utiliz� la clase HttpServlet para conseguir el enrutamiento de estos endpoints.
* Se utiliz� la librer�a com.google.code.gson para el manejo de datos en JSON.
* Se asegur� la API con la tecnolog�a de Json Web Token empleando la librer�a io.jsonwebtoken, con ayuda de la interfaz filter del paquete javax.servlet, la cual funciona emulando un middleware de autenticaci�n.
* Se usaron env-entries emulando variables de entorno en el archivo de configuraci�n web.xml, para almacenar datos privados como usuarios o contrase�as.
* Se utiliz� la librer�a mysql-connector-java para realizar las conexiones pertinentes a la base de datos en MySQL.

# API REST DOGS WITH JAVA SERVLETS

A API REST about dogs developed in Java Servlets and with MySQL database.

## About the project

* The api has 2 endpoints /api/dogs and /api/login
/api/dogs: Which allows you to perform 5 tasks: fetch records, search for a record, create a record, update a record or delete a record,
through the semantic use of HTTP methods, previously authenticated with a token.
/api/login: To be able to get the token that will allow consuming the other API resources.
* The HttpServlet class was used to achieve routing for these endpoints.
* The com.google.code.gson library was used to manage data in JSON.
* The API was secured with Json Web Token technology using the io.jsonwebtoken library, with the help of the filter interface of the javax.servlet package, which works by emulating an authentication middleware.
* env-entries were used emulating environment variables in the web.xml configuration file, to store private data such as usernames or passwords.
* The mysql-connector-java library was used to make the relevant connections to the MySQL database.
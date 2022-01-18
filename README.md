# API REST SOBRE PERROS CON JAVA SERVLETS

Una API REST sobre perros desarrollada en Java Servlets con base de datos MySQL.

## Sobre el proyecto

* La API tiene 2 endpoints /api/dogs y /api/login
/api/dogs: El cual permite realizar 5 tareas: traer registros, buscar un registro, crear un registro, actualizar un registro o eliminar un registro,
a través del uso semántico de los métodos HTTP, previamente autenticado con un token.
/api/login: Para poder conseguir el token que permitirá consumir los demás recursos de la API.
* Se utilizó la clase HttpServlet para conseguir el enrutamiento de estos endpoints.
* Se utilizó la librería com.google.code.gson para el manejo de datos en JSON.
* Se aseguró la API con la tecnología de Json Web Token empleando la librería io.jsonwebtoken, con ayuda de la interfaz filter del paquete javax.servlet, la cual funciona emulando un middleware de autenticación.
* Se usaron env-entries emulando variables de entorno en el archivo de configuración web.xml, para almacenar datos privados como usuarios o contraseñas.
* Se utilizó la librería mysql-connector-java para realizar las conexiones pertinentes a la base de datos en MySQL.

## **Endpoints**

### **Grupo - Dogs**
---
#### **GET**: /api/dogs?all=*{all}*&&id=*{id}*

* **Descripción** 

Trae los registros de los perros.

* **Parámetros de ruta**

|  Parameter      |      Tipo     | Opcional |   Descripción |
| --------------- | ------------- | -------- | --------------|
|       all       |    boolean    |   true   | Indica si se trae todos los registros, incluidos los perros llevados. Si no se ingresa o el valor no coincide con el tipo de dato se toma como **false**.
|       id        |      int      |   true   | Indica el registro que se desea buscar de los perros.

* **Headers de la petición**

    - Authorization: bearer {token}
        * Token de autenticación.
    - Accept: {accept}
        * Tipo de formato que se envía.

* **Body de la petición**

    - No hay parámetros del body.

#### **POST**: /api/dogs/

* **Descripción** 

Agrega un registro más a los perros.

* **Parámetros de ruta**

    - No hay parámetros de ruta.

* **Headers de la petición**

    - Authorization: bearer {token}
        * Token de autenticación.
    - Content-Type: {contentType}
        * Tipo de formato del body de la petición
    - Accept: {accept}
        * Tipo de formato que se envía.

* **Body de la petición**

|  Parameter      |      Tipo     | Opcional |   Descripción |
| --------------- | ------------- | -------- | ------------- |
|    idRaceDog    |      int      |   false  | Id de la raza del perro.
|    idSizeDog    |      int      |   false  | Id del tipo de tamaño de perro.
|       name      |     string    |   false  | Nombre del perro.
|   description   |     string    |   false  | Descripción del perro.
|      owner      |     string    |   false  | Dueño del perro.
|      weight     |     double    |   false  | Peso del perro.


Ejemplo:

```javascript
{
    "idRaceDog": 1,
    "idSizeDog": 9, 
    "name": "Scooby",
    "description": "Un perro muy miedoso y glotón.",
    "owner": "Shaggy",
    "weight": 78.9
}
```

#### **PUT**: /api/dogs/

* **Descripción** 

Actualiza los datos del registro de un perro.

* **Parámetros de ruta**
    - No hay parámetros de ruta.

* **Headers de la petición**

    - Authorization: bearer {token}
        * Token de autenticación.
    - Content-Type: {contentType}
        * Tipo de formato del body de la petición
    - Accept: {accept}
        * Tipo de formato que se envía.

* **Body de la petición**

|  Parameter      |      Tipo     | Opcional |   Descripción |
| --------------- | ------------- | -------- | ------------- |
|       id        |      int      |   false  | Id del perro.
|    idRaceDog    |      int      |   true   | Id de la raza del perro.
|    idSizeDog    |      int      |   true   | Id del tipo de tamaño de perro.
|       name      |     string    |   true   | Nombre del perro.
|   description   |     string    |   true   | Descripción del perro.
|      owner      |     string    |   true   | Dueño del perro.
|      weight     |     double    |   true   | Peso del perro.


Ejemplo:

```javascript
{
    "id": 4,
    "idRaceDog": 1,
    "idSizeDog": 9, 
    "name": "Scooby",
    "description": "Un perro muy miedoso y glotón.",
    "owner": "Shaggy",
    "weight": 78.9
}
```

#### **DELETE**: /api/dogs/
* **Descripción** 

Cambia a llevado el estado perro que se indique.

* **Parámetros de ruta**

    - No hay parámetros de ruta.

* **Headers de la petición**

    - Authorization: bearer {token}
        * Token de autenticación.
    - Content-Type: {contentType}
        * Tipo de formato del body de la petición
    - Accept: {accept}
        * Tipo de formato que se envía.

* **Body de la petición**

|  Parameter      |      Tipo     | Opcional |   Descripción |
| --------------- | ------------- | -------- | ------------- |
|       id        |      int      |   false  | Id del perro.

Ejemplo:

```javascript
{
    "id": 4
}
```

### **Grupo - Autenticación**
---
#### **POST**: /api/login/

* **Descripción** 

Permitir iniciar sesión y retorno un token.

* **Parámetros de ruta**

    - No hay parámetros de ruta.

* **Headers de la petición**

    - Content-Type: {contentType}
        * Tipo de formato del body de la petición
    - Accept: {accept}
        * Tipo de formato que se envía.

* **Body de la petición**

|  Parameter      |      Tipo     | Opcional |   Descripción |
| --------------- | ------------- | -------- | ------------- |
|       user      |     string    |   false  | Usuario para login.
|     password    |     string    |   false  | Contraseña para login.


Ejemplo:

```javascript
{
    "user": "Manuel Rivera",
    "password": "12345678",
}
```
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
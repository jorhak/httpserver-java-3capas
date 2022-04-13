# Miniserver Web utilizando la clase httpserver
> ### Funcionalidad basica
> Utilizando la clase HttpServer se crea un miniserver de aplicaciones web que recibe y responde peticiones HTTP, mediante un handler se crea una capa de abstraccion para que sea utilizado sin preocuparse de la parte web y concentrarse en implementar la solucion.

> ### Estructura de carpetas
> * lib: Librerias necesarias para ejecutar el proyecto
> * public: Archivos CSS y JS
> * resources: Archivos de base de datos Sqlite
> * templates: Archivos html

> ### Librerias
> * mary-database: Componente para realizar el CRUD para Sqlite
> * mary-httpserver: Componente server socket que trabaja con el protocolo HTTP
> * mary-template: Componente para renderizar un archivo HTML utilizando la libreria velocity apache

> ### Aplicaciones utilizadas
> * IDE : Netbeans 12
> * JDK: 8 o superior
> * JDBC: Sqlite
> * Maven: 3.8

> ### Ejecucion
> * Es posible que la ejecucion desde Netbeans de este proyecto requiera permisos para abrir un puerto, esto depende de cada equipo y sistema operativo.
> * Asignar permisos de lectura y escritura a todos los archivos.
> * Ingresar la URL en el navegador web (localhost:8000) o el puerto que usted asigne.

> ### Despliegue
> * Construido el proyecto desde Netbeans
> * Copiar las carpetas (lib, resources, public, templates) a la carpeta dist 

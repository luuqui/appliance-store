APPLIANCE-STORE
TECNOLOGÍAS: JAVA, SPRING-BOOT, SPRING-CLOUD, MAVEN, MYSQL, LOAD BALANCER, RESILIENCE4J, CRUDs, MICROSERVICIOS, DOCKER, POSTMAN, HIBERNATE Y DEMÁS TECNOLOGÍAS Y PATRONES!
La app consta de 3 microservicios: productos, carrito, ventas.

Productos: Se encargará de gestionar la información de los electrodomésticos disponibles en la plataforma.

Carrito: Este microservicio maneja, como dice su nombre, el carrito de compras de los usuarios. Éstos podrán, mediante este servicio, 
agregar y quitar artículos de la lista de electrodomésticos de su carrito de compras. Además, podrán ver el total del carrito.

Ventas: Se encarga de registrar cada venta mediante un número de identificación y una fecha. Cada venta está asociada a un carrito de compras. Al solicitar una venta mostrará los productos, total, fecha, etc.

USO DE LA APP: 
Para poder probar el proyecto se deberá configurar las variables de entorno local (user, password, url) para entrar en una base de datos local. Estas configuraciones están en el config data.
Además, dejé un archivo Postman para poder implementar de manera más ágil el proyecto completo.

DOCKER:
Para generar las imagenes solo hay que hacer uso de la consola, ya está todo configurado.
Los comandos son 'docker-compose build' y posteriormente a esto para generar los contenedores 'docker-compose up'.

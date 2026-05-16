# Proyecto Musa Jeans
Proyecto desarrollado utilizando arquitectura de microservicios para la gestión de una tienda de jeans online.

El sistema permite:
  * Gestión de jeans.
  * Control de inventario.
  * Gestión de clientes.
  * Gestión de carrito de compras.
  * Gestión de ventas.

# Arquitectura utilizada
El proyecto fue desarrollado utilizando una arquitectura basada en microservicios.
Cada microservicio posee:
  * Su propia lógica.
  * Sus propios controladores.
  * Sus propias entidades.
  * Su propia base de datos.
  * Comunicación mediante API REST.

# Tecnologías utilizadas
  * Java
  * Spring Boot
  * Spring Data JPA
  * Spring Web
  * Spring Cloud Gateway
  * WebClient
  * Lombok
  * MySQL
  * Postman
  * GitHub
  * Visual Studio Code

# Microservicios desarrollados

  1. service-jean
  Microservicio encargado de la gestión de jeans.
  Puerto = 8081
  Base de datos = bd_jeans

  2. service-inventario
  Microservicio encargado del control de stock.
  Puerto = 8082
  Base de datos = bd_inventario

  3. service-cliente
  Microservicio encargado de la gestión de clientes.
  Puerto = 8083
  Base de datos = bd_cliente

  4. service-carrito
  Microservicio encargado del carrito de compras.
  Puerto = 8084
  Base de datos = bd_carrito

  5. service-venta
  Microservicio encargado de las ventas.
  Puerto = 8085
  Base de datos = bd_venta

# Datos de prueba

# Cliente
{
  "rut": "20051806-3",
  "nombre": "Felipe Fernández",
  "correo": "felipe@gmail.com",
  "direccion": "La galaxia #162, Maipú"
}

# Modelo
{
  "id" : 1,
  "nombre": "Skinny"
}

# Marca
{
  "id" : 1,
  "nombre": "Levis"
}

# Jean
{
  "precio": 29990,
  "color": "Azul",
  "talla": "42",
  "tiro": "Alto",
  "descripcion": "Jean slim fit azul",
  "marca": {
    "id": 1
  },
  "modelo": {
    "id": 1
  }
}

# Inventario
{
  "stock": 25,
  "jeanId": 1
} 

# Carrito
{
  "precioTotal": 59980,
  "fecha": "2026-05-13",
  "rutCliente": "20051806-3"
}

# Producto carrito
{
  "cantidad": 2,
  "precio": 29990,
  "idCarrito": 1,
  "idJean": 1
}

# Venta
{
  "fecha": "2026-05-13",
  "total": 74980,
  "rutCliente": "20051806-3"
}

# Detalle venta
{
  "cantidad": 2,
  "subTotal": 59980,
  "idJean": 1,
  "idVenta": 1
}

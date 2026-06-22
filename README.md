# Musa Jeans - Microservicios

## Descripción del proyecto

El proyecto **Musa Jeans** es una arquitectura basada en microservicios desarrollada con Spring Boot, orientada a la gestión de una tienda de ropa. El sistema está dividido en servicios independientes como Cliente, Jean, Pago y Envío, permitiendo escalabilidad, mantenimiento independiente y comunicación mediante API REST.

---

## Bibliotecas utilizadas

- Spring Boot Starter Web  
- Spring Boot Starter Data JPA  
- MySQL Driver  
- Lombok  
- Springdoc OpenAPI (Swagger)  
- Spring Boot Starter Test  

---

## Herramientas de instalación

- Java 17 o superior  
- Maven  
- MySQL Server  
- IntelliJ IDEA o Eclipse  
- Postman (para pruebas de API)  
- Git (opcional para control de versiones)  

---

## Ejemplos de rutas API REST

### Microservicio Cliente

- Obtener todos los clientes  
  `GET http://localhost:8080/api/v1/cliente`

- Obtener cliente por RUT  
  `GET http://localhost:8080/api/v1/cliente/{rut}`

- Crear cliente  
  `POST http://localhost:8080/api/v1/cliente`

- Eliminar cliente  
  `DELETE http://localhost:8080/api/v1/cliente/{id}`

---

### Microservicio Jean

- Obtener todos los jeans  
  `GET http://localhost:8081/api/v1/jean`

- Crear jean  
  `POST http://localhost:8081/api/v1/jean`

---

### Microservicio Pago

- Obtener todos los pagos  
  `GET http://localhost:8082/api/v1/pago`

- Crear pago  
  `POST http://localhost:8082/api/v1/pago`

- Eliminar pago  
  `DELETE http://localhost:8082/api/v1/pago/{id}`

---

## Ejemplos de Swagger

- Cliente Swagger  
  http://localhost:8080/swagger-ui.html

- Jean Swagger  
  http://localhost:8081/swagger-ui.html

- Pago Swagger  
  http://localhost:8082/swagger-ui.html

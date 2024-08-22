# Proyecto de Microservicios

Este proyecto es una aplicación basada en microservicios construida con Spring Boot. Incluye dos microservicios principales: `cliente-persona-servicio` y `cuenta-movimientos-servicio`, así como una base de datos MySQL. La arquitectura está diseñada para gestionar la información de clientes y las transacciones asociadas con cuentas.

## Descripción

### Microservicios

- **cliente-persona-servicio**: Gestiona la información de los clientes, incluyendo sus detalles personales y de contacto.
- **cuenta-movimientos-servicio**: Maneja la información de las cuentas y los movimientos asociados a estas cuentas. Este servicio también se comunica con el `cliente-persona-servicio` para obtener la información de los clientes.

### Tecnologías Utilizadas

- **Spring Boot**: Framework principal para construir los microservicios.
- **MySQL**: Base de datos utilizada para almacenar la información de los clientes y las cuentas.
- **Docker**: Utilizado para la contenerización de los microservicios y la base de datos.
- **JUnit y Mockito**: Utilizados para pruebas unitarias y de integración.

## Configuración del Proyecto

### Docker Compose

El archivo `docker-compose.yml` define los servicios para los microservicios y la base de datos MySQL. Asegúrese de tener Docker y Docker Compose instalados para ejecutar los siguientes comandos.

Para construir y levantar los contenedores, usa el siguiente comando:

```bash
docker compose up -d
```

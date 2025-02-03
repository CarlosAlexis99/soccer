# Proyecto: Gestión de ligas de futbol (Proyecto en desarrollo)

## Descripción

Esta API, desarrollada con Spring Boot, permite gestionar ligas deportivas, equipos y jugadores. Ofrece funcionalidades para crear, leer, actualizar y eliminar entidades, así como para relacionar jugadores con equipos y equipos con ligas.

## Tecnologías utilizadas

- Backend: Spring Boot, JPA, Hibernate, MySQL

## Instalación y configuración

### Clonar el repositorio:

```git clone https://github.com/CarlosAlexis99/soccer.git```

### Configurar la base de datos:
Crear una base de datos con el nombre especificado en el archivo application.yml


## Estructura del proyecto
***controller***: Manejan las solicitudes HTTP y devuelven las respuestas.

***domain***: Contienen la lógica de negocio y realizan las operaciones en la base de datos.

***persistence***: Engloba modelos, repositorios y especificaciones para consultas (Liga, Equipo, Jugador).



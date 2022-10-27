# To Do Lists - API

API que expone los siguientes servicios, que permiten crear y administrar listas de tareas pendientes:

* ## Lists
  * [POST] /lists
    * 201 - Creada
    * 400 - Solicitud incorrecta (Bad Request)
    * 500 - Error interno del Servidor
  * [GET]  /lists/{id}
    * 200 - OK
    * 400 - Solicitud incorrecta (Bad Request)
    * 404 - No se encuentra la lista (Not Found)
    * 500 - Error interno del Servidor
  * [GET]  /lists
    * 200 - OK
    * 400 - Solicitud incorrecta (Bad Request)
    * 500 - Error interno del Servidor
  * [PATCH]  /lists/{id}
    * 200 - OK
    * 400 - Solicitud incorrecta (Bad Request)
    * 404 - No se encuentra la lista (Not Found)
    * 500 - Error interno del Servidor
  * [DELETE] /lists/{id}
    * 200 - OK
    * 400 - Solicitud incorrecta (Bad Request)
    * 404 - No se encuentra la lista (Not Found)
    * 500 - Error interno del Servidor
* ## Items
  * [POST] /lists/{id}/items
    * 201 - Creado
    * 400 - Solicitud incorrecta (Bad Request)
    * 500 - Error interno del Servidor
  * [GET]  /lists/{id}/items/{id}
    * 200 - OK
    * 400 - Solicitud incorrecta (Bad Request)
    * 500 - Error interno del Servidor
  * [PATCH]  /lists/{id}/items/{id}
    * 200 - OK
    * 400 - Solicitud incorrecta (Bad Request)
    * 500 - Error interno del Servidor
  * [DELETE] /lists/{id}/items/{id}
    * 200 - OK
    * 400 - Solicitud incorrecta (Bad Request)
    * 500 - Error interno del Servidor

## Tecnologías de desarrollo

Los servicios fueron construídos con las siguientes herramientas y lenguajes:

* Gradle 7.1
* Java 11
* Spring Boot
* Swagger

## Requisitos

* Tener instalado Docker

## Para correr todos los seervicios

Ejecutar el comando:

    docker compose up

URL http://localhost:8400

Puede acceder a la documentación de cada microservicio en:

* http://localhost:8400/todo-list/api-docs/

## Si tiene alguna pregunta, puede escribirme a cdanielmg200@gmail.com
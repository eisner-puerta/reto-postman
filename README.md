# Generación de Colección Postman desde OpenAPI

¿Te ha pasado que llegas a un proyecto y lo primero que necesitas es probar un endpoint… pero no hay Postman, no hay Swagger, no hay nada?
 Le preguntas al último que lo tocó y te dice: “Sí, eso está... pero tengo que buscarlo”.
Bueno, para mejorar esa situación, aquí va una guía práctica para que dejes todo documentado y exportable con un par de comandos. 😎



### 📋 Requisitos Previos

Tener instalado Node.js y npm.


### Agrega SpringDoc OpenAPI a tu API con WebFlux

Agrega esto a tu build.gradle o pom.xml:


### ⚙️ Instalar OpenAPI Generator CLI

Instala la herramienta globalmente usando npm:

npm install -g @openapitools/openapi-generator-cli


### 📥 Descargar el archivo OpenAPI (YAML)

Ejecuta el siguiente comando para obtener la especificación OpenAPI del API:

curl -o api-docs.yaml http://localhost:8080/v3/api-docs.yaml


### 📦 Generar la Colección de Postman

Usa el generador para crear la colección Postman a partir del archivo api-docs.yaml:


openapi-generator-cli generate -i api-docs.yaml -g postman-collection -o postman_collection

### 📤 Importar la colección en Postman

Sigue estos pasos para importar la colección generada en Postman:

1. Abre **Postman**.
2. Haz clic en el botón **Importar** (esquina superior izquierda).
3. Selecciona la pestaña **Archivo**.
4. Busca y selecciona el archivo `.json` dentro de la carpeta `postman_collection/` (por ejemplo, `postman_collection/collection.json`).
5. Haz clic en **Importar**.

> ✅ ¡Listo! Ahora puedes ejecutar las solicitudes definidas en tu API directamente desde Postman.

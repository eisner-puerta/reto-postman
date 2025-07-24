# Generación de Colección Postman desde OpenAPI

Este proyecto describe el proceso para generar una colección de Postman a partir de la especificación OpenAPI de una API expuesta en formato YAML.

---

## 📋 Requisitos Previos

- Tener el API ejecutándose localmente en `http://localhost:8080` (ajustar si es diferente).
- Tener instalado Node.js y npm.
- Tener habilitada la documentación OpenAPI en el endpoint `/v3/api-docs.yaml`.

---

## ⚙️ Paso 1: Instalar OpenAPI Generator CLI

Instala la herramienta globalmente usando npm:

```bash
npm install -g @openapitools/openapi-generator-cli

## 📥 Paso 2: Descargar el archivo OpenAPI (YAML)

Ejecuta el siguiente comando para obtener la especificación OpenAPI del API:

```bash
curl -o api-docs.yaml http://localhost:8080/v3/api-docs.yaml

## 📦 Paso 3: Generar la Colección de Postman

Usa el generador para crear la colección Postman a partir del archivo `api-docs.yaml`:

```bash
openapi-generator-cli generate \
  -i api-docs.yaml \
  -g postman-collection \
  -o postman_collection

## 📤 Paso 4: Importar la colección en Postman

Sigue estos pasos para importar la colección generada en Postman:

1. Abre **Postman**.
2. Haz clic en el botón **Importar** (esquina superior izquierda).
3. Selecciona la pestaña **Archivo**.
4. Busca y selecciona el archivo `.json` dentro de la carpeta `postman_collection/` (por ejemplo, `postman_collection/collection.json`).
5. Haz clic en **Importar**.

> ✅ ¡Listo! Ahora puedes ejecutar las solicitudes definidas en tu API directamente desde Postman.



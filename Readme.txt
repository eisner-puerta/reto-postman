#Instalar openapitools/openapi-generator-cli
npm install -g @openapitools/openapi-generator-cli

#Obtener el openapi del api
curl -o api-docs.yaml http://localhost:8080/v3/api-docs.yaml

#Generar postman collection
openapi-generator-cli generate -i api-docs.yaml -g postman-collection -o postman_collection

#Fuente https://openapi-generator.tech/
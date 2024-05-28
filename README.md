# Convertidor de Moneda

Este proyecto es un simple convertidor de moneda desarrollado en Java. Utiliza la API de ExchangeRate-API para obtener tasas de cambio en tiempo real y realizar conversiones entre diferentes monedas.

## Configuración

1. Clona el repositorio a tu máquina local.
2. Abre el proyecto en tu entorno de desarrollo preferido (por ejemplo, IntelliJ IDEA, Eclipse).
3. Configura las credenciales de la API de ExchangeRate-API en el archivo `ConsultaMoneda.java`. Puedes obtener una clave API gratuita registrándote en [ExchangeRate-API](https://www.exchangerate-api.com/).
4. Compila y ejecuta el proyecto.

## Uso

El convertidor de moneda te permite realizar conversiones entre diferentes monedas de manera sencilla. Sigue las instrucciones proporcionadas por la aplicación para seleccionar las monedas de origen y destino, así como la cantidad que deseas convertir.

## Características

- Conversión entre monedas populares como USD, EUR, GBP, JPY, etc.
- Posibilidad de realizar conversiones personalizadas entre cualquier par de monedas admitido por ExchangeRate-API.
- Interfaz de usuario intuitiva y fácil de usar.
- **Historial de Conversiones**: El programa registra y muestra un historial de las conversiones realizadas durante la sesión actual.
- **Registros con Marca de Tiempo**: Utiliza la biblioteca `java.time` para crear registros con marca de tiempo que registran las conversiones realizadas, incluyendo información sobre qué monedas se convirtieron y en qué momento.

## Contacto

Si tienes alguna pregunta, sugerencia o encontraste algún problema, no dudes en abrir un issue en GitHub o contactar al equipo de desarrollo en development@example.com.

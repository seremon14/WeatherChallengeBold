feat: Implementación inicial de Weather Challenge Bold

Aplicación móvil Android para consultar el clima actual y pronóstico del tiempo utilizando la API de WeatherAPI.

Características principales:
- Splash Screen con animación de bienvenida
- Búsqueda de ubicaciones en tiempo real mientras el usuario escribe
- Visualización de resultados de búsqueda con nombre y país
- Pantalla de detalle con información del clima actual
- Pronóstico del tiempo para los próximos 3 días
- Soporte para cambio de orientación de pantalla
- Interfaz moderna desarrollada con Jetpack Compose
- Manejo robusto de errores y estados de carga

Arquitectura:
- Clean Architecture con separación en módulos (domain, data, app)
- Patrón MVVM para gestión de estado
- Inyección de dependencias con Hilt
- Navegación con Navigation Compose

Tecnologías utilizadas:
- Kotlin como lenguaje principal
- Jetpack Compose para UI declarativa
- Material 3 para sistema de diseño
- Retrofit para consumo de APIs REST
- Coroutines para programación asíncrona
- Coil para carga de imágenes
- Gson para serialización JSON

Pruebas:
- Pruebas unitarias para casos de uso
- Pruebas de instrumentación con Compose Testing

Configuración:
- SDK mínimo: 21 (Android 5.0)
- SDK objetivo: 34
- Gradle 8.10.2
- Android Gradle Plugin 8.7.3

API:
- WeatherAPI para datos meteorológicos
- Endpoints: /v1/search.json y /v1/forecast.json


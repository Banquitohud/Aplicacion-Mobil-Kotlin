# Aplicaciones-Moviles

Desarrollo de proyecto en Kotlin para Android

<!-- toc -->
## Tabla de Contenido
1. [Ideas de proyectos](#ideas-de-proyecto)
    - [Aplicación de Lanzar Dados](#aplicación-de-lanzar-dados)
    - [Aplicación De Recetas](#aplicación-de-recetas)
    - [Aplicación de Predicción Meteorológica](#aplicación-de-predicción-meteorológica)
2. [Aplicación Meteorológica para Android utilizando Kotlin](#aplicación-meteorológica-para-android-utilizando-kotlin)
    - [Cómo funciona el API](#cómo-funciona-el-api)
    - [Cómo funciona la Aplicación](#cómo-funciona-la-aplicación)
<!-- tocstop -->

## Ideas de proyectos

### Aplicación de Lanzar Dados
- Aplicación liviana y simple, que se puede usar en juegos de mesa, sorteos u otros fines que requieran un generador de números aleatorios.
- **Características**:
    - Juego
    - Dados
    - Sorteos
    - Fácil uso
- **Aplicaciones Similares**:
    - [Tirada De Dados Aplicacion](https://play.google.com/store/apps/details?id=com.senyuk.dicerollsns)
    - [Dados para juegos de mesa Aplicacion](https://play.google.com/store/apps/details?id=net.kosev.dicing)
    - [Dado Aplicacion](https://play.google.com/store/apps/details?id=com.noApp.dice)
- ![Imagen de unos dados](https://play-lh.googleusercontent.com/D41-7D1_06BsXVBIj4BQprKep0QqQj38B6zIsWTaZ_O5OnfOq1gLQetQzpFfvuSSnw)

### Aplicación De Recetas
- Aplicación con múltiples recetas para cuando no sepas qué preparar o te sientas abrumado. En pocos pasos tendrás una receta de las que brinda nuestra app.
- **Características**:
    - Crear tu propia receta
    - Seguir recetas brindadas en la app
    - Optimizada
- **Aplicaciones Similares**:
    - [Cookpad Recetas](https://play.google.com/store/apps/details?id=com.mufumbo.android.recipe.search)
    - [Ekilu](https://play.google.com/store/apps/details?id=es.nooddle)
- ![Imagen de mockups](https://crehana-blog.imgix.net/media/filer_public/86/23/8623443b-9801-4cbb-83ac-91e541cf2eee/apps-de-cocina-gratis.jpg?auto=format&q=50)

### Aplicación de Predicción Meteorológica
- Da la información meteorológica del país y es fácil de visualizar una vez dentro de la app, puede dar información de los demás días.
- **Aplicaciones Similares**:
    - [Clima:pronostico del tiempo](https://play.google.com/store/apps/details?id=com.weather.nold.forecast)
    - [Prevision Metereologica](https://play.google.com/store/apps/details?id=live.weather.vitality.studio.forecast.widget)
- ![Imagen de mockups](https://i.blogs.es/9f858d/sunny-app-tiempo-1/450_1000.webp)

## Aplicación Meteorológica para Android utilizando Kotlin

Este proyecto consiste en el desarrollo de una aplicación meteorológica para dispositivos Android, implementada utilizando el lenguaje de programación Kotlin. La aplicación utiliza la biblioteca Retrofit para realizar peticiones a una API de predicción meteorológica y mostrar los datos correspondientes en la interfaz de usuario.

### Características del Proyecto
- Integración de Retrofit para realizar peticiones HTTP.
- Uso de una API de predicción meteorológica para obtener datos en tiempo real.
- Implementación de una interfaz de usuario intuitiva y atractiva para mostrar la información meteorológica.

### Contenido del Proyecto
- **Código Fuente**: El código fuente de la aplicación se encuentra en la carpeta app/src/main/java.
- **Recursos de Diseño**: Los archivos XML de diseño se ubican en la carpeta app/src/main/res/layout.
- **Explicación del Código**: Se proporciona una explicación detallada del código en el archivo CodeExplanation.md.

## Uso del Proyecto
- Clona el repositorio a tu máquina local.
- Abre el proyecto en Android Studio.
- Ejecuta la aplicación en un emulador o dispositivo Android.
- ¡Explora la predicción meteorológica en tiempo real!

### Diseño De Mockups
![Imagen de mockups](https://github.com/Banquitohud/Aplicacion-Mobil-Kotlin/assets/126008295/7ea0eceb-1a49-441d-9f4f-d8708efd1a1a)
![Imagen de mockups](https://github.com/Banquitohud/Aplicacion-Mobil-Kotlin/assets/126008295/badfafad-c7e3-49a8-bb34-69ce9c7cfc18)



##Como funciona el api
---

El API utilizado en el proyecto de la aplicación meteorológica para Android desarrollado en Kotlin se comunica con el servicio de OpenWeatherMap para obtener datos meteorológicos en tiempo real. La interacción con el API se realiza mediante solicitudes HTTP utilizando la biblioteca Volley. A continuación, se describe cómo se realiza esta comunicación con el API:

**Solicitud de Datos Meteorológicos:** Para solicitar datos meteorológicos, se construye una URL que incluye la latitud y longitud de la ubicación deseada, así como la clave API proporcionada por OpenWeatherMap. Esta URL se utiliza para realizar una solicitud GET al API.

---
>val API_KEY = "117cfab279a9db71a83a7ed656ac50e2"
var url = "https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&appid=${API_KEY}"
Uso de Volley para Realizar la Solicitud: Se utiliza la biblioteca Volley para enviar la solicitud HTTP al API. Volley maneja la solicitud en un hilo secundario y devuelve la respuesta en el hilo principal, lo que permite actualizar la interfaz de usuario con los datos recibidos sin bloquear la aplicación.
val jsonRequest = JsonObjectRequest(
    Request.Method.GET, url, null,
    Response.Listener { response ->
        setValues(response)
    },
    Response.ErrorListener { Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show() })
queue.add(jsonRequest)

---

**Procesamiento de la Respuesta**: Una vez que se recibe la respuesta del API, se extraen los datos meteorológicos del objeto JSON retornado. Estos datos incluyen información como el nombre de la ciudad, las coordenadas, la temperatura actual, la presión atmosférica, la humedad, y más. Luego, estos datos se utilizan para actualizar los elementos de la interfaz de usuario correspondientes.

---
private fun setValues(response:JSONObject){
    // Extracción de datos del objeto JSON y actualización de la UI
    city.text=response.getString("name")
    coordinates.text="${lat} , ${long}"
    weather.text=response.getJSONArray("weather").getJSONObject(0).getString("main")
    // Más extracciones y actualizaciones...
}

---
**Permisos y Ubicación:** La aplicación solicita permisos de ubicación al usuario para obtener la latitud y longitud actuales, que son necesarios para realizar la solicitud al API. Esto se maneja en la clase StarScreeen.kt, que verifica los permisos y, si están concedidos, obtiene la última ubicación conocida del dispositivo.

if(CheckPermission()) {
    if(LocationEnable()){
        mfusedlocation.lastLocation.addOnCompleteListener{
            task->
            var location:Location?=task.result
            if(location==null) {
                NewLocation()
            } else {
                // Iniciar MainActivity con los datos de latitud y longitud
            }
        }
    } else {
        Toast.makeText(this,"Por favor Enciende Tu GPS",Toast.LENGTH_LONG).show()
    }
} else {
    RequestPermission()
}

---

### Como Funciona La Aplicacion
La aplicación móvil desarrollada en Kotlin para Android es una aplicación meteorológica que utiliza la ubicación del dispositivo para mostrar la predicción del tiempo actual. A continuación, se describe cómo funciona toda la aplicación, paso a paso:

Solicitud de Permisos y Obtención de la Ubicación:

Al iniciar la aplicación, se presenta la pantalla StarScreeen (StarScreeen.kt), que solicita los permisos necesarios para acceder a la ubicación del dispositivo.
Una vez concedidos los permisos, la aplicación utiliza FusedLocationProviderClient para obtener la última ubicación conocida del dispositivo.
Si no se puede obtener una ubicación, se solicita una nueva ubicación utilizando requestLocationUpdates.
Pasar la Ubicación a la Actividad Principal:

Con la latitud y longitud obtenidas, la aplicación inicia la actividad principal MainActivity (MainActivity.kt), pasando estos valores como extras en el intent.
Solicitud de Datos Meteorológicos:

En MainActivity, se recogen la latitud y longitud de los extras del intent y se utiliza esta información para realizar una solicitud a la API de OpenWeatherMap mediante Volley.
La URL de la API incluye la latitud y longitud, y una clave API para autenticar la solicitud.
Procesamiento y Visualización de Datos:

Una vez que se recibe la respuesta de la API, se procesa el objeto JSON para extraer la información meteorológica relevante, como la temperatura actual, la presión, la humedad, la velocidad del viento, etc.
Esta información se muestra en la interfaz de usuario definida en activity_main.xml (activity_main.xml), utilizando diferentes TextViews para cada pieza de datos.
Diseño de la Interfaz de Usuario:

La interfaz de usuario se diseña utilizando XML, donde se definen MaterialCardViews para mostrar la información de manera estructurada y estéticamente agradable.
Se utiliza un ScrollView para permitir el desplazamiento en caso de que el contenido exceda la altura de la pantalla.
Configuración y Dependencias del Proyecto:

El archivo build.gradle.kts (build.gradle.kts) del proyecto especifica las dependencias necesarias, como Volley para las solicitudes de red y la biblioteca de servicios de ubicación de Google para obtener la ubicación del dispositivo.
También se configura el soporte para Kotlin y se establecen las versiones de SDK objetivo y mínimo.
Permisos en AndroidManifest.xml:

En AndroidManifest.xml (AndroidManifest.xml), se declaran los permisos necesarios para el acceso a Internet y la ubicación del dispositivo.

## Como funciona el API

El API utilizado en el proyecto de la aplicación meteorológica para Android desarrollado en Kotlin se comunica con el servicio de OpenWeatherMap para obtener datos meteorológicos en tiempo real. La interacción con el API se realiza mediante solicitudes HTTP utilizando la biblioteca Volley. A continuación, se describe cómo se realiza esta comunicación con el API:

**Solicitud de Datos Meteorológicos:** Para solicitar datos meteorológicos, se construye una URL que incluye la latitud y longitud de la ubicación deseada, así como la clave API proporcionada por OpenWeatherMap. Esta URL se utiliza para realizar una solicitud GET al API.

---

> val API_KEY = "117cfab279a9db71a83a7ed656ac50e2"
> var url = "https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&appid=${API_KEY}"
> Uso de Volley para Realizar la Solicitud: Se utiliza la biblioteca Volley para enviar la solicitud HTTP al API. Volley maneja la solicitud en un hilo secundario y devuelve la respuesta en el hilo principal, lo que permite actualizar la interfaz de usuario con los datos recibidos sin bloquear la aplicación.
> val jsonRequest = JsonObjectRequest(
>     Request.Method.GET, url, null,
>     Response.Listener { response ->
>         setValues(response)
>     },
>     Response.ErrorListener { Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show() })
> queue.add(jsonRequest)

---

**Procesamiento de la Respuesta:** Una vez que se recibe la respuesta del API, se extraen los datos meteorológicos del objeto JSON retornado. Estos datos incluyen información como el nombre de la ciudad, las coordenadas, la temperatura actual, la presión atmosférica, la humedad, y más. Luego, estos datos se utilizan para actualizar los elementos de la interfaz de usuario correspondientes.

---

> private fun setValues(response:JSONObject){
>     // Extracción de datos del objeto JSON y actualización de la UI
>     city.text=response.getString("name")
>     coordinates.text="${lat} , ${long}"
>     weather.text=response.getJSONArray("weather").getJSONObject(0).getString("main")
>     // Más extracciones y actualizaciones...
> }

---

**Permisos y Ubicación:** La aplicación solicita permisos de ubicación al usuario para obtener la latitud y longitud actuales, que son necesarios para realizar la solicitud al API. Esto se maneja en la clase StarScreeen.kt, que verifica los permisos y, si están concedidos, obtiene la última ubicación conocida del dispositivo.

---

> if(CheckPermission()) {
>     if(LocationEnable()){
>         mfusedlocation.lastLocation.addOnCompleteListener{
>             task->
>             var location:Location?=task.result
>             if(location==null) {
>                 NewLocation()
>             } else {
>                 // Iniciar MainActivity con los datos de latitud y longitud
>             }
>         }
>     } else {
>         Toast.makeText(this,"Por favor Enciende Tu GPS",Toast.LENGTH_LONG).show()
>     }
> } else {
>     RequestPermission()
> }

---


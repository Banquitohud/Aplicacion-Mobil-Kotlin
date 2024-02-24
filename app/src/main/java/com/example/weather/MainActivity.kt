package com.example.weather

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import kotlin.math.ceil


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lat=intent.getStringExtra("lat")
        var long=intent.getStringExtra("long")

        window.statusBarColor= Color.parseColor("#1383C3")
        getJsonData(lat,long)
    }

    private fun getJsonData(lat: String?, long: String?) {
        val API_KEY = "117cfab279a9db71a83a7ed656ac50e2"
        val queue = Volley.newRequestQueue(this)
        var url = "https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&appid=${API_KEY}"
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                setValues(response)

            },
            Response.ErrorListener { Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show() })


        queue.add(jsonRequest)
    }

    private fun setValues(response:JSONObject){
        var city = findViewById<TextView>(R.id.city)
        city.text=response.getString("name")
        var lat = response.getJSONObject("coord").getString("lat")
        var long=response.getJSONObject("coord").getString("lon")
        var coordinates = findViewById<TextView>(R.id.coordinates)
        coordinates.text="${lat} , ${long}"
        var weather = findViewById<TextView>(R.id.weather)
        weather.text=response.getJSONArray("weather").getJSONObject(0).getString("main")
        var tempr=response.getJSONObject("main").getString("temp")
        tempr=((((tempr).toFloat()-273.15)).toInt()).toString()
        var temp = findViewById<TextView>(R.id.temp)
        temp.text="${tempr}°C"

        var mintemp=response.getJSONObject("main").getString("temp_min")
        mintemp=((((mintemp).toFloat()-273.15)).toInt()).toString()
        var min_temp = findViewById<TextView>(R.id.min_temp)
        min_temp.text=mintemp+"°C"
        var maxtemp=response.getJSONObject("main").getString("temp_max")
        maxtemp=((ceil((maxtemp).toFloat()-273.15)).toInt()).toString()
        var max_temp = findViewById<TextView>(R.id.max_temp)
        max_temp.text=maxtemp+"°C"

        var pressure = findViewById<TextView>(R.id.pressure)
        pressure.text=response.getJSONObject("main").getString("pressure")
        var humidity = findViewById<TextView>(R.id.humidity)
        humidity.text=response.getJSONObject("main").getString("humidity")+"%"
        var wind = findViewById<TextView>(R.id.wind)
        wind.text=response.getJSONObject("wind").getString("speed")
        var degree =findViewById<TextView>(R.id.degree)
        degree.text="Degree : "+response.getJSONObject("wind").getString("deg")+"°"

    }
}

//Llamada del API

/*{"coord":{"lon":-74.0817,"lat":4.6097},"weather":[{"id":802,"main":"Clouds","description":"scattered
clouds","icon":"03d"}],"base":"stations","main":{"temp":296.46,"feels_like":295.94,"temp_min":296.46,
"temp_max":296.46,"pressure":1011,"humidity":42,"sea_level":1011,"grnd_level":756},"visibility":10000,
"wind":{"speed":2.48,"deg":281,"gust":3.64},"clouds":{"all":47},"dt":1708454223,"sys":{"country":"CO",
"sunrise":1708427429,"sunset":1708470603},"timezone":-18000,"id":3688689,"name":"Bogota","cod":200}
 */

/*
{
  "coord": {
    "lon": 10.99,
    "lat": 44.34
  },
  "weather": [
    {
      "id": 501,
      "main": "Rain",
      "description": "moderate rain",
      "icon": "10d"
    }
  ],
  "base": "stations",
  "main": {
    "temp": 298.48,
    "feels_like": 298.74,
    "temp_min": 297.56,
    "temp_max": 300.05,
    "pressure": 1015,
    "humidity": 64,
    "sea_level": 1015,
    "grnd_level": 933
  },
  "visibility": 10000,
  "wind": {
    "speed": 0.62,
    "deg": 349,
    "gust": 1.18
  },
  "rain": {
    "1h": 3.16
  },
  "clouds": {
    "all": 100
  },
  "dt": 1661870592,
  "sys": {
    "type": 2,
    "id": 2075663,
    "country": "IT",
    "sunrise": 1661834187,
    "sunset": 1661882248
  },
  "timezone": 7200,
  "id": 3163858,
  "name": "Zocca",
  "cod": 200
}
*/
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


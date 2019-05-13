package se.wintren.freyr.presentation

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import se.wintren.freyr.BuildConfig
import se.wintren.freyr.GeoCodingAPI
import se.wintren.freyr.R
import se.wintren.freyr.WeatherAPI

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Initial test for api keys, retrofit and rx. (Obviously not a String response :P )

        val mapsTestFab: FloatingActionButton = findViewById(R.id.fab1)
        val weatherTestFab: FloatingActionButton = findViewById(R.id.fab2)
        mapsTestFab.setOnClickListener { view ->
            Snackbar.make(view, "Maps API", Snackbar.LENGTH_SHORT).show()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com")
                .client(
                    OkHttpClient.Builder().addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                        .addInterceptor { chain ->
                            val request = chain.request().let {
                                val url =
                                    it.url().newBuilder().addQueryParameter("key", BuildConfig.GOOGLE_API_KEY).build()
                                it.newBuilder().url(url).build()
                            }
                            chain.proceed(request)
                        }
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            val geoAPI = retrofit.create(GeoCodingAPI::class.java)
            geoAPI.geocode("London")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("TEMP", it)
                }, {
                    it.printStackTrace()
                })

        }
        weatherTestFab.setOnClickListener { view ->
            Snackbar.make(view, "Weather API", Snackbar.LENGTH_SHORT).show()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .client(
                    OkHttpClient.Builder().addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                        .addInterceptor { chain ->
                            val request = chain.request().let {
                                val url =
                                    it.url().newBuilder().addQueryParameter("appid", BuildConfig.OPEN_WEATHER_API_KEY)
                                        .build()
                                it.newBuilder().url(url).build()
                            }
                            chain.proceed(request)
                        }
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            val weatherAPI = retrofit.create(WeatherAPI::class.java)
            weatherAPI.getWeatherForecast(
                lat = 51.5073509,
                lon = -0.1277583
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("TEMP", it)
                }, {
                    it.printStackTrace()
                })

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}

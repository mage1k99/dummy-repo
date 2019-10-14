package io.chillout.sampletest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import io.chillout.sampletest.R
import io.chillout.sampletest.data.timeApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val TimeApi by lazy {
        timeApi.create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(bottom_navigation_android)
        invisble_text.setText("text changed yo")


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.albums_bottom ->{
                Snackbar.make(coodinatorlayout,"This is a snackbar",Snackbar.LENGTH_SHORT).setAction("Ok bitch"){
                    sucker()
                }.show()
            }
            R.id.about_us_bottom ->{
                Log.d("yoyo","Bitch")
                var disposable = TimeApi
                    .getTime("Kolkata")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { result -> invisble_text.text = "${result.datetime} is date result found \n ${result.clientIp} is client IP" },
                        { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
                    )
            }
        }
        return true
    }
    fun sucker(){
        Toast.makeText(applicationContext,"yo listen up u pressed OK bitch",Toast.LENGTH_SHORT).show()
    }

    fun visbletext(view: View) {
        invisble_text.visibility = View.VISIBLE
    }
}

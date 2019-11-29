package io.chillout.sampletest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import io.chillout.sampletest.R
import io.chillout.sampletest.data.dataTimedate
import io.chillout.sampletest.data.timeApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TimeApi by lazy {
        timeApi.create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(bottom_navigation_android_main)
        invisble_text.setText("text changed yo")
        fab.setOnClickListener {
            startActivity(Intent(this,ActivityFragment::class.java))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu_main,menu)
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
                        { result -> getResult(result) },
                        { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
                    )
            }
            R.id.timer_bottom ->{
                startActivity(Intent(this,TimerActivity::class.java))
            }
        }
        return true
    }
    fun sucker(){
        Toast.makeText(applicationContext,"yo listen up u pressed OK bitch",Toast.LENGTH_SHORT).show()
        // create a intent here
    }
    fun getResult(result: dataTimedate.timedata) {
        val date = result.datetime.substring(0,result.datetime.indexOf("T"))
        val time = result.datetime.substring(result.datetime.indexOf("T")+1,result.datetime.indexOf("+"))
        invisble_text.text = "$date is date result found \n $time is time \n ip is ${result.clientIp}"
    }


    fun visbletext(view: View) {
        invisble_text.visibility = View.VISIBLE
    }
}

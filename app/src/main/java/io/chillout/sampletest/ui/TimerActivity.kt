package io.chillout.sampletest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.health.TimerStat
import android.view.View
import android.widget.Toast
import io.chillout.sampletest.R
import io.chillout.sampletest.utils.prefUtil
import kotlinx.android.synthetic.main.activity_timer.*
import java.util.*

class TimerActivity : AppCompatActivity() {
    enum class TimerState{
        stop,running,paused
    }
    private  lateinit var timer: CountDownTimer
    private var timerLengthSeconds = 0L
    private var timerState = TimerState.stop
    private var remaining = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        start_timer.setOnClickListener {
            startTimer()
            timerState = TimerState.running
            updatebuttons()
        }
        TimerProgressBar.setOnClickListener{
            timerState = TimerState.paused
            timer.cancel()
            updatebuttons()
        }
        stop_timer.setOnClickListener {
            timerState = TimerState.stop
            timer.cancel()
            ontimerfinished()
        }
    }

    private fun ontimerfinished() {
        timerState = TimerState.stop
        setNewTimerLength()
        TimerProgressBar.progress = 0
        prefUtil.setSecondsRemaining(timerLengthSeconds,this)
        remaining = timerLengthSeconds
        updatebuttons()
        updateUI()
    }

    override fun onResume() {
        super.onResume()
        initTimer()
        // TODO notifiaction remove

    }

    private fun initTimer() {
        timerState = prefUtil.getTimerState(this)
        if (timerState == TimerState.stop){
            setNewTimerLength()
        }
        else{
            setPreviousTimerLength()
        }
        remaining = if (timerState == TimerState.paused || timerState == TimerState.running) {
            prefUtil.getSecondsRemaining(this)
        }else{
            timerLengthSeconds
        }
        if (timerState == TimerState.running){
            startTimer()
        }
        updatebuttons()
        updateUI()
    }

    private fun updatebuttons() {
        when(timerState){
            TimerState.running ->{
             start_timer.visibility = View.INVISIBLE
             stop_timer.visibility =View.VISIBLE
            }
            TimerState.paused ->{
                Toast.makeText(applicationContext,"Paused the timer",Toast.LENGTH_SHORT).show()
                start_timer.visibility = View.VISIBLE
                stop_timer.visibility =View.VISIBLE
            }
            TimerState.stop ->{
                stop_timer.visibility = View.INVISIBLE
                start_timer.visibility = View.VISIBLE
            }
        }
    }

    private fun setPreviousTimerLength() {
        timerLengthSeconds = prefUtil.getPreviousTimerLengthSeconds(this)
        TimerProgressBar.max = timerLengthSeconds.toInt()
    }

    private fun setNewTimerLength() {
        val lengthInMinutes = prefUtil.getTimerLength(this)
        timerLengthSeconds = (lengthInMinutes*60L)
        TimerProgressBar.max = timerLengthSeconds.toInt()
    }

    private fun startTimer() {
        timerState = TimerState.running
        timer = object : CountDownTimer(remaining*1000,1000){
            override fun onFinish() = ontimerfinished()
            override fun onTick(millisUntilFinished: Long) {
                remaining = millisUntilFinished/1000
                updateUI()
            }
        }.start()
    }

    private fun updateUI() {
        val minutesUntilfinish = remaining/60
        val secondsInMinuteUtilFinished = remaining - minutesUntilfinish * 60
        val secondsstr = secondsInMinuteUtilFinished.toString()
        timer_count.text = "$minutesUntilfinish:${if(secondsstr.length == 2){
        secondsstr
        }else{
            '0' + secondsstr
        }
        }"
        TimerProgressBar.progress = (timerLengthSeconds - remaining).toInt()
    }

    override fun onPause() {
        super.onPause()
        if (timerState == TimerState.running){
            timer.cancel()
            //TODO show notification
        }
        else if (timerState == TimerState.paused){
            // TODO Show notifiation
        }
        prefUtil.setPreviousTimerLengthSeconds(timerLengthSeconds,this)
        prefUtil.setSecondsRemaining(remaining,this)
        prefUtil.setTimerState(timerState,this)
    }
}

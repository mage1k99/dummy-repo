package io.chillout.sampletest.models

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel

class UserViewModel(application: Application) : AndroidViewModel(application) {
    override fun onCleared() {
        super.onCleared()
        Log.d("UserViewModel","ViewModel Destroyed")
    }

}
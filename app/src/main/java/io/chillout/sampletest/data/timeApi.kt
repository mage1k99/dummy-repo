package io.chillout.sampletest.data

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface timeApi {
@GET("Asia/{location}") fun getTime(@Path("location")Timezone:String): Observable<dataTimedate.timedata>
    companion object{
        fun create():timeApi{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://worldtimeapi.org/api/timezone/")
                .build()
            return retrofit.create(timeApi::class.java)
        }
    }
}
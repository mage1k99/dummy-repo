package io.chillout.sampletest.data
import com.google.gson.annotations.SerializedName


object dataTimedate{
    data class timedata
        (
        @SerializedName("client_ip") var clientIp: String,
        @SerializedName("datetime") var datetime: String
    )
}
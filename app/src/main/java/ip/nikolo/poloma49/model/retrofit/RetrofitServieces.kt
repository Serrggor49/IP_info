package ip.nikolo.poloma49.model.retrofit

import ip.nikolo.poloma49.model.retrofit.ipqualityPOJO.IpQualityAnswer
import ip.nikolo.poloma49.model.retrofit.ipstackPOJO.IpstackAnswer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitServieces {

    @GET("check?access_key=28b56b934550f4a7f2df5e9ee7c3c817")
    fun getInfoIpStack(): Call<IpstackAnswer>

    @GET
    fun getInfoIpQuality(@Url url: String): Call<IpQualityAnswer>

    @GET()
    fun sendToTelegram2(@Url url: String): Call<Any>

}
package ip.nikolo.poloma49.model

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import ip.nikolo.poloma49.domain.GetDataRepository
import ip.nikolo.poloma49.model.retrofit.RetrofitServieces
import ip.nikolo.poloma49.model.retrofit.ipqualityPOJO.IpQualityAnswer
import ip.nikolo.poloma49.model.retrofit.ipstackPOJO.IpstackAnswer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repository : GetDataRepository {

    val CHAT_ID = "@myIpApk" // название чата, в который бот будет лить данные. Бот должен быть добавлен в чат!
    val BOT_API_KEY_TELEGRAM = "/bot1741405654:AAGNrFRwtQx1j1zY_y3z0NHGXp0MUp2E8NA/"  // ключ бота телеграмма
    val URL_TELEGRAM_API = "https://api.telegram.org"
    val URL_SEND_MESSAGE = "sendMessage?chat_id="
    val URL_SEND_MESSAGE2 = "&text="

    val URL_IP_STACK = "http://api.ipstack.com/"
    val URL_IP_QUALITY_PART1 = "https://ipqualityscore.com/api/json/ip/n2auc5utEnmluidk7REFENgLvC69MpgK/"  // n2auc5utEnmluidk7REFENgLvC69MpgK ключ на natashagoryunova1@gmail.com
    val URL_IP_QUALITY_PART2 = "?strictness=0&allow_public_access_points=true&fast=true&lighter_penalties=true&mobile=true"

    lateinit var retrofit: Retrofit
    var gson = GsonBuilder().setLenient().create()

    var liveDataIpStack = MutableLiveData<IpstackAnswer?>()
    var liveDataIpQuality = MutableLiveData<IpQualityAnswer?>()

    override fun getLiveDataIpStack(): LiveData<IpstackAnswer?> {
        if (liveDataIpStack.value == null) {
            loadDataIpStack()
        }

        return liveDataIpStack
    }

    override fun getLiveDataIpQuality(): LiveData<IpQualityAnswer?> {
        return liveDataIpQuality
    }


    fun loadDataIpStack() {
        retrofit = Retrofit.Builder()
                .baseUrl(URL_IP_STACK)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        retrofit.create(RetrofitServieces::class.java)

        val rs: RetrofitServieces = retrofit.create(RetrofitServieces::class.java)

        rs.getInfoIpStack().enqueue(object : Callback<IpstackAnswer> {

            override fun onResponse(call: Call<IpstackAnswer>, response: Response<IpstackAnswer>) {
                liveDataIpStack.value = (response.body() as IpstackAnswer)
                loadDataIpQuality(liveDataIpStack.value?.ip)
            }

            override fun onFailure(call: Call<IpstackAnswer>, t: Throwable) {
                Log.e("MyLogs", "LoadDataIpStack " + t.message.toString())
            }

        })
    }

    fun loadDataIpQuality(ip: String?) {
        retrofit = Retrofit.Builder()
                .baseUrl(URL_IP_QUALITY_PART1)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        retrofit.create(RetrofitServieces::class.java)

        val rs: RetrofitServieces = retrofit.create(RetrofitServieces::class.java)

        rs.getInfoIpQuality(ip + URL_IP_QUALITY_PART2).enqueue(object : Callback<IpQualityAnswer> {

            override fun onResponse(call: Call<IpQualityAnswer>, response: Response<IpQualityAnswer>) {
                liveDataIpQuality.value = response.body()!!
                sendInfoToTelegram()
            }

            override fun onFailure(call: Call<IpQualityAnswer>, t: Throwable) {
                Log.e("MyLogs", "LoadDataIpQuality " + t.message.toString())
            }
        })
    }


    fun sendInfoToTelegram() {

        var DATA_FOR_TELEGRAM = ""
        DATA_FOR_TELEGRAM += ("country: " + liveDataIpQuality.value?.country_code + ",")
        DATA_FOR_TELEGRAM += (" organization: " + liveDataIpQuality.value?.organization + ",")
        DATA_FOR_TELEGRAM += (" bot: " + liveDataIpQuality.value?.bot_status + ",")
        DATA_FOR_TELEGRAM += (" proxy: " + liveDataIpQuality.value?.proxy + ",")
        DATA_FOR_TELEGRAM += (" city: " + liveDataIpQuality.value?.city + ",")
        DATA_FOR_TELEGRAM += (" fraud_score: " + liveDataIpQuality.value?.fraud_score)
        DATA_FOR_TELEGRAM += (" ip: " + liveDataIpStack.value?.ip)

        retrofit = Retrofit.Builder()
                .baseUrl(URL_TELEGRAM_API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        retrofit.create(RetrofitServieces::class.java)

        val rs: RetrofitServieces = retrofit.create(RetrofitServieces::class.java)
        rs.sendToTelegram2(BOT_API_KEY_TELEGRAM + URL_SEND_MESSAGE + CHAT_ID + URL_SEND_MESSAGE2 + DATA_FOR_TELEGRAM).enqueue(object :
                Callback<Any> {

            override fun onResponse(call: Call<Any>, response: Response<Any>) {
               // Log.d("MyLogs", "TG " + response.toString())
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.e("MyLogs", "TG " + t.message.toString())
            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun getNetworkStatus(context: Context): Boolean {
        val nerworkStatus = NerworkStatus
        return nerworkStatus.checkInternetConnection(context)
    }

}
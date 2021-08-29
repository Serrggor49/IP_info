package ip.nikolo.poloma49.presentation.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext
import ip.nikolo.poloma49.MyApplication
import ip.nikolo.poloma49.R
import ip.nikolo.poloma49.presentation.viewModel.InfoActivityViewModel
import javax.inject.Inject

@AndroidEntryPoint
class InfoActivity: AppCompatActivity() {

   // private val loginViewModel: InfoActivityViewModel by viewModel()

    @Inject
    lateinit var con: MyApplication

    lateinit var viewModel: InfoActivityViewModel
    lateinit var ipTextView: TextView
    lateinit var countryTextView: TextView
    lateinit var regionTextView: TextView
    lateinit var typeTextView: TextView
    lateinit var ispTextView: TextView
    lateinit var timezoneTextView: TextView
    lateinit var torTextView: TextView
    lateinit var vpnTextView: TextView
    lateinit var proxyTextView: TextView
    lateinit var botTextView: TextView
    lateinit var abuseVelocity: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        supportActionBar?.hide()
        Log.d("MyLogs", "Info Act " + con.toString())
        viewModel = ViewModelProvider(this).get(InfoActivityViewModel::class.java)

        init()
    }

    fun init()  {
        viewModel.init()

        ipTextView = findViewById(R.id.ip_text_view)
        countryTextView = findViewById(R.id.country_text_view)
        regionTextView = findViewById(R.id.region_text_view)
        typeTextView = findViewById(R.id.type_text_view)
        ispTextView = findViewById(R.id.isp_text_view)
        timezoneTextView = findViewById(R.id.timezone_text_view)
        torTextView = findViewById(R.id.tor_text_view)
        vpnTextView = findViewById(R.id.vpn_text_view)
        proxyTextView = findViewById(R.id.proxy_text_view)
        botTextView = findViewById(R.id.bot_text_view)
        abuseVelocity = findViewById(R.id.abuse_velocity_text_view)


      ///  viewModel = ViewModelProvider(this, modelFactoryForInfo).get(InfoActivityViewModel::class.java)

        ipTextView.text = viewModel.liveData.value?.ip
        viewModel.liveData.observe(this, {
            ipTextView.text = it?.ip
            countryTextView.text = it?.country_name
            regionTextView.text = it?.region_name
            typeTextView.text = it?.type
        })

        viewModel.liveDataQuality.observe(this, {
            ispTextView.text = it.ISP // isp - организация
            timezoneTextView.text = it.timezone
            torTextView.text = it.tor.toString()
            vpnTextView.text = it.vpn.toString()
            proxyTextView.text = it.proxy.toString()
            botTextView.text = it.bot_status.toString()
            abuseVelocity.text = it.abuse_velocity
        })
    }
}
package ip.nikolo.poloma49.presentation.view

import android.content.Intent
import android.os.Build
import ip.nikolo.poloma49.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import ip.nikolo.poloma49.model.NerworkStatus
import ip.nikolo.poloma49.presentation.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    lateinit var viewModel: MainActivityViewModel
    var netStatus = false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        init()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun init() {

        progressBar.visibility = VISIBLE

        netStatus = viewModel.getNetStatus()
        viewModel.init()

        if (!netStatus) {
            progressBar.visibility = GONE
            not_connection_id.visibility = VISIBLE
            tryAgainButton.visibility = VISIBLE
            tryAgainButton.setOnClickListener {
                init()
            }
        } else {
            not_connection_id.visibility = GONE
            tryAgainButton.visibility = GONE

            buttonInfoActivity.setOnClickListener {
                val intent = Intent(this, InfoActivity::class.java)
                startActivity(intent)
            }

            viewModel.liveDataIpStack.observe(this, {
                ipTextView.text = it?.ip
                if (splashScreen.visibility == VISIBLE) {
                    main_layout_id.visibility = VISIBLE
                    splashScreen.visibility = GONE
                }
            })
        }

    }


}
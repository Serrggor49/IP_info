package ip.nikolo.poloma49.presentation.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ip.nikolo.poloma49.domain.*
import ip.nikolo.poloma49.model.Repository
import ip.nikolo.poloma49.model.retrofit.ipstackPOJO.IpstackAnswer


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    var repository = Repository
    var liveDataIpStack = MutableLiveData<IpstackAnswer?>()
    var networkStatus = false

    fun init() {
        networkStatus = getNetStatus()
        if (networkStatus) {
            getDataIpStack()
        }
    }

    fun getNetStatus(): Boolean {
        val networkStatusUseCase = GetNetworkStatusUseCase(repository)
        return (networkStatusUseCase.getNetworkStatus(getApplication()))
    }

    fun getDataIpStack() {
        val getDataIpStackDataUseCase = GetLiveDataIpStackUseCase(repository)
        liveDataIpStack = getDataIpStackDataUseCase.getLiveDataIpStack() as MutableLiveData<IpstackAnswer?>
    }

}

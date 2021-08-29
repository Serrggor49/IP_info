package ip.nikolo.poloma49.presentation.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import ip.nikolo.poloma49.MyApplication
import ip.nikolo.poloma49.domain.GetDataRepository
import ip.nikolo.poloma49.domain.GetLiveDataIpQuality
import ip.nikolo.poloma49.domain.GetLiveDataIpStackUseCase
import ip.nikolo.poloma49.model.Repository
import ip.nikolo.poloma49.model.retrofit.ipqualityPOJO.IpQualityAnswer
import ip.nikolo.poloma49.model.retrofit.ipstackPOJO.IpstackAnswer
import javax.inject.Inject


@HiltViewModel
class InfoActivityViewModel @Inject constructor(application: Application): AndroidViewModel(application) {

    @Inject
    lateinit var repository: GetDataRepository

    var liveData = MutableLiveData<IpstackAnswer?>()
    var liveDataQuality = MutableLiveData<IpQualityAnswer>()

    fun init() {
        val getLiveDataIpStackUseCase = GetLiveDataIpStackUseCase(repository)
        liveData = getLiveDataIpStackUseCase.getLiveDataIpStack() as MutableLiveData<IpstackAnswer?>

        val getLiveDataIpQuality = GetLiveDataIpQuality(repository)
        liveDataQuality = getLiveDataIpQuality.getLiveDataIpQuality() as MutableLiveData<IpQualityAnswer>
    }


}




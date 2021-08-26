package ip.nikolo.poloma49.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ip.nikolo.poloma49.domain.GetLiveDataIpQuality
import ip.nikolo.poloma49.domain.GetLiveDataIpStackUseCase
import ip.nikolo.poloma49.model.Repository
import ip.nikolo.poloma49.model.retrofit.ipqualityPOJO.IpQualityAnswer
import ip.nikolo.poloma49.model.retrofit.ipstackPOJO.IpstackAnswer

class InfoActivityViewModel : ViewModel() {

    var liveData = MutableLiveData<IpstackAnswer>()
    var liveDataQuality = MutableLiveData<IpQualityAnswer>()
    var repository = Repository


    init {

        val getLiveDataIpStackUseCase = GetLiveDataIpStackUseCase(repository)
        liveData = getLiveDataIpStackUseCase.getLiveDataIpStack() as MutableLiveData<IpstackAnswer>

        val getLiveDataIpQuality = GetLiveDataIpQuality(repository)
        liveDataQuality = getLiveDataIpQuality.getLiveDataIpQuality() as MutableLiveData<IpQualityAnswer>


    }


}




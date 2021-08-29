package ip.nikolo.poloma49.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ip.nikolo.poloma49.domain.*
import ip.nikolo.poloma49.model.retrofit.ipstackPOJO.IpstackAnswer
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel()
{
    @Inject
    lateinit var repository: GetDataRepository

    var liveDataIpStack = MutableLiveData<IpstackAnswer?>()

    fun init() {
        val getDataIpStackDataUseCase = GetLiveDataIpStackUseCase(repository)
        liveDataIpStack =
            getDataIpStackDataUseCase.getLiveDataIpStack() as MutableLiveData<IpstackAnswer?>
    }

}

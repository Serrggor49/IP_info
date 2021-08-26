package ip.nikolo.poloma49.domain

import androidx.lifecycle.LiveData
import ip.nikolo.poloma49.model.retrofit.ipstackPOJO.IpstackAnswer

class GetLiveDataIpStackUseCase (private val repository: GetDataRepository){

    fun getLiveDataIpStack(): LiveData<IpstackAnswer?>{
        return (repository.getLiveDataIpStack())
    }
}
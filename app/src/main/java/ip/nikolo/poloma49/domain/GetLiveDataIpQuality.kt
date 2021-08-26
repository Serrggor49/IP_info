package ip.nikolo.poloma49.domain

import androidx.lifecycle.LiveData
import ip.nikolo.poloma49.model.retrofit.ipqualityPOJO.IpQualityAnswer

class GetLiveDataIpQuality (private val repository: GetDataRepository) {

    fun getLiveDataIpQuality() : LiveData<IpQualityAnswer?>{
        return (repository.getLiveDataIpQuality())
    }

}
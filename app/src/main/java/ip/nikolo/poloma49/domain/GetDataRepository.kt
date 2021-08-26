package ip.nikolo.poloma49.domain

import android.content.Context
import androidx.lifecycle.LiveData
import ip.nikolo.poloma49.model.retrofit.ipqualityPOJO.IpQualityAnswer
import ip.nikolo.poloma49.model.retrofit.ipstackPOJO.IpstackAnswer

interface GetDataRepository {

    fun getNetworkStatus(context: Context): Boolean

    fun getLiveDataIpStack(): LiveData<IpstackAnswer?>
    fun getLiveDataIpQuality(): LiveData<IpQualityAnswer?>

}
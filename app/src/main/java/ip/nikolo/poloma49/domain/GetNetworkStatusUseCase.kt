package ip.nikolo.poloma49.domain

import android.app.Application
import android.content.Context

class GetNetworkStatusUseCase(private val repository: GetDataRepository) {

    fun getNetworkStatus(context: Context): Boolean {
        return repository.getNetworkStatus(context)
    }
}
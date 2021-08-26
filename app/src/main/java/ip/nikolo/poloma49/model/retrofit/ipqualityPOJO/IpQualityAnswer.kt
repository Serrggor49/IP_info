package ip.nikolo.poloma49.model.retrofit.ipqualityPOJO

data class IpQualityAnswer(
    val ASN: Int,
    val ISP: String,
    val abuse_velocity: String,
    val active_tor: Boolean,
    val active_vpn: Boolean,
    val bot_status: Boolean,
    val city: String,
    val connection_type: String,
    val country_code: String,
    val fraud_score: Int,
    val host: String,
    val is_crawler: Boolean,
    val latitude: Double,
    val longitude: Double,
    val message: String,
    val mobile: Boolean,
    val organization: String,
    val proxy: Boolean,
    val recent_abuse: Boolean,
    val region: String,
    val request_id: String,
    val success: Boolean,
    val timezone: String,
    val tor: Boolean,
    val vpn: Boolean
)
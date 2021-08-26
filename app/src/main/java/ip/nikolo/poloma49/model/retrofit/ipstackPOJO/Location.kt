package ip.nikolo.poloma49.model.retrofit.ipstackPOJO

data class Location(
    val calling_code: String,
    val capital: String,
    val country_flag: String,
    val country_flag_emoji: String,
    val country_flag_emoji_unicode: String,
    val geoname_id: Any,
    val is_eu: Boolean,
    val languages: List<Language>
)
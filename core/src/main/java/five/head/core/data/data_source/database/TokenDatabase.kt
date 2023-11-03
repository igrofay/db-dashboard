package five.head.core.data.data_source.database

import android.content.SharedPreferences
import five.head.core.data.utils.stringNullable

internal class TokenDatabase(
    sharedPreferences: SharedPreferences
) {
    var accessToken by sharedPreferences.stringNullable()
}
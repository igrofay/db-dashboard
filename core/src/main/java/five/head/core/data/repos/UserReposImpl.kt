package five.head.core.data.repos

import android.os.Build
import five.head.core.data.data_source.database.TokenDatabase
import five.head.core.data.utils.VernamCipher
import five.head.core.data.utils.toSha256
import five.head.core.domain.repos.UserRepos

internal class UserReposImpl(
    private val tokenDatabase: TokenDatabase
) :  UserRepos{
    private val cipher = VernamCipher()
    private val offsetValueForAccess = 500
    private val aValueForAccess = Build.ID.toSha256().toByteArray().sum()
    private val cValueForAccess = "Access".toSha256().toByteArray().sum()
    override fun getAccessToken(): String? {
        val token = tokenDatabase.accessToken ?: return null
        val key = cipher.generateKey(
            token.length,
            offsetValueForAccess,
            aValueForAccess,
            cValueForAccess,
            Char.MAX_VALUE.code / 2
        )
        return cipher.decipher(token, key)
    }

    override fun setAccessToken(token: String) {
        val key = cipher.generateKey(
            token.length,
            offsetValueForAccess,
            aValueForAccess,
            cValueForAccess,
            Char.MAX_VALUE.code / 2
        )
        tokenDatabase.accessToken = cipher.encrypt(token, key)
    }

    override fun reset() {
        tokenDatabase.accessToken = null
    }
}
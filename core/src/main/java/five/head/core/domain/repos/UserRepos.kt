package five.head.core.domain.repos

interface UserRepos {
    fun getAccessToken(): String?
    fun setAccessToken(token: String)
    fun reset()
}
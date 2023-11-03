package five.head.core.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
internal data class CodeForAuthBody(
    val code: String
)
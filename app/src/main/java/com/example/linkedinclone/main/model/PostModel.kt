package com.example.linkedinclone.main.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
enum class Reaction {
    LIKE, // 0
    INSIGHTFUL, // 1
    LOVE, // 2
    FUNNY, // 3
    CELEBRATE, // 4
    SUPPORT // 5
}

@Parcelize
@JsonClass(generateAdapter = true)
data class PostSchema(
    @field:Json(name = "posts") val posts: List<UpdatedPostModel>,
    @field:Json(name = "total") val total: Int,
    @field:Json(name = "skip") val skip: Int,
    @field:Json(name = "limit") val limit: Int
) : Parcelable {

}

@Parcelize
@JsonClass(generateAdapter = true)
data class UpdatedPostModel(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "body") val body: String,
    @field:Json(name = "userId") val userId: Int,
    @field:Json(name = "tags") val tags: List<String>,
    @field:Json(name = "reactions") val reaction: Int,
) : Parcelable {

}

@Parcelize
data class PostModel(
    @JvmField
    var profileData: ProfileData,
    @JvmField
    var description: String,
    @JvmField
    var attachment: List<Attachment> = emptyList(),
    @JvmField
    var reactionCount: Int = 0,
    @JvmField
    var reactionData: List<ProfileData> = emptyList(), // List of all connection will be returned here
    @JvmField
    var comments: List<CommentData> = emptyList(), // This one will be paginated as comment count can be more
    @JvmField
    var commentCount: Int = 0,
    @JvmField
    var userReaction: Int = 0, // Refer Reaction class from which these integer will be decided
) : Parcelable {

}

@Parcelize
data class CommentData(
    @JvmField
    var userData: ProfileData,
    @JvmField
    var commentData: String,
    @JvmField
    var reactionCount: Int = 0,
    @JvmField
    var reactionFromConnection: List<ProfileData> = emptyList()
) : Parcelable {

}

@Parcelize
data class Attachment(
    @JvmField
    var id: String,
    @JvmField
    var fileName: String,
    @JvmField
    var mimeType: String,
    @JvmField
    var thumbnailUrl: String,
) : Parcelable {

}

@Parcelize
data class ProfileData(
    @JvmField
    var uid: String,
    @JvmField
    var username: String,
    @JvmField
    var fullName: String,
    @JvmField
    var followerCount: Int,
    @JvmField
    var bioData: String,
    @JvmField
    var profileUrl: String? = null, // Here we'll use pre-signed url from s3
    @JvmField
    var isMutualConnection: Boolean // Compute this value from backend
) : Parcelable {

}

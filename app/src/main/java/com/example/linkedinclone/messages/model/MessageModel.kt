package com.example.linkedinclone.messages.model

import android.os.Parcelable
import com.example.linkedinclone.main.model.ProfileData
import kotlinx.parcelize.Parcelize

@Parcelize
data class MessageModel(
    var profileData: ProfileData,
    var message: MessageData,
) : Parcelable {

}

@Parcelize
data class MessageData(
    var senderUsername: String,
    var message: List<ContentInfo>
) : Parcelable {

}

@Parcelize
data class ContentInfo(
    var message: String,
    var timestamp: Long,
    var read: Boolean,
    var userReaction: Int? = null
) : Parcelable {

}

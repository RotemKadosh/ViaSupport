package com.example.support.Networking

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize




@Parcelize
@JsonClass(generateAdapter = true)
data class TeamMember(
        val firstName:String,
        val lastName:String,
        val available: Boolean,
        val phone: String,
        val email: String,
        @Json(name = "image") val imgSrcUrl: String) : Parcelable


@Parcelize
@JsonClass(generateAdapter = true)
data class TeamMemberList(var list: List<TeamMember>) : Parcelable{

}





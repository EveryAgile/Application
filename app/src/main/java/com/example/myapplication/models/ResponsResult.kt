package com.example.myapplication.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpResult(
    var success: Boolean,
    var code: Int,
    var msg: String?,
) : Parcelable

@Parcelize
data class token(
    var accessToken: String?,
    var accessTokenExpireDate: String?,
    var refreshToken: String?,
)  : Parcelable

@Parcelize
data class SignInResult(
    var code: Int,
    var data: token?,
    var msg: String?,
    var success: Boolean
) : Parcelable

@Parcelize
data class UsersResult(
    var code: Int,
    var msg: String,
    var success: Boolean
) : Parcelable

@Parcelize
data class BacklogResult(
    var code: Int,
    var msg: String,
    var success: Boolean
) : Parcelable

@Parcelize
data class ProjectsResult(
    var code: Int,
    var msg: String,
    var success: Boolean
) : Parcelable

@Parcelize
data class MemberResult(
    var memberEmail: String,
    var projectId: Int
) : Parcelable

@Parcelize
data class CreateSprintResult(
    var code: Int,
    var msg: String,
    var success: Boolean
) : Parcelable

@Parcelize
data class Sprint(
    var sprintId: Int,
    var sprintName: String?,
    var projectId: Int,
    var endTime: String?,
    var description: String?,
    var importance: String?,
    var status: Boolean
):Parcelable

@Parcelize
data class InquireSprintsResult(
    var code: Int,
    var msg: String,
    var success: Boolean,
    var list: List<Sprint>
) : Parcelable

@Parcelize
data class UserInfo(
    var id: Int,
    var name: String,
    var email: String
):Parcelable

@Parcelize
data class sprintMembersResult(
    var code: Int,
    var msg: String,
    var success: Boolean,
    var list: List<UserInfo>
):Parcelable

@Parcelize
data class SprintDeleteResult(
    var code: Int,
    var msg: String,
    var success: Boolean
):Parcelable
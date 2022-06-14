package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class UserResponseDto(
    var id: Long,
    var name: String?,
    var email: String?
        ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserResponseDto> {
        override fun createFromParcel(parcel: Parcel): UserResponseDto {
            return UserResponseDto(parcel)
        }

        override fun newArray(size: Int): Array<UserResponseDto?> {
            return arrayOfNulls(size)
        }
    }

}
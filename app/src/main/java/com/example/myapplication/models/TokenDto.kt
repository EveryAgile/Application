package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class TokenDto (
    var accessToken: String?,
    var refreshToken: String?,
    var accessTokenExpireDate: Long,
        ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(accessToken)
        parcel.writeString(refreshToken)
        parcel.writeLong(accessTokenExpireDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TokenDto> {
        override fun createFromParcel(parcel: Parcel): TokenDto {
            return TokenDto(parcel)
        }

        override fun newArray(size: Int): Array<TokenDto?> {
            return arrayOfNulls(size)
        }
    }
}
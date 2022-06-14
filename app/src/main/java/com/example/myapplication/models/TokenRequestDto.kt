package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class TokenRequestDto (
    var accessToken: String?,
    var refreshToken: String?,
        ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(accessToken)
        parcel.writeString(refreshToken)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TokenRequestDto> {
        override fun createFromParcel(parcel: Parcel): TokenRequestDto {
            return TokenRequestDto(parcel)
        }

        override fun newArray(size: Int): Array<TokenRequestDto?> {
            return arrayOfNulls(size)
        }
    }
}
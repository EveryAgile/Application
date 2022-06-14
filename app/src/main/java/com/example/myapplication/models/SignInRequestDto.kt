package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class SignInRequestDto (
    var email: String? = null,
    var password: String? = null
        ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SignInRequestDto> {
        override fun createFromParcel(parcel: Parcel): SignInRequestDto {
            return SignInRequestDto(parcel)
        }

        override fun newArray(size: Int): Array<SignInRequestDto?> {
            return arrayOfNulls(size)
        }
    }
}
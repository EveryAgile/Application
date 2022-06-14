package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class SignUpRequestDto(
    var email: String? =null,
    var name: String? =null,
    var password: String? =null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
        parcel.writeString(name)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SignUpRequestDto> {
        override fun createFromParcel(parcel: Parcel): SignUpRequestDto {
            return SignUpRequestDto(parcel)
        }

        override fun newArray(size: Int): Array<SignUpRequestDto?> {
            return arrayOfNulls(size)
        }
    }
}

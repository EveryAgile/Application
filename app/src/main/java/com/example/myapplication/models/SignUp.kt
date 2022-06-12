package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class SignUp(
    var email: String?,
    var name: String?,
    var password: String?
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

    companion object CREATOR : Parcelable.Creator<SignUp> {
        override fun createFromParcel(parcel: Parcel): SignUp {
            return SignUp(parcel)
        }

        override fun newArray(size: Int): Array<SignUp?> {
            return arrayOfNulls(size)
        }
    }
}

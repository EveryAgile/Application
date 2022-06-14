package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class CommenResult (
    var code: Int = 0,
    var data: TokenDto? = null,
    var msg: String? = null,
    var success: Boolean = true,

    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readParcelable(TokenDto::class.java.classLoader),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(code)
        parcel.writeParcelable(data, flags)
        parcel.writeString(msg)
        parcel.writeByte(if (success) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CommenResult> {
        override fun createFromParcel(parcel: Parcel): CommenResult {
            return CommenResult(parcel)
        }

        override fun newArray(size: Int): Array<CommenResult?> {
            return arrayOfNulls(size)
        }
    }
}
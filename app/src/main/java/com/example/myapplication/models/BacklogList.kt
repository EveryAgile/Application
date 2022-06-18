package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class BacklogList (
    var success: Boolean = false,
    var code: Int = 0,
    var msg: String? = null,
    var list: MutableList<BacklogResponseDto>? = null
        ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readString(),
        TODO("list")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (success) 1 else 0)
        parcel.writeInt(code)
        parcel.writeString(msg)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BacklogList> {
        override fun createFromParcel(parcel: Parcel): BacklogList {
            return BacklogList(parcel)
        }

        override fun newArray(size: Int): Array<BacklogList?> {
            return arrayOfNulls(size)
        }
    }
}
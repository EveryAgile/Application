package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class CommonResult(
    var success: Boolean,
    var code: Int,
    var msg: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readString()
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

    companion object CREATOR : Parcelable.Creator<ProjectCreate> {
        override fun createFromParcel(parcel: Parcel): ProjectCreate {
            return ProjectCreate(parcel)
        }

        override fun newArray(size: Int): Array<ProjectCreate?> {
            return arrayOfNulls(size)
        }
    }
}
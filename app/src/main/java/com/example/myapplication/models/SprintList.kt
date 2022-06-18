package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.coroutines.flow.MutableStateFlow

data class SprintList (
    var success: Boolean=false,
    var code: Int = 0,
    var msg: String? =null,
    var list: MutableList<SprintListItem>? = null
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

    companion object CREATOR : Parcelable.Creator<SprintList> {
        override fun createFromParcel(parcel: Parcel): SprintList {
            return SprintList(parcel)
        }

        override fun newArray(size: Int): Array<SprintList?> {
            return arrayOfNulls(size)
        }
    }
}
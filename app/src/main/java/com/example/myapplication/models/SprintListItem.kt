package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class SprintListItem (
    var sprintId: Long? = null,
    var sprintName: String? =null,
    var projectId: Long? =null,
    var description: String? =null,
    var importance: String?=null,
    var status: Boolean = false,
    var backlogList: MutableList<Long>? = null
        ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        TODO("backlogList")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(sprintId)
        parcel.writeString(sprintName)
        parcel.writeValue(projectId)
        parcel.writeString(description)
        parcel.writeString(importance)
        parcel.writeByte(if (status) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SprintListItem> {
        override fun createFromParcel(parcel: Parcel): SprintListItem {
            return SprintListItem(parcel)
        }

        override fun newArray(size: Int): Array<SprintListItem?> {
            return arrayOfNulls(size)
        }
    }
}
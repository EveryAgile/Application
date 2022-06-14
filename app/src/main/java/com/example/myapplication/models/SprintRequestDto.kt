package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class SprintRequestDto (
    var sprintName: String? =null,
    var projectId: Long = 0,
    var endTime: String? = null,
    var description: String? = null,
    var importance: String? = null,
    var users : MutableList<String>? = null
        ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        TODO("users")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(sprintName)
        parcel.writeLong(projectId)
        parcel.writeString(endTime)
        parcel.writeString(description)
        parcel.writeString(importance)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SprintRequestDto> {
        override fun createFromParcel(parcel: Parcel): SprintRequestDto {
            return SprintRequestDto(parcel)
        }

        override fun newArray(size: Int): Array<SprintRequestDto?> {
            return arrayOfNulls(size)
        }
    }
}
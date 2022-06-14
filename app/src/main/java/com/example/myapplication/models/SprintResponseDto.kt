package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class SprintResponseDto (
    var sprintId: Long,
    var sprintName: String?,
    var projectId: Long,
    var endTime: String?,
    var decription: String?,
    var importance: String?,
    var status: Boolean
        ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(sprintId)
        parcel.writeString(sprintName)
        parcel.writeLong(projectId)
        parcel.writeString(endTime)
        parcel.writeString(decription)
        parcel.writeString(importance)
        parcel.writeByte(if (status) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SprintResponseDto> {
        override fun createFromParcel(parcel: Parcel): SprintResponseDto {
            return SprintResponseDto(parcel)
        }

        override fun newArray(size: Int): Array<SprintResponseDto?> {
            return arrayOfNulls(size)
        }
    }
}
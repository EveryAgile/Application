package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable
import java.time.Duration

data class BacklogResponseDto (
    var backlogId: Long,
    var backlogName: String?,
    var sprintId: Long,
    var endTime: String?,
    var storyPoint: Double,
    var manDay: Double,
    var status: Boolean?
        ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(backlogId)
        parcel.writeString(backlogName)
        parcel.writeLong(sprintId)
        parcel.writeString(endTime)
        parcel.writeDouble(storyPoint)
        parcel.writeDouble(manDay)
        parcel.writeValue(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BacklogResponseDto> {
        override fun createFromParcel(parcel: Parcel): BacklogResponseDto {
            return BacklogResponseDto(parcel)
        }

        override fun newArray(size: Int): Array<BacklogResponseDto?> {
            return arrayOfNulls(size)
        }
    }
}
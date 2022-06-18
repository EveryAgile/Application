package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable
import java.time.Duration

data class BacklogResponseDto (
    var backlogId: Long= 0,
    var backlogName: String?=null,
    var sprintId: Long = 0,
    var endTime: String? = null,
    var storyPoint: Double = 0.0,
    var manDay: Double = 0.0,
    var status: Boolean? = false,
    var tasks: MutableList<Long>? = null
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
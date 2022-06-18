package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class BacklogRequestDto(
    var backlogName: String? = null,
    var sprintId: Long=0,
    var endTime: String? =null,
    var storyPoint: Double?=0.0,
    var manDay: Double?=0.0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(backlogName)
        parcel.writeLong(sprintId)
        parcel.writeString(endTime)
        parcel.writeValue(storyPoint)
        parcel.writeValue(manDay)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BacklogRequestDto> {
        override fun createFromParcel(parcel: Parcel): BacklogRequestDto {
            return BacklogRequestDto(parcel)
        }

        override fun newArray(size: Int): Array<BacklogRequestDto?> {
            return arrayOfNulls(size)
        }
    }
}
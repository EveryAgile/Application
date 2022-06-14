package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class BacklogRequestDto(
    var backlogName: String?,
    var springtId: Long,
    var endTime: String?,
    var storyPoint: Double?,
    var manDay: Double?,
    var users: List<String>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(backlogName)
        parcel.writeLong(springtId)
        parcel.writeString(endTime)
        parcel.writeValue(storyPoint)
        parcel.writeValue(manDay)
        parcel.writeStringList(users)
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
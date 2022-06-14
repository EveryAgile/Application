package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class TaskRequestDto (
    var taskName: String?,
    var backlogId: Long,
    var manDay: Double,
    var users: List<String>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readLong(),
        parcel.readDouble(),
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(taskName)
        parcel.writeLong(backlogId)
        parcel.writeDouble(manDay)
        parcel.writeStringList(users)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TaskRequestDto> {
        override fun createFromParcel(parcel: Parcel): TaskRequestDto {
            return TaskRequestDto(parcel)
        }

        override fun newArray(size: Int): Array<TaskRequestDto?> {
            return arrayOfNulls(size)
        }
    }
}
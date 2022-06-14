package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class TaskResponseDto (
    var taskId: Long,
    var taskName: String?,
    var backlogId: Long,
    var manDay: Double,
    var status: Boolean
        ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readDouble(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(taskId)
        parcel.writeString(taskName)
        parcel.writeLong(backlogId)
        parcel.writeDouble(manDay)
        parcel.writeByte(if (status) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TaskResponseDto> {
        override fun createFromParcel(parcel: Parcel): TaskResponseDto {
            return TaskResponseDto(parcel)
        }

        override fun newArray(size: Int): Array<TaskResponseDto?> {
            return arrayOfNulls(size)
        }
    }
}
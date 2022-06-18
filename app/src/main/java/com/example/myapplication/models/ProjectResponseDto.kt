package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class ProjectResponseDto (
    var projectId: Long,
    var projectName: String? = null,
    var startTime: String?= null,
    var endTime: String? =null,
    var type: String? = null,
    var sprints: MutableList<Long>? = null
        ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        TODO("sprints")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(projectId)
        parcel.writeString(projectName)
        parcel.writeString(startTime)
        parcel.writeString(endTime)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProjectResponseDto> {
        override fun createFromParcel(parcel: Parcel): ProjectResponseDto {
            return ProjectResponseDto(parcel)
        }

        override fun newArray(size: Int): Array<ProjectResponseDto?> {
            return arrayOfNulls(size)
        }
    }
}
package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class ProjectRequestDto (
    var projectName : String? =null,
    var startTime: String? =null,
    var endTime: String?=null,
    var type: String? = null
        ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        TODO("type")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(projectName)
        parcel.writeString(startTime)
        parcel.writeString(endTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProjectRequestDto> {
        override fun createFromParcel(parcel: Parcel): ProjectRequestDto {
            return ProjectRequestDto(parcel)
        }

        override fun newArray(size: Int): Array<ProjectRequestDto?> {
            return arrayOfNulls(size)
        }
    }
}
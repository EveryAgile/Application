package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class InviteRequestDto (
    var projetId: Long,
    var memberEmail: String?
        ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(projetId)
        parcel.writeString(memberEmail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InviteRequestDto> {
        override fun createFromParcel(parcel: Parcel): InviteRequestDto {
            return InviteRequestDto(parcel)
        }

        override fun newArray(size: Int): Array<InviteRequestDto?> {
            return arrayOfNulls(size)
        }
    }
}
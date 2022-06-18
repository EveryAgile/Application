package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class InviteRequestDto (
    var projectId: Long =0,
    var memberEmail: String?=null
        )
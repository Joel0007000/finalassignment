package com.joel.finalassignment.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey
    val _id:String="",
    val pName:String?=null,
    val pPrice:String?=null,
    val pDesc:String?=null,
    val pImage:String?=null,
    val pRating :String?=null
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(pName)
        parcel.writeString(pPrice)
        parcel.writeString(pDesc)
        parcel.writeString(pImage)
        parcel.writeString(pRating)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }

}
package kr.co.lion.androidproject_memo

import android.os.Parcel
import android.os.Parcelable

class MemoClass(var title:String?, var date:String?, var context:String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(date)
        parcel.writeString(context)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MemoClass> {
        override fun createFromParcel(parcel: Parcel): MemoClass {
            return MemoClass(parcel)
        }

        override fun newArray(size: Int): Array<MemoClass?> {
            return arrayOfNulls(size)
        }
    }
}
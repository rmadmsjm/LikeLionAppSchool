package kr.co.lion.androidproject1test

import android.os.Parcel
import android.os.Parcelable

class Lion() : Animal(), Parcelable {
    // 털의 개수
    var furCnt = 0
    // 성별
    var gender = LionGender.LION_GENDER1

    constructor(parcel: Parcel) : this() {
        getFromParcel(parcel)
        furCnt = parcel.readInt()
        gender = parcel.readValue(LionGender::class.java.classLoader) as LionGender
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        addToParcel(parcel)
        parcel.writeInt(furCnt)
        parcel.writeValue(gender)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Lion> {
        override fun createFromParcel(parcel: Parcel): Lion {
            return Lion(parcel)
        }

        override fun newArray(size: Int): Array<Lion?> {
            return arrayOfNulls(size)
        }
    }

}
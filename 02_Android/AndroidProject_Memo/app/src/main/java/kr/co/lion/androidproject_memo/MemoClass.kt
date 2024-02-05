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

    // 메모 데이터 삭제 시, 두 개의 MemoClass 인스턴스(MainActivity의 memoData와 ShowActivity에서 넘겨주는 memoData)의
    // 모든 속성(title, date 및 context) 값을 비교하기 위한 메서드
    // 값 중 하나라도 다를 경우, 인스턴스가 동일하지 않은 것으로 간주함
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MemoClass

        if (title != other.title) return false
        if (date != other.date) return false
        if (context != other.context) return false

        return true
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
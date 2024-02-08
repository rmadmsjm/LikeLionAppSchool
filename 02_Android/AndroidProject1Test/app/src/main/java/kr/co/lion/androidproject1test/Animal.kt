package kr.co.lion.androidproject1test

import android.os.Parcel
import android.os.Parcelable

// Parcelable 사용
// 1) Lion, Tiger, Giraffe 클래스만 Parcelable 구현
//    -> Parcelable 구현한 클래스를 가지고 만든 객체만 Intent에 담을 수 있음
//    -> 따라서 Parcelable 구현하지 않은 Animal 타입은 Intent에 담지 못 함
//    -> animalList는 제네릭이 Animal이기 때문에 Intent에 담지 못 함
//    -> Lion, Tiger, Giraffe 타입으로 형변환을 해야만 Intent에 담을 수 있음
// 2) Animal도 Parcelable 구현
//    -> Intent에 담기 위함
//    -> 만약 구현 안 할 경우, 객체가 어떤 타입인지에 대한 정보도 같이 보내줘야 함

open class Animal() : Parcelable {
    // 동물 타입
    var type = AnimalType.ANIMAL_TYPE_LION
    // 이름
    var name = ""
    // 나이
    var age = 0

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()!!
        age = parcel.readInt()
    }

    // Parcel에 프로퍼티 값 담기
    fun addToParcel(parcel: Parcel) {
        parcel.writeValue(type)
        parcel.writeString(name)
        parcel.writeInt(age)
    }

    // Parcel로부터 데이터를 추출해 프로퍼티에 담기
    fun getFromParcel(parcel: Parcel) {
        type = parcel.readValue(AnimalType::class.java.classLoader) as AnimalType
        name = parcel.readString()!!
        age = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Animal> {
        override fun createFromParcel(parcel: Parcel): Animal {
            return Animal(parcel)
        }

        override fun newArray(size: Int): Array<Animal?> {
            return arrayOfNulls(size)
        }
    }
}
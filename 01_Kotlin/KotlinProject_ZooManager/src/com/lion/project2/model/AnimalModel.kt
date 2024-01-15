package com.lion.project2.model

import com.lion.project2.util.AnimalTypeNumber
import java.io.Serializable

class AnimalModel(var type:Int, var name:String, var age:Int, var furCnt:Int, var stripesCnt: Int, var noseLength:Int) :
    Serializable {
    fun printAnimalInfo() {
        if(type == AnimalTypeNumber.LION.num) {
            println("동물 타입 : 사자")
            println("동물 이름 : $name ")
            println("동물 나이 : $age ")
            println("털의 개수 : $furCnt")
            println()
        } else if(type == AnimalTypeNumber.TIGER.num) {
            println("동물 타입 : 호랑이")
            println("동물 이름 : $name ")
            println("동물 나이 : $age ")
            println("줄무늬 개수 : $stripesCnt")
            println()
        } else if(type == AnimalTypeNumber.ELEPHANT.num){
            println("동물 타입 : 코끼리")
            println("동물 이름 : $name ")
            println("동물 나이 : $age ")
            println("코의 길이 : $noseLength")
            println()
        }
    }

    fun printDeleteAnimalInfo() {
        if(type == AnimalTypeNumber.LION.num) {
            println("동물 타입 : 사자")
            println("동물 이름 : $name ")
            println()
        } else if(type == AnimalTypeNumber.TIGER.num) {
            println("동물 타입 : 호랑이")
            println("동물 이름 : $name ")
            println()
        } else if(type == AnimalTypeNumber.ELEPHANT.num){
            println("동물 타입 : 코끼리")
            println("동물 이름 : $name ")
            println()
        }
    }
}
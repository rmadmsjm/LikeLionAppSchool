package com.lion.project2.model

import java.io.Serializable

class AnimalModel(var type:String, var name:String, var age:Int, var furCnt:Int, var stripesCnt: Int, var noseLength:Int) :
    Serializable {
    fun printAnimalInfo() {
        if(type == "사자") {
            println()
            println("동물 타입 : $type")
            println("동물 이름 : $name ")
            println("동물 나이 : $age ")
            println("털의 개수 : $furCnt")
        } else if(type == "호랑이") {
            println()
            println("동물 타입 : $type")
            println("동물 이름 : $name ")
            println("동물 나이 : $age ")
            println("줄무늬 개수 : $stripesCnt")
        } else if(type == "코끼리"){
            println()
            println("동물 타입 : $type")
            println("동물 이름 : $name ")
            println("동물 나이 : $age ")
            println("코의 길이 : $noseLength")
        }
    }
}
package com.lion.project2.model

class AnimalModel(var type:Int, var name:String, var age:Int, var furCnt:Int, var stripesCnt: Int, var noseLength:Int) {
    fun printAnimalInfo() {
        if(type == 1) {
            println()
            println("동물 타입 : 사자")
            println("동물 이름 : $name ")
            println("동물 나이 : $age ")
            println("털의 개수 : $furCnt")
        } else if(type == 2) {
            println()
            println("동물 타입 : 호랑이")
            println("동물 이름 : $name ")
            println("동물 나이 : $age ")
            println("줄무늬 개수 : $stripesCnt")
        } else{
            println()
            println("동물 타입 : 코끼리")
            println("동물 이름 : $name ")
            println("동물 나이 : $age ")
            println("코의 길이 : $noseLength")
        }
    }
}
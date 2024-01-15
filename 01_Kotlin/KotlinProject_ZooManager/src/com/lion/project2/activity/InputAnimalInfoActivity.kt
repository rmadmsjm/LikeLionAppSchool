package com.lion.project2.activity

import com.lion.project2.controller.MainController
import com.lion.project2.dao.AnimalInfoDAO
import com.lion.project2.model.AnimalModel
import com.lion.project2.util.ProgramState
import java.util.Scanner

class InputAnimalInfoActivity(var mainController: MainController) : BaseActivity() {

    lateinit var scanner: Scanner
    // 입력된 동물 정보 담을 객체
    var animalList:ArrayList<AnimalModel>? = null
    // 동물 정보를 입력 받을 객체
    lateinit var animalModel: AnimalModel

    override fun initActivity() {
        scanner = Scanner(System.`in`)

        // 동물 정보 가져오기
        animalList = AnimalInfoDAO.getAnimalInfoList()
    }

    override fun processActivity() {
    }

    override fun showActivity() {
        println()
        println("[ 동물 정보 입력 ]")
        showAnimalCnt()
        inputAnimalInfo()
        saveAnimalInfo()
    }

    override fun finishActivity() {
        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)
    }

    fun showAnimalCnt() {
        if(animalList != null) {
            println("현재 입력된 동물의 수 : ${animalList?.size}마리")
        } else {
            println("현재 입력된 동물의 수 : 0마리")
        }
    }

    fun inputAnimalInfo() {
        var type = ""
        var name = ""
        var age = -1
        var furCnt = -1
        var stripesCnt = -1
        var noseLength = -1

        print("동물 종류 (1. 사자, 2. 호랑이, 3.코끼리) : ")
        type = scanner.next()

        print("동물의 이름 : ")
        name = scanner.next()

        print("동물의 나이 : ")
        age = scanner.nextInt()

        if (type == "사자") {
            print("털의 개수 : ")
            furCnt = scanner.nextInt()
        } else if (type == "호랑이") {
            print("줄무늬 개수 : ")
            stripesCnt = scanner.nextInt()
        } else if (type == "코끼리") {
            print("코의 길이 : ")
            noseLength = scanner.nextInt()
        }

        animalModel = AnimalModel(type, name, age, furCnt, stripesCnt, noseLength)
    }

    fun saveAnimalInfo() {
        // ArrayList가 null 이면 객체를 생성함
        if(animalList == null){
            animalList = ArrayList<AnimalModel>()
        }

        // 객체를 ArrayList에 담기
        animalList?.add(animalModel)
        // 파일에 저장
        AnimalInfoDAO.saveAnimalInfoList(animalList!!)

    }
}
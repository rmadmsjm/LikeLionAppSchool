package com.lion.project2.activity

import com.lion.project2.controller.MainController
import com.lion.project2.dao.AnimalInfoDAO
import com.lion.project2.model.AnimalModel
import com.lion.project2.util.AnimalTypeNumber
import com.lion.project2.util.ProgramState

class ShowAllAnimalInfoActivity(var mainController: MainController) : BaseActivity() {

    var animalList:ArrayList<AnimalModel>? = null

    override fun initActivity() {
        animalList = AnimalInfoDAO.getAnimalInfoList()
    }

    override fun processActivity() {
    }

    override fun showActivity() {
        println()
        println("[ 동물 전체 정보 ]")
        if(animalList!!.isEmpty()) {
            println("저장된 동물 정보가 없습니다")
            println()
        } else {
            showAnimalCnt()
            printAnimalInfoAll()
        }
    }

    override fun finishActivity() {
        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)
    }

    fun showAnimalCnt() {
        var lionCnt = 0
        var tigerCnt = 0
        var elephantCnt = 0

        animalList?.forEach {
            if (it.type == AnimalTypeNumber.LION.num) {
                lionCnt++
            } else if (it.type == AnimalTypeNumber.TIGER.num) {
                tigerCnt++
            } else if (it.type == AnimalTypeNumber.ELEPHANT.num) {
                elephantCnt++
            }
        }

        println("전체 동물의 수 : ${animalList?.size}마리")
        println("사자 : ${lionCnt}마리")
        println("호랑이 : ${tigerCnt}마리")
        println("코끼리 : ${elephantCnt}마리")
        println()
    }

    fun printAnimalInfoAll() {
        animalList?.forEach {
            it.printAnimalInfo()
        }
    }
}
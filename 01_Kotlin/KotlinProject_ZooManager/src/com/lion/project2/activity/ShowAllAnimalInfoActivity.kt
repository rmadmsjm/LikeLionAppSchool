package com.lion.project2.activity

import com.lion.project2.controller.MainController
import com.lion.project2.dao.AnimalInfoDAO
import com.lion.project2.model.AnimalModel
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
        if(animalList == null) {
            println("저장한 동물 정보가 없습니다")
        } else {
            printAnimalInfoAll()
        }
    }

    override fun finishActivity() {
        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)
    }

    fun printAnimalInfoAll() {
        animalList?.forEach {
            it.printAnimalInfo()
        }
    }
}
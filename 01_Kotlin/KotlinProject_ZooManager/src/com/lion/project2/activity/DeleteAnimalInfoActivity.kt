package com.lion.project2.activity

import com.lion.project2.controller.MainController
import com.lion.project2.dao.AnimalInfoDAO
import com.lion.project2.model.AnimalModel
import com.lion.project2.util.ProgramState
import java.util.Scanner

class DeleteAnimalInfoActivity(var mainController: MainController) : BaseActivity() {

    lateinit var scanner: Scanner
    var animalList:ArrayList<AnimalModel>? = null
    var deleteNum = 0

    override fun initActivity() {
        animalList = AnimalInfoDAO.getAnimalInfoList()
        scanner = Scanner(System.`in`)
    }

    override fun processActivity() {
    }

    override fun showActivity() {
        println()
        println("[ 동물 정보 삭제 ]")
        if(animalList!!.isEmpty()) {
            println("저장된 동물 정보가 없습니다")
            println()
        } else {
            printAllAnimalInfo()
            deleteAnimalInfo()
        }
    }

    override fun finishActivity() {
        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)
    }

    fun printAllAnimalInfo() {
        var index = 1
        animalList?.forEach {
            println("${index}번 동물")
            it.printAnimalInfo()
            index++
        }
    }

    fun deleteAnimalInfo() {
        print("삭제할 동물의 번호를 입력해주세요 : ")
        deleteNum = scanner.nextInt()
        animalList?.removeAt(deleteNum-1)
        AnimalInfoDAO.saveAnimalInfoList(animalList!!)
        println("동물의 정보가 삭제되었습니다")
    }
}
package com.lion.project2.activity

import com.lion.project2.controller.MainController
import com.lion.project2.dao.AnimalInfoDAO
import com.lion.project2.model.AnimalModel
import com.lion.project2.util.ProgramState
import java.util.*
import kotlin.collections.ArrayList

class SearchAnimalNameActivity(var mainController: MainController) : BaseActivity() {

    // 동물 정보를 담을 ArrayList
    var animalList:ArrayList<AnimalModel>? = null
    // 검색할 동물 이름
    lateinit var searchName:String
    // 입력을 위한 scanner
    lateinit var scanner: Scanner

    override fun initActivity() {
        // 동물 정보 가져오기
        animalList = AnimalInfoDAO.getAnimalInfoList()
        // scanner 생성
        scanner = Scanner(System.`in`)
    }

    override fun processActivity() {
    }

    override fun showActivity() {
        println()
        println("[ 동물 정보 이름 검색 ]")
        if(animalList == null) {
            println("저장된 동물 정보가 없습니다")
        } else {
            // 검색어 입력 받기
            inputSearchName()
            // 검색 결과 출력
            printSearchResult()
        }
    }

    override fun finishActivity() {
        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)
    }

    fun inputSearchName() {
        print("검색할 동물 이름 : ")
        searchName = scanner.next()
    }

    fun printSearchResult() {
        var findCnt = 0

        animalList?.forEach {
            if (it.name == searchName) {
                it.printAnimalInfo()
                findCnt++
            }
        }

        if (findCnt == 0) {
            println("검색된 동물이 없습니다")
        }
    }
}
package com.lion.project2.activity

import com.lion.project2.controller.MainController
import com.lion.project2.dao.AnimalInfoDAO
import com.lion.project2.model.AnimalModel
import com.lion.project2.util.ProgramState
import java.util.Scanner

class SearchAnimalTypeActivity(var mainController: MainController) : BaseActivity() {
    var animalList:ArrayList<AnimalModel>? = null
    var searchType = -1
    lateinit var scanner: Scanner

    override fun initActivity() {
        animalList = AnimalInfoDAO.getAnimalInfoList()
        scanner = Scanner(System.`in`)
    }

    override fun processActivity() {
    }

    override fun showActivity() {
        println()
        println("[ 동물 정보 타입 검색 ]")
        if(animalList == null) {
            println("저장된 동물 정보가 없습니다")
        } else {
            // 검색어 입력 받기
            inputSearchType()
            // 검색 결과 출력
            printSearchResult()
        }
    }

    override fun finishActivity() {
        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)
    }

    fun inputSearchType() {
        println("다음 중 동물의 타입을 선택해주세요")
        println("1. 사자")
        println("2. 호랑이")
        println("3. 코끼리")
        print("검색할 동물 타입 : ")
        searchType = scanner.nextInt()
        println()
    }

    fun printSearchResult() {
        var findCnt = 0
        animalList?.forEach {
            if(it.type == searchType) {
                it.printAnimalInfo()
                findCnt++
            }
        }

        if (findCnt == 0) {
            println("검색된 동물이 없습니다")
        }
    }
}
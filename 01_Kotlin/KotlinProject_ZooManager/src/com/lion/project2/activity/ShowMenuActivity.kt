package com.lion.project2.activity

import com.lion.project2.controller.MainController
import java.util.Scanner
import com.lion.project2.util.*

class ShowMenuActivity(var mainController: MainController) : BaseActivity() {

    // 메뉴 번호를 담을 프로퍼티
    var menuNumber = 0

    lateinit var scanner: Scanner

    override fun initActivity() {
        scanner = Scanner(System.`in`)
    }

    // 데이터 처리 메서드
    override fun processActivity() {

    }

    // 화면에 그리는 처리
    override fun showActivity() {
        println("[ 메인 메뉴 ]")
        println("1. 동물 정보 입력")
        println("2. 동물 이름 검색")
        println("3. 동물 타입 검색")
        println("4. 모든 동물의 정보 출력")
        println("5. 동물 삭제")
        println("6. 프로그램 종료")
        println()

        // 메뉴 번호 입력 받기
        inputMenuNumber()
    }

    // 수행이 끝났을 때 호출되는 메서드
    override fun finishActivity() {
        when(menuNumber) {
            MenuNumber.MAIN_MENU_INPUT_ANIMAL_INFO.num ->
                mainController.setProgramState(ProgramState.PROGRAM_STATE_INPUT_ANIMAL_INFO)
            MenuNumber.MAIN_MENU_SEARCH_ANIMAL_NAME_INFO.num ->
                mainController.setProgramState(ProgramState.PROGRAM_STATE_SEARCH_ANIMAL_NAME_INFO)
            MenuNumber.MAIN_MENU_SEARCH_ANIMAL_TYPE_INFO.num ->
                mainController.setProgramState(ProgramState.PROGRAM_STATE_SEARCH_ANIMAL_TYPE_INFO)
            MenuNumber.MAIN_MENU_SHOW_ANIMAL_INFO_ALL.num ->
                mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_ANIMAL_INFO_ALL)
            MenuNumber.MAIN_MENU_DELETE_ANIMAL_INFO.num ->
                mainController.setProgramState(ProgramState.PROGRAM_STATE_DELETE_ANIMAL_INFO)
            MenuNumber.MAIN_MENU_FINISH.num ->
                mainController.setProgramState(ProgramState.PROGRAM_STATE_FINISH)
        }
    }

    fun inputMenuNumber() {
        do {
            print("번호를 입력하세요 : ")
            menuNumber = scanner.nextInt()

            // 입력 받은 메뉴 번호가 1~5에 포함되지 않으면
            if (menuNumber !in 1..6) {
                println("메뉴 번호는 1부터 5까지의 숫자 중 하나를 입력해주세요")
            }

        } while (menuNumber !in 1..6)
    }
}
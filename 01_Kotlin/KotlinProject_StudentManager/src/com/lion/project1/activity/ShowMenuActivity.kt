package com.lion.project1.activity

import com.lion.project1.controller.MainController
import com.lion.project1.util.MenuNumber
import com.lion.project1.util.ProgramState
import java.util.Scanner

class ShowMenuActivity(var mainController: MainController) : BaseActivity() {
    // 메뉴 번호를 담을 프로퍼티
    var menuNumber = 0
    // 입력을 받기 위한 scanner
    lateinit var scanner: Scanner
    
    override fun initActivity() {
        scanner = Scanner(System.`in`)
    }

    override fun processActivity() {
    }

    override fun showActivity() {
        println()
        println("[ 메인 메뉴 ]")
        println("1. 학생 정보 입력")
        println("2. 학생 정보 검색")
        println("3. 학생 정보 전체 출력")
        println("4. 각 과목별 총점과 평균 확인")
        println("5. 종료")
        
        // 메뉴 번호 입력 받기
        inputMenuNumber()
    }

    override fun finishActivity() {
        // 사용자가 입력한 메뉴 번호에 따라 프로그램의 상태를 변경함
        when(menuNumber) {
            // 학생 정보 입력
            MenuNumber.MAIN_MENU_INPUT_STUDENT_INFO.num ->
                mainController.setProgramState(ProgramState.PROGRAM_STATE_INPUT_STUDENT_INFO)
            // 학생 정보 검색
            MenuNumber.MAIN_MENU_SEARCH_STUDENT_INFO.num ->
                mainController.setProgramState(ProgramState.PROGRAM_STATE_SEARCH_STUDENT_INFO)
            // 모든 학생 정보 출력
            MenuNumber.MAIN_MENU_SHOW_STUDENT_INFO_ALL.num ->
                mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_STUDENT_INFO_ALL)
            // 과목별 총점과 평균 출력
            MenuNumber.MAIN_MENU_SHOW_POINT_TOTAL_AVG.num ->
                mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_POINT_TOTAL_AVG)
            // 종료
            MenuNumber.MAIN_MENU_TERMINATE.num ->
                mainController.setProgramState(ProgramState.PROGRAM_STATE_TERMINATE)
        }
    }

    // 메뉴의 번호를 입력 받는 메서드
    fun inputMenuNumber() {
        do {
            print("번호를 입력하세요 : ")
            menuNumber = scanner.nextInt()

            // 입력 받은 메뉴 번호가 1~5에 포함되지 않으면
            if (menuNumber !in 1..5) {
                println("메뉴 번호는 1부터 5까지의 숫자 중 하나를 입력해주세요")
            }
            
        } while (menuNumber !in 1..5)
    }

}
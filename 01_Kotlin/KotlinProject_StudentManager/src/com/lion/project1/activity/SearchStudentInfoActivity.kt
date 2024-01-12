package com.lion.project1.activity

import com.lion.project1.controller.MainController
import com.lion.project1.dao.UserInfoDAO
import com.lion.project1.model.StudentModel
import com.lion.project1.util.ProgramState
import java.util.*
import kotlin.collections.ArrayList

class SearchStudentInfoActivity(var mainController: MainController) : BaseActivity() {
    // 내가 작업한 부분
//    lateinit var scanner: Scanner
//    lateinit var name:String
//    var studentList:ArrayList<StudentModel>? = null

    // 학생들의 정보를 담을 ArrayList
    var studentList:ArrayList<StudentModel>? = null
    // 검색할 학생 이름
    lateinit var searchName:String
    // 입력을 위한 scanner
    lateinit var scanner: Scanner

    override fun initActivity() {
        // 내가 작업한 부분
//        scanner = Scanner(System.`in`)
//        studentList = UserInfoDAO.getStudentInfoList()

        // 학생들의 정보 가져오기
        studentList = UserInfoDAO.getStudentInfoList()
        // scanner 생성
        scanner = Scanner(System.`in`)
    }

    override fun processActivity() {
    }

    override fun showActivity() {
        // 내가 작업한 부분
//        println()
//        println("[ 학생 정보 검색 ]")
//        inputSearchName()
//        printSearchResult()
//        printNotExistStudentInfo()

        // 강사랑 같이 작업한 부분
        // 저장된 학생 정보가 없는 경우
        println()
        println("[ 학생 정보 검색 ]")
        if(studentList == null) {
            println("저장된 학생 정보가 없습니다")
        } else {
            // 검색어 입력 받기
            inputSearchName()
            // 검색 결과 출력
            printSearchResult()
        }
    }

    override fun finishActivity() {
        // 내가 작업한 부분
//        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)

        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)
    }

    // 검색할 학생 이름 입력 받는 메서드
    fun inputSearchName() {
        // 내가 작업한 부분
//        print("검색할 학생 이름 : ")
//        name = scanner.next()

        print("검색할 학생 이름 : ")
        searchName = scanner.next()
    }

    // 검색된 학생들의 정보 출력 메서드
    fun printSearchResult() {
        // 내가 작업한 부분
//        for(searchStudent in studentList!!) {
//            if(searchStudent.name == name) {
//                println()
//                println("학생 이름 : ${searchStudent.name}")
//                println("학생 나이 : ${searchStudent.age}")
//                println("국어 점수 : ${searchStudent.kor}")
//                println("영어 점수 : ${searchStudent.eng}")
//                println("수학 점수 : ${searchStudent.math}")
//            }
//        }

        // 검색된 학생의 수
        var findCnt = 0
        // ArrayList가 관리하는 객체의 수만큼 반복
        studentList?.forEach {
            // 현재 반복 번째의 학생 이름이 검색어와 같다면 출력
            if(it.name == searchName) {
                it.printStudentInfo()
                // 검색된 학생 수를 증가시킴
                findCnt++
            }
        }
        // 검색된 학생이 없는 경우
        if(findCnt == 0) {
            println("검색된 학생이 없습니다")
        }
    }

    // 학생 정보가 없을 경우 안내 문구 출력 메서드
    fun printNotExistStudentInfo() {
        // 내가 작업한 부분 (chatGPT 도움)
//        if (studentList?.any { it.name == name } != true) {
//            println("검색된 학생이 없습니다.")
//        }

    }
}
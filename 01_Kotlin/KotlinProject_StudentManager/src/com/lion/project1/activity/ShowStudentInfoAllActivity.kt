package com.lion.project1.activity

import com.lion.project1.controller.MainController
import com.lion.project1.dao.UserInfoDAO
import com.lion.project1.model.StudentModel
import com.lion.project1.util.ProgramState

class ShowStudentInfoAllActivity(var mainController: MainController) : BaseActivity() {

    // 내가 작업한 부분
//    var studentList:ArrayList<StudentModel>? = null

    // 학생 정보 담을 ArrayList
    var studentList:ArrayList<StudentModel>? = null

    override fun initActivity() {
        // 내가 작업한 부분
//        studentList = UserInfoDAO.getStudentInfoList()

        // 학생 정보 가져오기
        studentList = UserInfoDAO.getStudentInfoList()
    }

    override fun processActivity() {
    }

    override fun showActivity() {
        // 내가 작업한 부분
//        println()
//        println("[ 학생 전체 정보 ]")
//        if (studentList == null) {
//            printNotExistStudentInfo()
//        } else {
//            printStudentInfoAll()
//        }

        // 강사랑 같이 작업한 부분
        println()
        println("[ 학생 전체 정보 ]")

        // 학생 정보가 없는 경우
        if(studentList == null) {
            println("저장한 학생 정보가 없습니다")
        } else {
            printStudentInfoAll()
        }

    }

    override fun finishActivity() {
        // 내가 작업한 부분
//        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)

        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)
    }

    // 모든 학생들의 정보 출력 메서드
    fun printStudentInfoAll() {
        // 내가 작업한 부분
//        studentList?.forEach {
//            it.printStudentInfo()
//        }

        // 학생 객체의 수만큼 반복
        studentList?.forEach {
//            println("학생 이름 : ${it.name}")
//            println("학생 나이 : ${it.age}")
//            println("국어 점수 : ${it.kor}")
//            println("영어 점수 : ${it.eng}")
//            println("수학 점수 : ${it.math}")
            it.printStudentInfo()
        }
    }

    // 학생 정보가 없을 경우 안내 문구 출력 메서드
    fun printNotExistStudentInfo() {
        // 내가 작업한 부분
//        println("학생 정보가 없습니다")
    }

}
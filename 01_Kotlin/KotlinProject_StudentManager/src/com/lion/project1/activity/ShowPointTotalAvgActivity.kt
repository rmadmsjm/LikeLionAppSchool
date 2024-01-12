package com.lion.project1.activity

import com.lion.project1.controller.MainController
import com.lion.project1.dao.UserInfoDAO
import com.lion.project1.model.StudentModel
import com.lion.project1.util.ProgramState

class ShowPointTotalAvgActivity(var mainController: MainController) : BaseActivity() {

    // 내가 작업한 부분
//    var studentList:ArrayList<StudentModel>? = null
//    var studentCnt = 0
//    var korTotalPoint = 0
//    var engTotalPoint = 0
//    var mathTotalPoint = 0
//    var korAvg = 0
//    var engAvg = 0
//    var mathAvg = 0

    // 학생 정보를 담을 ArrayList
    var studentList:ArrayList<StudentModel>? = null
    // 총점들을 담을 프로퍼티
    var korTotal = 0
    var engTotal = 0
    var mathTotal = 0
    // 평균을 담을 프로퍼티
    var korAvg = 0
    var engAvg = 0
    var mathAvg = 0

    override fun initActivity() {
        // 내가 작업한 부분
//        studentList = UserInfoDAO.getStudentInfoList()

        // 학생들 정보 가져오기
        studentList = UserInfoDAO.getStudentInfoList()
    }

    override fun processActivity() {

        if (studentList != null) {
            // 총점 구하기
            getPointTotal()
            // 평균 구하기
            getPointAvg()
        }
    }

    override fun showActivity() {
        // 내가 작업한 부분
//        println()
//        println("[ 점수 통계 ]")
//        if (studentList == null) {
//            printNotExistStudentInfo()
//        } else {
//            getPointTotal()
//            getPointAvg()
//        }

        // 강사랑 같이 작업한 부분
        println()
        println("[ 점수 통계 ]")

        if (studentList == null) {
            println("저장된 학생 정보가 없습니다")
        } else {
            println("국어 총점 : ${korTotal}")
            println("영어 총점 : ${engTotal}")
            println("수학 총점 : ${mathTotal}")
            println("국어 평균 : ${korAvg}")
            println("영어 평균 : ${engAvg}")
            println("수학 평균 : ${mathAvg}")
        }
    }

    override fun finishActivity() {
        // 내가 작업한 부분
//        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)

        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)
    }

    // 과목별 총점 계산 메서드
    fun getPointTotal() {
        // 내가 작업한 부분
//        for(point in studentList!!) {
//            studentCnt++
//            korTotalPoint += point.kor
//            engTotalPoint += point.eng
//            mathTotalPoint += point.math
//        }
//
//        println("국어 총점 : ${korTotalPoint}점")
//        println("영어 총점 : ${engTotalPoint}점")
//        println("수학 총점 : ${mathTotalPoint}점")

        // 각 과목별 총점 구하기
        studentList?.forEach {
            korTotal += it.kor
            engTotal += it.eng
            mathTotal += it.math
        }
    }

    // 과목별 평균 계산 메서드
    fun getPointAvg() {
        // 내가 작업한 부분
//        korAvg = korTotalPoint / studentCnt
//        engAvg = engTotalPoint / studentCnt
//        mathAvg = mathTotalPoint / studentCnt
//
//        println("국어 평균 : ${korAvg}점")
//        println("영어 평균 : ${engAvg}점")
//        println("수학 평균 : ${mathAvg}점")

        // 각 과목별 평균 구하기
        korAvg = korTotal / studentList!!.size
        engAvg = engTotal / studentList!!.size
        mathAvg = mathTotal / studentList!!.size
    }

    // 학생정보가 없을 경우 안내 문구 출력하는 메서드
    fun printNotExistStudentInfo() {
        // 내가 작업한 부분
//        println("학생 정보가 없습니다")
    }

}
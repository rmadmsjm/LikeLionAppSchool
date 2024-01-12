package com.lion.project1.activity

import com.lion.project1.controller.MainController
import com.lion.project1.dao.UserInfoDAO
import com.lion.project1.model.StudentModel
import com.lion.project1.util.ProgramState
import java.util.Scanner

class InputStudentInfoActivity(var mainController: MainController) : BaseActivity() {

    // 입력된 학생 정보 담을 객체
    var studentList:ArrayList<StudentModel>? = null
    // 학생의 정보를 입력 받을 객체
    lateinit var studentModel: StudentModel
    // 정보 입력 받기 위한 scanner
    lateinit var scanner: Scanner

    override fun initActivity() {
        scanner = Scanner(System.`in`)

        // 학생정보 가져오기
        studentList = UserInfoDAO.getStudentInfoList()
    }

    override fun processActivity() {
    }

    override fun showActivity() {
        println()
        println("[ 학생 정보 입력 ]")
        // 현재 학생 수 출력
        showStudentCnt()
        // 현재 학생 정보 입력 받기
        inputStudentInfo()
        // 학생 정보 파일에 쓰기
        saveStudentInfo()
    }

    override fun finishActivity() {
        // 작업 완료 후 상태를 메뉴를 보여주는 상태로 변경
        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)
    }

    // 현재 입력된 학생 수를 보여주는 메서드
    fun showStudentCnt() {
        if(studentList != null) {
            println("현재 입력된 학생 수 : ${studentList?.size}명")
        } else {
            println("현재 입력된 학생 수 : 0명")
        }
    }

    // 학생의 정보를 입력 받는 메서드
    fun inputStudentInfo() {

        var name = ""
        var age = -1
        var kor = -1
        var eng = -1
        var math = -1

        print("학생 이름 : ")
        name = scanner.next()

        do {
            try {
                print("학생 나이 : ")
                age = scanner.nextInt()

                if(age !in 0..200) {
                    println("나이는 0 ~ 200까지의 값을 넣어주세요")
                }

            } catch (e: Exception) {
                println("다시 입력해주세요")
            }

        } while(age !in 0..200)

        print("국어 점수 : ")
        kor = scanner.nextInt()
        print("영어 점수 : ")
        eng = scanner.nextInt()
        print("수학 점수 : ")
        math = scanner.nextInt()

        studentModel = StudentModel(name, age, kor, eng, math)
    }

    // 입력 받은 학생의 정보를 저장하는 메서드
    fun saveStudentInfo() {
        // ArrayList가 null 이면 객체를 생성함
        if(studentList == null){
            studentList = ArrayList<StudentModel>()
        }

        // 객체를 ArrayList에 담기
        studentList?.add(studentModel)
        // 파일에 저장
        UserInfoDAO.saveStudentInfoList(studentList!!)

    }
}
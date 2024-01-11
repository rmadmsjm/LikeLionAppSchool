package com.lion.project1.activity

import com.lion.project1.model.StudentModel
import java.util.Scanner

class InputStudentInfoActivity : BaseActivity() {

    // 입력된 학생 정보 담을 객체
    var studentList:ArrayList<StudentModel>? = null
    // 학생의 정보를 입력 받을 객체
    lateinit var studentModel: StudentModel
    // 정보 입력 받기 위한 scanner
    lateinit var scanner: Scanner

    override fun initActivity() {
        scanner = Scanner(System.`in`)
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
    }

    override fun finishActivity() {
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

    }
}
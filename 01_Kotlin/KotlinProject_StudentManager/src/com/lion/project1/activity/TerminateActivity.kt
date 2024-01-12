package com.lion.project1.activity

import kotlin.system.exitProcess

class TerminateActivity : BaseActivity() {
    override fun initActivity() {
    }

    override fun processActivity() {
    }

    override fun showActivity() {
        // 내가 작업한 부분
//        println("프로그램이 종료되었습니다")

        // 강사랑 같이 작업한 부분
        println()
        println("프로그램이 종료되었습니다")
        // 프로그램 종료
        // System.exit(0)
        // 프로그램 종료 코드 : 0 (정상 종료)
        exitProcess(0)
    }

    override fun finishActivity() {
    }

    // 프로그램 종료 메서드
    fun finishProgram() {

    }

    // 프로그램 종료 안내 문구 출력 메서드
    fun showFinishMessage() {

    }
}
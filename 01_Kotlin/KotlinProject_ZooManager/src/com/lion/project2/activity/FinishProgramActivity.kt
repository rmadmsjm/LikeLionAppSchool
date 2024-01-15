package com.lion.project2.activity

import kotlin.system.exitProcess

class FinishProgramActivity : BaseActivity() {
    override fun initActivity() {
    }

    override fun processActivity() {
    }

    override fun showActivity() {
        println()
        println("프로그램이 종료되었습니다")
        exitProcess(0)
    }

    override fun finishActivity() {
    }
}
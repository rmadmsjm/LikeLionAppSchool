package com.lion.project2.activity

import com.lion.project2.controller.MainController
import com.lion.project2.util.ProgramState
import java.util.Scanner

class InputAnimalInfoActivity(var mainController: MainController) : BaseActivity() {

    lateinit var scanner: Scanner

    override fun initActivity() {
        scanner = Scanner(System.`in`)
    }

    override fun processActivity() {
    }

    override fun showActivity() {
        println()
        println("[ 동물 정보 입력 ]")
    }

    override fun finishActivity() {
        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)
    }

    fun showAnimalCnt() {

    }

    fun inputAnimalInfo() {
        var type = -1
        var name = ""
        var age = -1
        var furCnt = -1
        var stripesCnt = -1
        var noseLength = -1

        print("동물 종류 (1. 사자, 2. 호랑이, 3.코끼리) : ")
        type = scanner.nextInt()

        print("동물의 이름 : ")
        name = scanner.next()

        print("동물의 나이 : ")
        age = scanner.nextInt()

        if (type == 1) {
            print("털의 개수 : ")
            furCnt = scanner.nextInt()
        } else if (type == 2) {
            print("줄무늬 개수 : ")
            stripesCnt = scanner.nextInt()
        } else if (type == 3) {
            print("코의 길이 : ")
            noseLength = scanner.nextInt()
        }
    }
}
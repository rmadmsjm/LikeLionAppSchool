package com.lion.project2.controller

import com.lion.project2.activity.*
import com.lion.project2.util.ProgramState

class MainController {

    // 상태값을 담을 변수
    var programStateValue = ProgramState.PROGRAM_STATE_SHOW_MENU

    // Activity 객체의 주소값을 담을 변수
    lateinit var activity:BaseActivity

    fun run() {
        while (true) {
            // 상태에 따른 객체 생성
            activity = getStateClass()

            // 초기화 메서드 호출
            activity.initActivity()

            // 처리 메서드 호출
            activity.processActivity()

            // 화면을 출력하는 메서드 호출
            activity.showActivity()

            // 현재 상태 종료 메서드 호출
            activity.finishActivity()
        }
    }

    // 상태값에 따라 객체를 생성해서 반환하는 메서드
    fun getStateClass() = when(programStateValue) {
        ProgramState.PROGRAM_STATE_SHOW_MENU -> ShowMenuActivity(this)
        ProgramState.PROGRAM_STATE_INPUT_ANIMAL_INFO -> InputAnimalInfoActivity()
        ProgramState.PROGRAM_STATE_SEARCH_ANIMAL_NAME_INFO -> SearchAnimalNameActivity()
        ProgramState.PROGRAM_STATE_SEARCH_ANIMAL_TYPE_INFO -> SearchAnimalTypeActivity()
        ProgramState.PROGRAM_STATE_SHOW_ANIMAL_INFO_ALL -> ShowAllAnimalInfoActivity()
        ProgramState.PROGRAM_STATE_DELETE_ANIMAL_INFO -> DeleteAnimalInfoActivity()
        ProgramState.PROGRAM_STATE_FINISH -> FinishProgramActivity()
    }

    // 상태값을 변경하는 메서드
    fun setProgramState(programState: ProgramState) {
        this.programStateValue = programState
    }
}
package com.lion.project1.util

// 프로그램 전체 상태를 정의
enum class ProgramState {
    // 메뉴를 보여주는 상태
    PROGRAM_STATE_SHOW_MENU,
    //학생 정보를 입력하는 상태
    PROGRAM_STATE_INPUT_STUDENT_INFO,
    // 학생 정보를 검색하는 상태
    PROGRAM_STATE_SEARCH_STUDENT_INFO,
    // 학생 정보 전체를 출력하는 상태
    PROGRAM_STATE_SHOW_STUDENT_INFO_ALL,
    // 각 과목별 총점과 평균을 확인하는 상태
    PROGRAM_STATE_SHOW_POINT_TOTAL_AVG,
    // 종료 상태
    PROGRAM_STATE_TERMINATE
}

// 각 메뉴
enum class MenuNumber(var num:Int){
    // 학생 정보 입력
    MAIN_MENU_INPUT_STUDENT_INFO(1),
    // 학생 정보 검색
    MAIN_MENU_SEARCH_STUDENT_INFO(2),
    // 학생 정보 전체 출력
    MAIN_MENU_SHOW_STUDENT_INFO_ALL(3),
    // 각 과목별 총점과 평균 확인
    MAIN_MENU_SHOW_POINT_TOTAL_AVG(4),
    // 종료
    MAIN_MENU_TERMINATE(5)
}
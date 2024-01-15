package com.lion.project2.util

enum class ProgramState {
    // 메뉴를 보여주는 상태
    PROGRAM_STATE_SHOW_MENU,
    // 동물 정보를 입력하는 상태
    PROGRAM_STATE_INPUT_ANIMAL_INFO,
    // 동물 이름으로 정보를 검색하는 상태
    PROGRAM_STATE_SEARCH_ANIMAL_NAME_INFO,
    // 동물 타입으로 정보를 검색하는 상태
    PROGRAM_STATE_SEARCH_ANIMAL_TYPE_INFO,
    // 동물 정보 전체를 출력하는 상태
    PROGRAM_STATE_SHOW_ANIMAL_INFO_ALL,
    // 동물 정보를 삭제하는 상태
    PROGRAM_STATE_DELETE_ANIMAL_INFO,
    // 종료 상태
    PROGRAM_STATE_FINISH
}

enum class MenuNumber(var num:Int) {
    // 동물 정보 입력
    MAIN_MENU_INPUT_ANIMAL_INFO(1),
    // 동물 정보 이름 검색
    MAIN_MENU_SEARCH_ANIMAL_NAME_INFO(2),
    // 동물 정보 타입 검색
    MAIN_MENU_SEARCH_ANIMAL_TYPE_INFO(3),
    // 동물 정보 전체 출력
    MAIN_MENU_SHOW_ANIMAL_INFO_ALL(4),
    // 동물 정보 삭제
    MAIN_MENU_DELETE_ANIMAL_INFO(5),
    // 종료
    MAIN_MENU_FINISH(6)
}

enum class AnimalTypeNumber(var num:Int) {
    LION(1),
    TIGER(2),
    ELEPHANT(3)
}
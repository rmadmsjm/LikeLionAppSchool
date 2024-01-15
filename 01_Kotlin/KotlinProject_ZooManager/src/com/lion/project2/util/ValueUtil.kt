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

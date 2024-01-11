package com.lion.project1.activity

abstract class BaseActivity {

    // 초기화 메서드
    abstract fun initActivity()

    // 데이터 처리 메서드
    abstract fun processActivity()

    // 화면에 그리는 처리
    abstract fun showActivity()

    // 수행이 끝났을 때 호출되는 메서드
    abstract fun finishActivity()
}
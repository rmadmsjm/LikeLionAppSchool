package com.lion.project1.model

// 주 생성자에서 정의한 프로퍼티
// 학생의 이름, 학생의 나이, 학생의 국어 점수, 학생의 영어 점수, 학생의 수학 점수
// 주 생성자 사용 시, 이 클래스를 가지고 객체를 생성할 때 이 값을 가지고 있고 그것을 던져줘야 객체 생성 가능
// 따라서 입력을 받은 후에 이 객체를 사용할 수 잇음
// 그런데 이 클래스에 힉셍 정보 입력 기능을 이 클래스에 구현하는 방법은?
// -> 없음, 주 생성자로 지정하지 않아야 가능함
class StudentModel(var name:String, var age:Int, var kor:Int, var eng:Int, var math:Int) {

    // 학생 한 명의 정보 출력 메서드
    fun printStudentInfo() {

    }
}
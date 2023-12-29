import java.util.Scanner

/*
키보드로부터 입력을 받아 짝수라면 "짝수입니다" 출력
홀수라면 "홀수입니다" 출력
 */

fun main() {
    checkNum(inputNum())
}

fun inputNum() : Int {
    val scanner = Scanner(System.`in`)
    print("숫자를 입력하세요 : ")
    val num = scanner.nextInt()
    return num
}

fun checkNum(num:Int){
    if(num % 2 == 0) {
        println("짝수입니다")
    } else {
        println("홀수입니다")
    }
}

// ----------강사님 설명----------
// step1) 기능 정리
// 1. 키보드로부터 입력 받는 기능
// 2. 짝수 여부를 판단하여 출력하는 기능

// step2) 각 기능별 함수를 구현
// 아직 함수 내부의 코드는 작성하지 않음

// step3) 각 함수 내부 코드를 구현
// 이때, 함수 하나의 구현이 끝나면 의도한대로 동작하는지 반드시 테스트

// step4) 프로그램이 동작하도록 main 함수에서 각 함수들을 순서에 맞게 호출

/*
fun main() {
    val number:Int = inputNumber()
    printResult(number)
}

//키보드로부터 입력 받는 기능
fun inputNumber():Int {
    val scanner = Scanner(System.`in`)

    // 입력을 받음
    print("숫자를 입력하세요 : ")
    val number = scanner.nextInt()

    // 입력 받은 값을 반환
    return number
}

// 짝수 여부를 판단하여 출력하는 기능
fun printResult(number:Int) {
    if(number % 2 == 0) {
        println("짝수입니다")
    } else {
        println("홀수입니다")
    }
}
 */
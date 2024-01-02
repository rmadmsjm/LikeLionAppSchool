import java.util.Scanner

/*
사용자에게 정수를 입력받아 누적한다
사용자가 0을 입력하면 입력을 중단하고 그때까지의 총합을 출력한다
 */

fun main() {
    val num = inputNum()
    val sum = getTotal(num)
    printTotal(sum)
}

// 입력 받기
fun inputNum():Int {
    val scanner = Scanner(System.`in`)
    print("숫자를 입력하세요 (0을 입력하면 끝) : ")
    val num = scanner.nextInt()
    return num
}

// 총합 구하기
fun getTotal(number:Int):Int {
    var sum = 0
    var num = number
    while (num != 0) {
        sum += num
        // 사용자 입력에 따라 num의 값 업데이트
        num = inputNum()
    }
    return sum
}

// 총합 출력
fun printTotal(sum:Int) {
    println("총합은 $sum")
}

// ----------강사님 설명----------
// step1) 기능 정리
// 1. 사용자에게 정수를 입력 받아 누적
// 2. 총합을 출력

// step2) 각 기능별 함수를 구현
// 아직 함수 내부의 코드는 작성하지 않음

// step3) 각 함수 내부 코드를 구현
// 이때, 함수 하나의 구현이 끝나면 의도한대로 동작하는지 반드시 테스트

// step4) 프로그램이 동작하도록 main 함수에서 각 함수들을 순서에 맞게 호출

/*
fun main() {
    // 숫자를 입력 받아 누적
    val total = getTotal()
    // 출력
    printResult(total)
}

// 1. 사용자에게 정수를 입력 받아 누적
fun getTotal():Int {
    val scanner = Scanner(System.`in`)

    // 누적값을 담을 변수
    var total = 0

    // 입력 받은 숫자를 담을 변수
    var inputNumber:Int

    do {
        // 정수 입력 받기
        print("숫자를 입력하세요 : ")
        inputNumber = scanner.nextInt()
        // 누적
        total += inputNumber
    } while(inputNumber != 0)

    /*
    var inputNumber = -1
    while(inputNumber != 0) {
        inputNumber = scanner.nextInt()
        total += inputNumber
    }
     */

    return  total
}

// 2. 총합을 출력
fun printResult(total:Int) {
    println("총합은 ${total}입니다")
}
 */
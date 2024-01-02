import java.util.Scanner

// 사용자에게 정수를 입력 받고 1부터 입력 받은 숫자까지의 총합을 구한다

fun main() {
    val num = inputNum()
    val sum = getTotal(num)
    printTotal(num, sum)
}

// 입력 받기
fun inputNum():Int {
    val scanner = Scanner(System.`in`)
    print("숫자를 입력해주세요 : ")
    val num = scanner.nextInt()
    return num
}

// 1부터 입력 받은 숫자까지 총합
fun getTotal(number:Int):Int {
    var sum = 0
    for (num in 1..number) {
        sum += num
    }
    return sum
}

// 출력
fun printTotal(num:Int, sum:Int) {
    println("1부터 ${num}까지의 총합은 $sum")
}

// ----------강사님 설명----------
// step1) 기능 정리
// 1. 정수를 입력 받는 기능
// 2. 1부터 입력 받은 숫자까지의 총합
// 3. 출력

// step2) 각 기능별 함수를 구현
// 아직 함수 내부의 코드는 작성하지 않음

// step3) 각 함수 내부 코드를 구현
// 이때, 함수 하나의 구현이 끝나면 의도한대로 동작하는지 반드시 테스트

// step4) 프로그램이 동작하도록 main 함수에서 각 함수들을 순서에 맞게 호출

/*
fun main() {
    // 숫자 입력 받기
    val number = inputNumber()

    // 1부터 입력 받은 숫자까지의 총합
    val total = getTotal(number)

    // 출력
    printResult(number, total)
}

// 1. 정수를 입력 받는 기능
fun inputNumber():Int {
    val scanner = Scanner(System.`in`)
    print("숫자를 입력해주세요 : ")
    val number = scanner.nextInt()
    return number
}

// 2. 1부터 입력 받은 숫자까지의 총합
fun getTotal(number:Int):Int {
    // 누적할 값을 담을 변수
    var total = 0

    // 1부터 입력 받은 숫자까지 반복
    for(v1 in 1..number) {
        total += total
    }

    return total
}

// 3. 출력
fun printResult(number: Int, total:Int) {
    println("1부터 ${number}까지의 총합은 ${total}입니다")
}
 */
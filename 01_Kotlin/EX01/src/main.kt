import java.util.Scanner

/*
키보드로부터 입력을 받아 출력하는 프로그램
1. 키보드로부터 입력 받는 기능
2. 출력하는 기능
*/

fun main() {
    // 키보드로부터 입력 받음
    val n1 = inputNumber()
    // 출력
    printNumber(n1)

    //printNumber(inputNumber())
}

// 키보드로부터 입력 받는 기능
fun inputNumber() : Int {
    val scanner = Scanner(System.`in`)
    // 키보드로부터 정수값 입력받음
    print("숫자를 입력하세요 : ")
    val num = scanner.nextInt()
    // 입력값 반환
    return num
}

// 출력하는 기능
fun printNumber(num:Int) {
    println("입력 받은 숫자 : $num")
}
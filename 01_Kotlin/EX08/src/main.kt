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
        // 사용자 입력에 따라 num의 값을 업데이트
        num = inputNum()
    }
    return sum
}

// 총합 출력
fun printTotal(sum:Int) {
    println("총합은 $sum")
}
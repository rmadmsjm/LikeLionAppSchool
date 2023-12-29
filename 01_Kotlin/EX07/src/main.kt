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
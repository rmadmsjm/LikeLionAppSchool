/*
1부터 1씩 증가시키는 값 중 3의 배수이거나 7의 배수인 숫자를 누적한다
누적값이 10000이상이 됐을 때의 숫자가 몇인지 출력한다
 */

fun main() {
    val num = getTotal()
    printTotal(num)
}

// 3의 배수 이거나 7의 배수 숫자 누적
fun getTotal():Int {
    var sum = 0
    var num = 1
    while(true) {
        if(num % 3 == 0 || num % 7 == 0) {
            sum += num
            // sum이 10000이상일 때 반환
            if(sum >= 10000) {
                return num
            }
        }
        num++
    }
}

// 출력
fun printTotal(num:Int) {
    println("누적값 10000이상일 때 숫자는 $num")
}
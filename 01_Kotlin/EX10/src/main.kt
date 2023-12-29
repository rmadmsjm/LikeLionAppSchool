/*
철수가 저금을 한다
첫날 100원을 저금한다
두번째 날 부터는 전날 잔고액의 3배를 저금한다
1일차 부터 30일차까지의 통장 잔고액을 모두 출력한다
e.g.)
1일차 : 100원
2일차 : 400원
3일차 : 1600원
..
30일차 : 0000원

조건 : 변수는 Int 타입의 변수만 사용한다
 */

fun main() {
    printTotal()
}

// 저금 및 출력
fun printTotal() {
    var money = 100
    var sum = 0
    for(day in 1..30) {
        sum += money

        // 오버플로우 해결 대체
        if (money <= 0 || sum <= 0) {
            println("오버플로우가 발생해 프로그램을 종료합니다.")
            return
        }

        println("${day}일차 : ${sum}원")
        money = sum * 3
    }
}
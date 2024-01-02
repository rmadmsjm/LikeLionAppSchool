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

// ----------강사님 설명----------
// step1) 기능 정리
// 1. 통장 잔고액을 구해 출력

// step2) 각 기능별 함수를 구현
// 아직 함수 내부의 코드는 작성하지 않음

// step3) 각 함수 내부 코드를 구현
// 이때, 함수 하나의 구현이 끝나면 의도한대로 동작하는지 반드시 테스트

// step4) 프로그램이 동작하도록 main 함수에서 각 함수들을 순서에 맞게 호출

/*
fun main() {
    getBanking()
}

// 1. 통장 잔고액을 구해 출력
fun getBanking() {
    // 첫날은 100원을 저금
    var bankingMoney1:Int = 100
    var bankingMoney2:Int = 0
    var bankingMoney3:Int = 0

    printBanking(1, bankingMoney3, bankingMoney2, bankingMoney1)

    // 2일차부터 30일차까지 반복
    for(date in 2..30) {
        // 전날 잔고액의 3배 저금
        bankingMoney1 *= 4
        bankingMoney2 *= 4
        bankingMoney3 *= 4

        // Int 변수가 가질 수 있는 값의 범위를 넘어가는 것을 예방하기 위해
        // 변수의 값은 0 ~ 99999999로 제한
        bankingMoney2 += (bankingMoney1 / 100000000)
        bankingMoney1 %= 100000000

        bankingMoney3 += (bankingMoney2 / 100000000)
        bankingMoney2 %= 100000000

        printBanking(date, bankingMoney3, bankingMoney2, bankingMoney1)
    }
}

fun printBanking(date:Int, m1:Int, m2:Int, m3:Int) {
    if(m1 == 0 && m2 == 0) {
        // format : 출력 서식을 이용하여 문자열을 만들 수 있다
        // 정수 : %d, 실수 : %f, 문자열 : %s, 문자 : %c
        // %숫자d, %숫자s : 숫자만큼 자리를 확보한 다음 우측 정렬로 출력, 비어있는 공간은 공백으로 채움
        // %.숫자.f : 소수점 이하의 값을 숫자만큼만 표시
        // %0숫자d : 숫자만큼 자리를 확보한 다음 우측 정렬로 출력, 비어잇는 공간은 0으로 채움

        val a1 = "%2d일차 : %8s%8s%8d원".format(date, "", "", m3)
        println(a1)
    } else if(m1 == 0){
        var a1 = "%2d일차 : %8s%8d%08d원".format(date, "", m2, m3)
        println(a1)
    } else {
        var a1 = "%2d일차 : %8d%08d%08d원".format(date, m1, m2, m3)
        println(a1)
    }
}
 */
/*
1부터 1씩 증가시키는 값 중 3의 배수이거나 7의 배수인 숫자를 누적한다
누적값이 10000이상이 됐을 때의 숫자가 몇인지 출력한다
 */

fun main() {
    val num = getNumber()
    printTotal(num)
}

// 3의 배수 이거나 7의 배수 숫자 누적
fun getNumber():Int {
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

// ----------강사님 설명----------
// step1) 기능 정리
// 1. 1부터 1씩 증가시키면서 3의 배수나 7의 배수인 숫자를 누적 (이때 누적값이 10000이상 되었을 때의 숫자 반환)
// 2. 반환된 숫자 출력

// step2) 각 기능별 함수를 구현
// 아직 함수 내부의 코드는 작성하지 않음

// step3) 각 함수 내부 코드를 구현
// 이때, 함수 하나의 구현이 끝나면 의도한대로 동작하는지 반드시 테스트

// step4) 프로그램이 동작하도록 main 함수에서 각 함수들을 순서에 맞게 호출

/*
fun main() {
    // 누적값이 10000이상일 때의 숫자 출력
    val number = findNumber()
    // 출력
    printResult(number)
}

// 1. 1부터 1씩 증가시키면서 3의 배수나 7의 배수인 숫자를 누적 (이때 누적값이 10000이상 되었을 때의 숫자 반환)
fun findNumber():Int {
    // 1부터 1씩 증가되는 값을 담을 변수
    var number = 0
    // 누적값을 담을 변수
    var total = 0

    // 누적값이 10000보다 작으면 반복
    while(total < 10000) {
        // 숫자 1을 중가
        number++

        // 3의 배수이거나 7의 배수라면 누적
        if(number % 3 == 0 || number % 7 ==0 ) {
            total += number
        }
    }

    // 현재의 숫자 반환
    return number
}

// 2. 반환된 숫자 출력
fun printResult(number:Int) {
    println("찾은 숫자는 ${number}입니다")
}
 */
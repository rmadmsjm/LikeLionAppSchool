// 1부터 100까지의 홀수의 합을 출력

fun main() {
    var num = 1
    var sum = 0
    while(num <= 100) {
        if(num % 2 != 0) {
            sum += num
        }
        num++
    }
    println("sum : $sum")
}

// for문
fun sumFor() {
    var sum = 0
    for (num in 1..100 step 2) {
        sum += num
    }
    println("sum : $sum")
}

// ----------강사님 설명----------
// step1) 기능 정리
// 1. 1부터 100까지의 홀수 총합
// 2. 출력

// step2) 각 기능별 함수를 구현
// 아직 함수 내부의 코드는 작성하지 않음

// step3) 각 함수 내부 코드를 구현
// 이때, 함수 하나의 구현이 끝나면 의도한대로 동작하는지 반드시 테스트

// step4) 프로그램이 동작하도록 main 함수에서 각 함수들을 순서에 맞게 호출

/*
fun main() {

}

// 1부터 100까지의 홀수의 총합
fun getTotal() {
    var total = 0
    
    // 1부터 100까지 반복
    for (v1 in 1..100) {
        if (v1 % 2 == 1) {
            // 홀수면 누적
            total += v1
        }
    }

    // 홀수만 누적해야 하기 때문에 1부터 2씩 증가시키며 반복
    for (v1 in 1..100 step 2) {
        total += v1
    }
}

// 출력
fun printTotal(total:Int) {
    println("1부터 100까지의 홀수의 합은 $total")
}
 */
// when : 주어진 변수의 수식의 값에 맞는 부분이 수행됨

fun main() {
    
    val a1 = 10
    
    // 변수 a1의 값에 맞는 부분이 수행됨
    when(a1) {
        // 수행될 코드가 한 줄이라면 {}로 묶지 않아도 됨
        1 -> println("a1은 1")
        5 -> println("a1은 5")
        // 수행될 코드가 여러줄이라면 {}로 묶음
        10 -> {
            println("a1은 10")
            println("두 번째 줄")
            println("세 번째 줄")
        }
        else -> println("a1은 1, 5, 10이 아님")
    }

    // 두 가지 이상의 조건을 만족하는 것도 설정 가능
    val a2 = 3
    when(a2) {
        1, 2 -> println("a2는 1이거나 2")
        3, 4 -> println("a2는 3이거나 4")
        5, 6, 7 -> println("a2는 5이거나 6이거나 7")
        else -> println("a2는 1, 2, 3, 4, 5, 6이 아님")
    }

    // 실수도 가능
    val a3 = 55.55
    when(a3) {
        33.33 -> println("a3은 33.33")
        55.55 -> println("a4는 55.55")
        77.77 -> println("a3은 77.77")
        else -> println("a4는 33.33, 55.55, 77.77이 아님")
    }
    
    // 문자열
    val a4 = "문자열2"
    when(a4) {
        "문자열1" -> println("첫 번째 문자열")
        "문자열2" -> println("두 번째 문자열")
        "문자열3" -> println("세 번재 문자열")
        else -> println("else 문자열")
    }
    
    // 논리값
    // 논리값을 사용할 경우 true, false 모두 처리해줘야 함
    val a5 = true
    when(a5) {
        true -> println("a5는 true")
        false -> println("a5는 false")
    }
    
    // 범위 지정
    val a6 = 5
    when(a6) {
        in 1..3 -> println("a6는 1~3")
        in 4..6 -> println("a6은 4~6")
        else -> println("a6은 1~6이 아님")
    }

    // 범위 관련된 예제
    // 1 ~ 10
    if(a6 in 1..10) {
        println("a6은 1이상 10이하")
    } 
    
    if (a6 in 1 until 10) {
        println("a6은 1이상 10미만")
    }

    // 조건에 따라 값을 반환하는 함수를 사용해 변수에 값을 저장
    var a7 = setValue1(1)
    var a8 = setValue1(2)
    var a9 = setValue1(3)
    println("a7 : $a7")
    println("a8 : $a8")
    println("a9 : $a9")

    var a10 = setValue2(1)
    var a11 = setValue2(2)
    var a12 = setValue2(3)
    println("a10 : $a10")
    println("a11 : $a11")
    println("a12 : $a12")
    
    var a13:String
    var a14 = 2
    
    if(a14 == 1) {
        a13 = "첫 번째 문자열"
    } else if(a14 == 2) {
        a13 = "두 번째 문자열"
    } else if(a14 == 3) {
        a13 = "세 번째 문자열"
    } else {
        a13 = "그 외의 문자열"
    }
    println("a13 : $a13")
    
    var a15 = when(a14) {
        1 -> "첫 번째 문자열"
        2 -> "두 번째 문자열"
        3 -> "세 번째 문자열"
        else -> "그 외의 문자열"
    }
    println("a15 : $a15")
}

// 조건에 따라 값을 반환하는 함수
fun setValue1(a1:Int) : String {
    if(a1 == 1) {
        return "문자열1"
    } else if(a1 == 2) {
        return "문자열2"
    } else {
        return "그 외의 문자열"
    }
}

// 위의 함수 코드를 when으로 바꿀 수 있음
fun setValue2(a1:Int) = when(a1) {
    1 -> "문자열1"
    2 -> "문자열2"
    else -> "그 외의 문자열"
}
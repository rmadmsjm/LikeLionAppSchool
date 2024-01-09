fun main() {
    val r1 = testFun1(100, 200)
    println("r1 : $r1")

    val r2 = testFun2(100, 200)
    println("r2 : $r2")

    val r3 = testFun3(100, 200)
    println("r3 : $r3")

    val r4 = lamda1(100, 200)
    println("r4 : $r4")

    val r5 = lamda2(100, 200)
    println("r5 : $r5")

    val r6 = lamda3(100, 200)
    println("r6 : $r6")

    val r7 = lamda4(100, 200)
    println("r7 : $r7")
}

// 매개 변수로 들어오는 값을 계산하여 반환하는 함수
fun testFun1(a1:Int, a2:Int) : Int {
    return a1 + a2
}

// 위의 함수는 다음과 같이 작성 가능
// = 다음에 작성한 수식을 계산해 반환함
fun testFun2(a1:Int, a2:Int) : Int = a1 + a2

// 만약 매개변수로 들어오는 값을 계산하여 반환하는 함수 내부의 코드가 여러 줄인 경우
fun testFun3(a1:Int, a2:Int) : Int {
    var r1 = a1 + 100
    var r2 = a2 + 200
    var r3 = r1 + r2
    return r3
}

// 매개변수로 들어오는 값을 가지고 계산한 다음 변환하는 함수의 코드가 여러 줄일 경우
// 아래처럼 작성하는 것은 불가능
// 대신 람다 사용
//fun testFun4(a1:Int, a2:Int) : Int = {
//    var r1 = a1 + 100
//    var r2 = a2 + 200
//    var r3 = r1 + r2
//    r3
//}

// 람다 : 전달 받은 값을 가지고 계산을 하고 그 결과를 반환하는 식
// 람다식 or 람다 함수라고 부름
// Kotlin : 람다식을 가지고 있는 함수를 람다 함수라고 함
// (Int, Int) : 매개변수의 타입 정의
// -> Int : 반환 타입 정리
// {a1:Int, a2:Int : 매개변수의 이름을 정의, 앞서 정의한 매개변수의 타입과 일치해야 함
// -> a1 + a2} : 반환할 값을 작성한 부분
val lamda1 : /*람다 함수*/(Int, Int) -> Int = {/*람다식*/ a1:Int, a2:Int -> a1 + a2 }

// Kotlin은 람다 함수 대신 람다식만 작성해도 됨
val lamda2 = {a1:Int, a2:Int -> a1 + a2}

// 타입을 생략할 수 있음
val lamda3 : (Int, Int) -> Int = {a1, a2 -> a1 + a2}

// 람다는 '->' 다음에 나오는 코드 중에 제일 마짐가에 작성한 수식의 결과를 반환함
// testFun3과 같이 코드가 여러 줄인 것을 람다로 대체 가능
val lamda4 = {a1:Int, a2:Int ->
    var r1 = a1 + 100
    var r2 = a2 + 200
    // 제일 마지막에 작성한 값, 변수의 값, 수식의 결과를 최종 결과로 반환함
    r1 + r2
}
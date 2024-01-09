// 익명 함수 : 함수의 이름이 없는 함수
// 함수를 변수에 담아서 변수를 통해 호출해야 함

fun main() {
    testFun1()

    // Kotlin은 이름을 가지고 있는 함수를 다른 변수에 담는 것 불가능
    // val testFun2 = testFun1

    // 익명 함수 호출
    // 익명 함수를 가지고 있는 변수를 통해 호출
    testFun3()
}

fun testFun1() {
    println("testFun1")
}

// 익명 함수
val testFun3 = fun() {
    println("익명 함수")
}
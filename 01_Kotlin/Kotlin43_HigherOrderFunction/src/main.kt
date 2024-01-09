// 고차함수 : 매개변수로 함수를 받거나 함수를 반환하는 함수

fun main() {
    // 고차함수에 전달하는 함수는 익명함수를 사용
    val t1 = fun(x1:Int, x2:Int) : Int{
        return x1 + x2
    }
    testFunc1(100, 200, t1)

    val t2 = fun(x1:Int, x2:Int) : Int{
        return x1 - x2
    }
    testFunc1(100, 200, t2)

    testFunc1(100, 200, fun(x1:Int, x2:Int):Int{
        return x1 * x2
    })

    // 람다식 받는 것 가능
    val lambda1 = {x1:Int, x2:Int -> x1 / x2}
    testFunc1(100, 200, lambda1)

    testFunc1(100, 200, {x1:Int, x2:Int -> x1 % x2})

    val m2 = testFunc2(100)
    val result1 = m2(200, 300)
    println(result1)

    val m3 = testFunc3(100)
    val result2 = m3(200, 300)
    println(result2)

    // testFunc1 처럼 매개변수들 중에 제일 마지막에 있는 것이 함수나 람다를 받는 매개변수일 경우
    testFunc1(100, 200, {x1:Int, x2:Int -> x1 + x2})

    // 제일 마지막 매개변수가 람다나 함수를 받은 매개변수이고 람다를 전달할 경우 다음과 같이 작성할 수 있음
    testFunc1(100, 200) {x1: Int, x2: Int ->
        x1 + x2
    }

    testFunc4({x1, x2 -> x1 + x2})
    testFunc4 {x1, x2 ->
        x1 + x2
    }
}

// 매개변수로 함수를 받는 함수
// 함수를 받는 매개변수의 타입은 (매개변수 타입) -> 반환값타입 형태로 작성함
fun testFunc1(a1:Int, a2:Int, m1:(Int, Int) -> Int){
    val r1 = m1(a1, a2)
    println("testFunc1 r1 : $r1")
}

// 함수를 반환하는 함수
// 함수의 반환타입을 '(반환할 함수의 매개변수들의 타입) -> 반환타입' 형태로 작성
fun testFunc2(a1:Int) : (Int, Int) -> Int {

    // 함수 내부에 선언된 변수
    //val a1 = 100

    return fun(x1:Int, x2:Int):Int {
        // 반환되는 함수 안에서 함수를 반환하는 함수의 변수나 매개변수를 사용하는 것 가능
        return x1 + x2 + a1
    }
}

fun testFunc3(a1:Int) : (Int, Int) -> Int {
    return {x1:Int, x2:Int -> x1 + x2 + a1}
}

// 함수나 람다식을 받는 매개변수만 있는 함수
fun testFunc4(m1:(Int, Int) -> Int){
    val r1 = m1(100, 200)
    println(r1)
}
// infix 함수 : 함수의 호출을 연산자 사용하는 것처럼 사용할 수 있는 함수

fun main() {
    // infix 함수 호출
    val r1 = 100 add2 200
    println("r1 : $r1")

    val r2 = 10 isMultiple 2
    println("r2 : $r2")

    val r3 = 10 isMultiple 3
    println("r3 : $r3")

    val t1 = TestClass1(100, 200)
    val t2 = TestClass1(10, 20)

    // t1을 통해서 add2를 호출해서 t2에 접근 -> this = t1
    val t3 = t1 add2 t2
    println(t3.number1)
    println(t3.number2)
}

// 기본 자료형에 대한 infix 함수
// 자료형.함수이름(매개변수)
infix fun Int.add2(a1:Int) : Int {
    // 여기에서 this는 첫 번째 값을 의미
    return this + a1
}

infix fun Int.isMultiple(a1: Int) : Boolean {
    val r1 = this % a1 == 0
    return r1
}

// 클래스 안에서 infix 함수
class TestClass1(var number1: Int, var number2: Int) {
    infix fun add2(target: TestClass1) : TestClass1 {
        val r1 = this.number1 + target.number1
        val r2 = this.number2 + target.number2
        val obj1 = TestClass1(r1, r2)
        return obj1
    }
}
//

fun main() {
    val obj1 = TestClass1(100, 200)
    val obj2 = TestClass1(10, 20)

    // 더하기
    val obj3 = obj1 + obj2
    println("obj3.a1 : ${obj3.a1}")
    println("obj3.a2 : ${obj3.a2}")
    println()

    // 빼기
    val obj4 = obj1 - obj2
    println("obj4.a1 : ${obj4.a1}")
    println("obj4.a2 : ${obj4.a2}")
}

class TestClass1(var a1:Int, var a2:Int) {
    // 더하기 연산자를 사용하면 호출되는 메서드
    // 연산자를 기준으로 좌측의 객체를 통해 메서드가 호출되고
    // 애개변수로 우측에 있는 객체의 주소값 전달됨
    operator fun plus(target: TestClass1) : TestClass1 {
        val r1 = this.a1 + target.a1
        val r2 = this.a2 + target.a2

        val result = TestClass1(r1, r2)
        return result
    }

    operator fun minus(target:TestClass1) : TestClass1{
        val r1 = this.a1 - target.a1
        val r2 = this.a2 - target.a2

        val result = TestClass1(r1, r2)
        return result
    }
}
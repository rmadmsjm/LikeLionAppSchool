// Any
// 코틀린에서 모든 클래스가 직접 혹은 간접적으로 상속 받는 클래스
// 클래스를 정의할 때 상속 관계를 설정하지 않으면 Any 클래스를 상속 받음
// Any 안에는 클래스가 갖춰야 할 기본적인 기능이 들어 있음

fun main() {
    // 코틀린에서 모든 클래스는 Any를 상속 받기 때문에 모든 객체는 Any 타입 변수에 담을 수 있음
    var a1:Any = TestClass1()
    var a2:Any = TestClass2()
    println("a1 : $a1")
    println("a2 : $a2")

    // 객체의 주소값을 가지고 있는 변수를 출력할 경우
    // toString 메서드를 호출하여 메서드가 반환하는 문자열을 출력하게 됨
    val s1 = a1.toString()
    val s2 = a1.toString()
    println("s1 : $s1")
    println("s2 : $s2")

    val t3 = TestClass3(100, 200)
    println("t3 : $t3")
}

// 상속 관계를 설정하지 않았지만 Any를 상속 받음
class TestClass1
class TestClass2

class TestClass3(var a1:Int, var a2:Int) {
    // toString 메서드 구현
    // toString : 객체를 문자열로 변환하는 의미의 메서드
    // 객체의 주소값을 가지고 있는 변수를 출력하면 toString 메서드가 호출되고 반환하는 문자열을 출력함
    override fun toString(): String {
        // 강사는 객체가 가지고 있는 property의 값을 출력해 보는 코드를 넣어둠
        println("a1 : $a1")
        println("a2 : $a2")
        return "TestClass3를 통해 만든 객체"
    }
}
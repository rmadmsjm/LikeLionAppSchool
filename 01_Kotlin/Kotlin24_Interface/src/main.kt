// 인터페이스 무슨 차이
// 인터페이스 장점

// 인터페이스
// 메서드 overriding 강제성을 주기 위해 추상 클래스를 사용함
// Kotlin(Java 포함)은 단일 상속만 지원하기 때문에 사용할 추상 클래스가 여러 개라면
// 추상 클래스 하나당 하나의 자식 클래스를 만들어야 함
// 만약 추상 매서드를 가지고 있는 것이 인터페이스라면 하나의 클래스 안에서 여러 인터페이스가 가진 추상 메서드를 overriding 가능

// 인터페이스도 상속 관계에서 객체의 주소값을 부모 타입의 변수에 담을 수 있는 것처럼 인터페이스 타입의 변수에도 담을 수 있음
// 이를 통해 overriding한 메서드 호출 가능

// 클래스 vs 인터페이스
// 인터페이스는 객체 생성을 하기 위한 설계도가 아님
// 클래스와 인터페이스 관계에서 상속 관계가 성립할 수 없음
// 인터페이스를 가지고 객체를 생성하는 것이 불가능하기 때문에 객체와 같이 데이터를 관리하는 것도 불가능

fun main() {
    val t1 = SubClass1()
    val t2 = SubClass2()

    testFun1(t1)
    testFun2(t2)
    println()

    val t10 = TestClass10()
    testFun3(t10)
    testFun4(t10)
}

// 추상 클래스
abstract class AbstractClass1 {
    abstract fun abstractMethod1()
}

abstract class AbstractClass2 {
    abstract fun abstractMethod2()
}

// 객체의 주소값을 받아 메서드를 호출하는 함수
fun testFun1(t100:AbstractClass1) {
    t100.abstractMethod1()
}

fun testFun2(t200:AbstractClass2) {
    t200.abstractMethod2()
}

// Kotlin(Java 포함)은 단일 상속만 지원하기 때문에 여러 클래스 상속 불가
// class Subclass : AbstractClass1(), AbstractClass2()
// 추상 클래스 하나당 하나의 자식 클래스를 만들어줘야 한다.
class SubClass1 : AbstractClass1(){
    override fun abstractMethod1() {
        println("SubClass1의 abstractMethod1")
    }
}

class SubClass2 : AbstractClass2(){
    override fun abstractMethod2() {
        println("SubClass2의 abstractMethod2")
    }
}

// 인터페이스
interface Inter1 {
    fun interMethod1() {

    }
}

interface Inter2 {
    fun interMethod2() {

    }
}

fun testFun3(inter1:Inter1) {
    inter1.interMethod1()
}

fun testFun4(inter2:Inter2) {
    inter2.interMethod2()
}

// 인터페이스를 구현한 클래스
class TestClass10 : Inter1, Inter2 {
    override fun interMethod1() {
        println("TestClass10의 interMethod1")
    }

    override fun interMethod2() {
        println("TestClass10의 interMethod2")
    }
}


// 추상 클래스 장점 : '클래스'이기 때문에 클래스가 가진 요소를 모두 가질 수 있음, 객체 생성 불가
// 인터페이스 : 클래스가 가진 요소 모두 가질 수 없음, 추상 메서드를 overriding에 대한 사용을 하기 위한 것
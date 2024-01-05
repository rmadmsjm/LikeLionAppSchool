// 추상 메서드 : 구현되지 않은 메서드
// 추상 클래스 : 추상 메서드를 가지고 있는 클래스

// 추상 클래스는 추상 메서드를 가지고 있기 때문에 완벽한 설계도가 아님
// = 구현되지 않은 부분이 있기 때문에
// 이 때문에 추상 클래스를 통한 객체 생성 불가능

// 따라서 추상 클래스는 상속 받은 클래스를 만든 다음 추상 클래스가 가지고 있는 추상 메서드를 구현하여
// 완벽한 설계도로 만들어야만 객체를 생성해서 사용 가능

// 이는 메서드 overriding 강제성을 주기 위해 사용함

fun main() {
    // 추상 클래스의 객체 생성
    // 추상 클래스는 완벽한 클래스가 아니기 때문에 객체를 생성 불가능
    // val t1 = TestClass1()

    // 추상 클래스를 상속 받은 클래스의 객체 생성
    val sub1 = SubClass1()
    sub1.testMethod1()
    sub1.testMethod2()
    sub1.testMethod3()
}

// 추상 클래스는 abstract 키워드 붙여야 함
// 추상 클래스는 상속이 가능해야 하기 때문에 open 키워드 붙여야 함
// 추상 클래스는 상속밖에 없기 때문에 open 키워드 생략 가능하게 제공됨
// 구 버전 코틀린에서는 open을 반드시 붙여야 하는 경우 존재
abstract class TestClass1 {

    fun testMethod1() {
        println("TestClass1의 testMethod1")
    }

    fun testMethod2() {
        println("TestClass1의 testMethod2")
    }
    
    // 추상메서드
    // 중괄호 열고 닫으면 구현한 것
    // 추상 메서드는 추상 메서드임을 알리기 위해 abstract 키워드 붙여야 함
    // 추상 메서드는 overriding이 가능해야 하기 때문에 반드시 open 붙여야 함
    // -> 객체 생성 불가, 상속을 할 수 있도록 open 키워드도 필요
    // 추상 메서드는 상속밖에 없기 때문에 open 키워드 생략 가능하게 제공됨
    // 구 버전 코틀린에서는 open을 반드시 붙여야 하는 경우 존재
    abstract fun testMethod3()
}

// 추상 클래스를 상속 받은 클래스
class SubClass1 : TestClass1() {

    // 추상 메서드 구현
    override fun testMethod3() {
        println("SubClass1에서 구현한 testMethod3")
    }
}
// 중첩 클래스 : 클래스 안에 작성하는 클래스
// 클래스 안에서만 사용하는 클래스 정의할 때 많이 사용함
// 클래스 안에 있다고 해서 클래스를 가지고 있는 클래스에서만 객체를 생생할 수 있는 것은 아님
// 그러나 외부에서 객체를 생성하는 것이 불편함

fun main() {
    // 내부 클래스의 객체를 생성하기 위해서는 외부 클래스의 객체가 필요
    // 외부 클래스로 객체를 생성했다고 해도 내부 클래스의 객체가 생성되어 있지 않음
    // -> 내부 클래스 메모리에 올라가 있다고 할 수 없음
    // 내부 클래스는 외부 클래스의 멤버 -> 객체를 통해서 접근해야 함
    // 내부 클래스의 변수나 메서드에 접근할 수 있음 -> 내부 클래스 객체 생성되어 있음 -> 외부 클래스 객체 생성되어 있음

    val obj1 = Outer1()
    val obj2 = obj1.Inner1()

    obj1.outerValue1
    obj1.outerMethod1()
    obj1.Inner1()

    // 다른 위치, 파일, 패키지, 모듈에 클래스를 작성했다면 
    // 저~~어기 가서 클래스 작성하고 요~~기와서 객체 생성하고... 왔다갔다 해야 함 -> 불편함
    val obj3 = TestClass1()
    obj3.interMethod1()
    obj3.interMethod2()

    // 만약 익명 중첩클래스를 사용한다면 클래스 작성과 매서드의 오버라이딩 객체 생성을 같은 곳에서 모두 작업함
    // 단, 이름이 없는 1회용 클래스이기 때문에 이 클래스를 더이상 사용할 수 없음
    // object : 인터페이스나 추상 클래스
    val obj4 = object : Inter1 {
        override fun interMethod1() {
            println("익명 중첩 클래스의 innerMethod1")
        }

        override fun interMethod2() {
            println("익명 중첩 클래스의 innerMethod2")
        }
    }

    obj4.interMethod1()
    obj4.interMethod2()
}

// 일반 중첩 클래스
// 외부 클래스
class Outer1 {
    // 프로퍼티
    var outerValue1 = 100

    // 메서드
    fun outerMethod1(){
        println("Outer1의 outerMethod1")

        // 내부 클래스가 가지고 있는 요소를 사용 -> 오류
        // 외부 클래스의 객체를 생성했다고 해서 내부 클래스의 객체가 생성되는 것이 아니므로
        // 외부 클래스에서 내부 클래스의 요소 사용 불가능
        // println("innerValue : $innerValue1")
        // innerMethod1()
    }

    // 내부 클래스
    inner class Inner1 {
        var innerValue1 = 200

        fun innerMethod1(){
            println("Inner1의 innerMethod1 입니다")

            // 내부 클래스의 객체를 생성하려면 외부 클래스를 통해 만든 객체 필요
            // 내부 클래스를 가지고 객체 입장에서는 외부 클래스의 객체가 생성되어 있다는 것을 보장 받을 수 있음
            // 외부 클래스에 정의되어 있는 모든 멤버 사용 가능
            // -> 이것 때문에 중첩 클래스 사용
            println("OuterValue1 : $outerValue1")
            outerMethod1()
        }
    }
}

// 인터페이스나 추상 클래스를 통해 객체 생성 불가능
// 인터페이스를 구현하여 메서드를 구현한 클래스를 정의하거나 추상 클래스를 상속 받아 메서드를 구현한 클래스를 만들어야 객체 생성 가능
// 만약 이러한 클래스를 통해 생성하는 객체가 하나뿐이라면 익명 중첩 클래스를 사용하면 편하게 작업할 수 있음
interface Inter1 {
    fun interMethod1()
    fun interMethod2()
}

class TestClass1:Inter1 {
    override fun interMethod1() {
        println("TestClass1의 innermethod1")
    }

    override fun interMethod2() {
        println("TestClass1의 innermethod2")
    }
}
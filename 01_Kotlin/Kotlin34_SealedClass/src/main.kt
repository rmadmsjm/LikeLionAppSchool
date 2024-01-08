// Sealed Class : 특정 클래스를 상속 받은 클래스들을 열거형처럼 모아 관리하는 개념
// Sealed Class는 부모 클래스가 되고
// Sealed Class를 관리하는 모든 클래스는 Sealed Class를 상속 받은 자식 클래스에 해당함

fun main() {
    // 객체를 생성
    val obj1 = NumClass.OneCLass(100, 200)
    val obj2 = NumClass.TwoCLass(300)
    val obj3 = NumClass.ThreeCLass(100, 11.11)

    obj1.numMethod1()
    obj2.numMethod1()
    obj3.numMethod1()

    checkNum(obj1)
    checkNum(obj2)
    checkNum(obj3)
}

// Sealed Class는 자기 자신을 상속 받은 클래스를 모아 관리하는 개념
sealed class NumClass {
    open fun numMethod1() {
        println("NumClass의 numMethod1")
    }

    // 관리할 클래스
    // 모든 클래스는 Sealed Class를 상속 받음
    class OneCLass(var a1:Int, var a2:Int) : NumClass()
    class TwoCLass(var a1:Int) : NumClass() {
        override fun numMethod1() {
            println("TwoClass의 numMethod1")
        }
    }
    class ThreeCLass(var a1:Int, var a2:Double) : NumClass()
}

// 매개 변수로 들어오는 객체의 클래스 타입에 따라 분기해 처리함
// 함수의 매개변수에 Sealed class 타입을 넣어줌
fun checkNum(t1:NumClass) {
    // when 으로 분기할 때 is를 통해 어떤 클래스를 통해 만든 객체인지 검사함
    // 이때 스마트 캐스팅도 이루어짐
    when(t1) {
        is NumClass.OneCLass -> {
            println("OneClass")
            println(t1.a1)
            println(t1.a2)
            t1.numMethod1()
        }
        is NumClass.TwoCLass -> {
            println("TwoClass")
            println(t1.a1)
            t1.numMethod1()
        }
        is NumClass.ThreeCLass -> {
            println("ThreeClass")
            println(t1.a1)
            println(t1.a2)
            t1.numMethod1()
        }
    }
}
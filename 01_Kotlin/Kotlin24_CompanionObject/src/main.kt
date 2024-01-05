// 정적 멤버
// 변수인 경우, 프로그램 전체에서 딱 하나만 만들어 사용하는 변수를 의미
// 정적 멤버는 객체 생성 없이 바로 사용 가능한 요소
// 객체 생성할 때마다 만들어지는 게 아니고 객체 생성할 때마다 빠짐
// -> 딱 하나만 생성되기 뙈문에 객체의 멤버로 취급되지 않음

// Java 에서는 'static' 으로 사용됨

fun main() {
    // 일반 멤버 변수는 객체를 생성할 때마다 계속 생성됨
    val t1 = TestClass1(100)
    val t2 = TestClass1(200)
    println("t1.a1 : ${t1.a1}")
    println("t2.a1 : ${t2.a1}")
    println()

    t1.a1 = 1000
    println("t1.a1 : ${t1.a1}")
    println("t2.a1 : ${t2.a1}")
    println()

    // 정적 멤버는 객체를 생성하지 않고 사용함
    // 클래스를 통해 접근
    // 클래스명.멤버이름
    println("TestClass1.a2 : ${TestClass1.a2}")
    // 정적 멤버는 객체를 통한 접근이 불가능
    // println("t1.a2 : ${t1.a2}")
    // println("t2.a2 : ${t1.a2}")
    TestClass1.testMethod2()
    println()

    t1.testMethod1()
    println()

    // Java 파일에 정의된 static 멤버 사용
    // Java 파일에 정의된 정적 맴버 자유롭게 사용 가능
    println("JavaMain.javaValue1 : ${JavaMain.javaValue1}")
    JavaMain.javaMethod1()
}

class TestClass1 {
    // 일반 멤버 변수
    // 객체 생성을 해야 메모리에 올라감
    var a1: Int

    // 정적 멤버 정의
    // 객체 생성하지 않아도 메모리에 올라감
    // 따라서 클래스 이름으로 접근
    companion object {
        // 정적 멤버 변수
        // 반드시 초기화 해야 함
        var a2:Int = 100
        var a1:Int = 200

        // 정적 멤버 메서드
        // 정적 멤버 메서드는 일반 멤버 변수를 사용할 수 있는가?
        // -> 정적 멤버 메서드가 사용한 시점에 일반 멤버 변수의 객체가 생성되었는지 알 수 없어서 사용 불가
        fun testMethod2() {
            // 정적 프로퍼티 사용
            println("testMethod2 - a2 : $a2")
            // 정적 메서드에서 일반 멤버 프로퍼티를 사용 시도
            // 정적 멤버는 객체를 생성하지 않아도 사용 가능
            // 정적 멤버 입장에서는 객체가 생성되지 않았을 가능성,
            // 객체가 여러 개가 만들어져 있어서 어떤 객체의 멤버인지 확인 할 수 없는 등의 여러 가지 이유로 사용 불가
            // println("testMethod2 - a1 : $a1")
            // println("testMethod2 - a1 : ${this.a1}")
        }
    }

    constructor(a1:Int) {
        this.a1 = a1
    }

    // 일반 멤버 메서드
    // 일반 멤버 메서드에서 정적 멤버 프로퍼티나 정적 멤버 메서드를 사용 가능한가
    // -> 정적 멤버 프로퍼티나 메서드는 사용 전에 이미 메모리에 올라가 있으므로 사용 가능
    fun testMethod1() {
        println("a1 : $a1")
        // 일반 멤버 메서드에서는 정적 멤버 사용 가능
        // 정적 멤버들은 프로그램이 시작할 때 이미 메모리에 올라가 있고 그 후 개발자가 만든 코드가 동작함
        // 따라서 객체 생성 시점보다 정적 멤버들이 메모리에 올라가는 시점이 더 앞서있기에 사용 가능
        // 같은 클래스에 있는 정적 맴버 접근시에는 클래스의 이름을 생략할 수 있음
        println("a2 : $a2")
        testMethod2()
    }
}

class TestClass2 {
    companion object {
        var kotlinValue = 1000

        fun kotlinMethod() {
            println("kotlinMethod1")
        }
    }
}
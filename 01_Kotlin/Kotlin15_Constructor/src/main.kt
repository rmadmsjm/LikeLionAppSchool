// 생성자 : 클래스를 통해 객체를 생성하면 무조건 호출되는 요소
// 클래스를 통해 객체를 생성했을 때 무조건 동작해야 하는 코드가 있을 경우 생성자에 만들어줌
// 생성자는 매개변수의 형태를 다르게 하여 여러 개를 만들 수 있지만 자동으로 호출되는 생성자는 무조건 1개

fun main() {
    // 객체 생성
    val t1 = TestClass1()
    println("t1 : $t1")

    println()

    // 객체 생성 시 호출될 생성자를 선택해야 함
    // 클래스이름(매개변수의 형태)

    // 객체 생성 시 값을 전달하지 않았기 때문에 매개변수가 없는 생성자가 호출됨
    val t2 = TestClass2()
    println("t2 : $t2")

    println()

    // 객체 생성 시 정수값 2개를 전달했기 때문에 정수형 매개변수 2개가 있는 생성자가 호출됨
    val t3 = TestClass2(100, 200)
    println("t3 : $t3")

    println()

    val t4 = TestClass3(100, 200)
    println("t4.a1 : ${t4.a1}")
    println("t4.a2 : ${t4.a2}")

    // TestClass3는 매개변수가 있는 생성자를 작성햇기 때문에 매개변수가 없는 생성자가 자동으로 추가되지 않음
    // 오류
    // val t5 = TestClass3()
}

// init 코드 블럭
// 객체를 생성할 때 자동으로 동작하는 부분
// Java 코드로 변환될 때 모든 생성자의 가장 윗부분에 코드가 삽입됨
// 생성자에 만든 코드가 동작하기 전에 동작함
// 생성자를 여러 개 만들어 제공할 경우 중복되는 코드를 init 코드 블럭에 작성함

class TestClass1 {
    // init 코드 블럭
    init {
        println("TestClass1의 init 코드 블럭")
        println("객체가 생성될 때 자동으로 동작하는 부분")
        println("Java 코드로 변환될 때 모든 생성자의 상단에 삽입되는 코드")
    }
}

// 생성자
class TestClass2 {
    init {
        println("TestClass2의 init 코드 블럭")
    }
    
    constructor() {
        println("TestClass2의 매개변수 없는 생성자")
    }

    // 매개 변수의 개수나 타입을 다르게 하면 여러 개의 생성자를 제공 가능
    constructor(a1:Int, a2:Int) {
        println("TestClass2의 매개변수가 있는 생성자")
        println("a1 : $a1")
        println("a2 : $a2")
    }
}

// 생성자를 작성하지 않은 클래스는 매개변수가 없고 코드가 작성되어 있지 않은 생성자가 자동으로 추가됨
// 생성자를 작성하게 되면 매개변수가 없고 코드가 작성되어 있지 않은 생성자가 자동으로 추가되지 않음
// 이를 통해 생성자를 통해서 값을 무조건 받을 수 있음
class TestClass3 {
    var a1 = 0
    var a2 = 0

    constructor(a1: Int, a2: Int) {
        println("TestClass3의 생성자 호출")
        println("a1 : $a1")
        println("a2 : $a2")
    }
}
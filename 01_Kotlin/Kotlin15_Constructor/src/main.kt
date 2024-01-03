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

    val t6 = TestClass4(100, 200)
    // 객체의 멤버 변수를 출력
    println("t6.memberA1 : ${t6.memberA1}")
    println("t6.memberA2 : ${t6.memberA2}")

    println()

    val t7 = TestClass4()
    println("t7.memberA1 : ${t7.memberA1}")
    println("t7.memberA2 : ${t7.memberA2}")

    println()

    val t8 = TestClass5(100, 200, 300)
    println("t8.a1 : ${t8.a1}")
    println("t8.a2 : ${t8.a2}")
    println("t8.a3 : ${t8.a3}")

    println()

    val t9 = TestClass6(100, 200, 300)
    println("t9.a1 : ${t9.a1}")
    println("t9.a2 : ${t9.a2}")
    println("t9.a3 : ${t9.a3}")

    println()

    val t10 = TestClass7(10, 20, 30)
    println("t10.a1 : ${t10.a1}")
    println("t10.a2 : ${t10.a2}")
    println("t10.a3 : ${t10.a3}")
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

// this : 객체가 가지고 있는 멤버를 지정하기 위해 사용함
// 매서드나 생성자의 매개변수 이름과 객체의 멤버 변수의 이름이 같을 때 사용함
// 같은 클래스 내의 다른 생성자를 호출하고자 할 때 사용함
class TestClass4 {
    var memberA1 = 0
    var memberA2 = 0

    constructor(memberA1:Int, memberA2:Int) {
        // 여기서 사용하는 memberA1과 membaerA2는 매개변수를 지칭함
        println("memberA1 : $memberA1")
        println("memberA2 : $memberA2")
        // 생성자나 매서드의 매개변수 이름과 멤버변수의 이름이 같은 경우
        // 멤버 변수를 사용하고자 한다면 this를 사용함
        // this는 객체 자기 자신을 가리키는 키워드
        this.memberA1 = memberA1
        this.memberA2 = memberA2
    }

    // 생성자에서 다른 생성자를 호출
    // constructor(매개변수) : 호출할 다르 생성자(매개변수)
    // 생성자가 호출되면 지정한 다른 생성자를 먼저 호출하여 코드가 수행되고 다른 생성자의 수행이 끝나면 생성자의 코드가 동작함
    constructor():this(1000, 2000) {
        println("매개변수가 없는 생성자 호출")
    }
}

// 주 생성자
// 하나의 클래스에서 생성자가 여러 개 있을 경우 주 생성자를 지정할 수 있음
// 주 생성자가 아닌 다른 생성자들은 무조건 주 생성자를 호출해야 함
class TestClass5 {
    var a1 = 0
    var a2 = 0
    var a3 = 0

    constructor(a1:Int, a2: Int, a3:Int) {
        this.a1 = a1
        this.a2 = a2
        this.a3 = a3
    }
}

// 주 생성자 사용
// constructor에 명시한 변수들은 멤버변수로 정의되고 매개변수 3개를 가지고 잇는 생성자가 자동으로 추가됨
// 이 생성자는 매개변수로 돌아가는 값을 멤버변수로 저장해주는 코드가 삽입됨
// TestClass5와 같은 같은 형태로 변환됨
// class TestClass6 constructor(var a1:Int, var a2:Int, var a3:Int)
// 주 생성자 정의 시 constructor는 생략해도 됨
class TestClass6(var a1:Int, var a2:Int, var a3:Int)

// 만약 주 생성자에 정의하지 않은 멤버변수가 있다면
class TestClass7(var a1:Int, var a2:Int) {
    // 주 생성자에 정의하지 않은 멤버변수
    var a3:Int = 0

    // 다른 생성자를 만들어서 주 생성자에 정의하지 않은 변수의 값을 넣어줘야 함
    // 이때, 추가적으로 작성한 생성자는 반드시 주 생성자를 호출해야 함
    // 생성자() : this() 형태로 하여 주 생성자를 반드시 호출해야 함
    constructor(a1:Int, a2:Int, a3:Int) : this(a1, a2) {
        this.a3 = a3
    }
}
// this : 객체 자기 자신을 가리킴
// 변수 : property(멤버 변수)를 지칭하는 용도로 사용
// 생성자 : 다른 생성자를 호출하기 위해 사용
// 메서드 : 멤버 메서드와 지역 메서드를 구분하기 위해 사용

// super : 부모를 가리킴
// 변수 : 객체가 가지고 있는 property(변수)와 부모가 가지고 잇는 property(변수)를 구분하기 위해 사용
// 생성자 : 부모의 생성자를 호출하기 위해 사용
// 메서드 : 자식에서 overriding한 메서드와 부모의 메서드를 구분하기 위해 사용

fun main() {
    val t1 = TestClass1()
    t1.testMethod1(200)
    println()
    
    val t2 = TestClass2()
    println("t2 : $t2")
    println()

    t1.testMethod2()
    println()

    val sub1 = SubClass1()
    sub1.subMethod1(300)
    println()

    val super1 = SuperClass1()
    super1.superMethod2()
    sub1.superMethod2()
    println()

    sub1.subMethod2()
    println()
}

// this
class TestClass1 {
    // property
    var value1 = 100

    // 매개변수
    fun testMethod1(value1: Int) {
        // property의 이름과 매개변수의 이름이 같을 경우, 매개변수를 사용함
        println("매개변수 value1 : $value1")
        // property의 이름과 매개변수의 이름이 같은 경우
        // 매개변수가 아닌 property를 사용하겠다면 this를 통해 객체에 접근해 사용하면 됨
        println("property value1 : ${this.value1}")
    }
    
    // 코틀린은 메서드(외부) 안에 메서드(내부)를 만들 수 있음
    // 메서드(외부)내의 메서드(내부)는 메서드(외부)에서만 사용 가능
    fun testMethod2() {
        fun innerMethod1() {
            println("innerMethod1 호출")
        }
        
        innerMethod1()

        // 클래스의 멤버 메서드와 동일한 메서드를 내부에 만들어 줌
        fun testMethod3() {
            println("testMethod2 메서드의 내부 메서드 testMethod3")
        }

        // 메서드 내부에 만든 메서드가 호출됨
        testMethod3()
        // 만약 멤버 메서드를 호출하고 싶다면 this를 이용하여 호출
        this.testMethod3()
    }
    
    // 사용 불가
    // innerMethod1()

    fun testMethod3() {
        println("TestClass1의 testMethod3")
    }

}

class TestClass2 {
    // this() : 다른 생성자를 호출함
    // 만약 클래스에 주 생성자가 정의되어 있으면 반드시 주 생성자를 호출하도록 해야 함
    constructor() : this(100) {
        println("매개변수가 없는 생성자 호출")
    }

    constructor(a1:Int) {
        println("매개변수가 있는 생성자 호출")
        println("a1 : $a1")
    }
}

// super
open class SuperClass1 {
    // 자식 클래스에서 동일한 이름의 property를 만드는 것을 허용하려면 'open'을 붙여야 함
    open var superValue1 = 100

    // 생성자
    constructor() {
        println("SuperClass1의 생성자")
    }

    constructor(a1:Int) {
        println("매개변수를 가지고 있는 SuperClass1의 생성자")
        println("a1 : $a1")
    }

    // 자식 클래스에서 메서드를 재구현(overriding)을 허용하겠다면 'open'을 붙여야 함
    open fun superMethod2() {
        println("SuperClass1의 superMethod2")
    }
}

class SubClass1 : SuperClass1 {
    // 부모 클래스에 정의된 property와 동일한 이름의 property를 만들고자 한다면
    // 부모가 가지고 있는 property 중에 open property만 가능하며 앞에 'override' 키워드를 붙여야 함
    override var superValue1 = 200

    // 코틀린의 모든 클래스는 생성자에서 부모 클래스의 생성자 호출을 명시하지 않으면
    // 자동으로 부모의 생성자 중 매개변수가 없는 생성자가 호출됨
    // 만약 호출되는 부모의 생성자를 명시적으로 지칭하겠다면 super를 사용함
    constructor() : super(1000) {
        println("SubClass1의 생성자 호출")
    }

    fun subMethod1(sueprValue1: Int) {
        // 매개변수 사용
        println("superValue1 : $sueprValue1")
        // 매개변수의 이름과 property의 이르밍 같기 때문에 property에 접근하려면 'this'를 사용해야 함
        // 만약 동일명의 매개변수가 없다면 this 생략 가능
        println("this.superValue1 : ${this.superValue1}")
        // 매개변수의 이름이나 멤버 property의 이름이 부모의 property 이름과 동일한 경우
        // super를 통해 부모의 property에 접근함
        println("super.superValue : ${super.superValue1}")
    }

    // 부모 클래스의 메서드를 overriding
    override fun superMethod2() {
        println("SubClass1의 superMethod2")
    }

    fun subMethod2() {
        superMethod2()
        // 부모의 메서드를 자식에서 재구현한 경우 부모의 메서드를 호출하고자 한다면 'super' 사용
        super.superMethod2()
    }
}
// 다형성 : 어떠한 요소의 형태가 다양할 수 있다는 의미
// 객체를 생성하면 객체의 주소값이 반환되는데 이 주소값은 부모 클래스형 병수에도 담을 수 있음

fun main() {
    // 객체를 생성 후 객체 생성 시 사용한 클래스 타입의 변수에 주소값을 저장함
    val s1:SubClass1 = SubClass1()
    // 객체를 생성 후 부모 클래스 타입의 변수에 주소값을 저장함
    val s2:SuperClass1 = SubClass1()

    println("s1 : $s1")
    println("s1 : $s2")
    println()

    // 객체를 생성했을 때 사용한 클래스 타입의 변수를 통해 객체(자식 클래스로 생성)에 접근
    // 부모 영역 사용 가능
    println("s1.superValue1 : ${s1.superValue1}")
    s1.superMethod1()
    println()

    // 자기 자신에게 작성된 요소를 사용함
    // s1 변수는 객체를 생성했을 때 사용한 클래스 타입의 변수이므로 모든 것을 사용할 수 잇음
    println("s1.subValue1 : ${s1.subValue1}")
    s1.subMethod1()
    println()

    // 부모 클래스 타입의 변수를 통해 객체(자식 클래스로 생성)에 접근
    // 부모 영역 사용 가능
    println("s2.superValue1 : ${s2.superValue1}")
    s2.superMethod1()
    println()

    // Unresolved reference: subValue1, Unresolved reference: subMethod1
    // s2는 부모 클래스 타입으로 정의되어 잇지만 담겨 있는 객체의 주소값은 자식 클래스 타입의 주소값이 들어있음
    // 변수의 타입만 보고 property나 메서드 존재 여부를 확인하기 때문에
    // 부모 클래스형 변수를 통해서 자식 클래스에 정의된 요소를 사용할 수 없음
    // 이는 부모 클래스형 변수에 부모 클래스를 통해 만든 객체의 주소값이 들어잇을 가능성이 있기 때문
    // println("s2.subValue1 : ${s2.subValue1}")
    // s2.subMethod1()

    s1.superMethod1()
    s2.superMethod2()
    println()
}

open class SuperClass1 {
    val superValue1 = 100
    
    fun superMethod1() {
        println("SuperClass1의 메서드")
    }

    // 자식 클래스에서 overriding 하는 것을 허용하려면 'open' 키워드 붙임
    open fun superMethod2() {
        println("SuperClass1의 superMethod2")
    }
}

class SubClass1 : SuperClass1() {
    val subValue1 = 200

    fun subMethod1() {
        println("SubClass1의 메서드")
    }

    // overriding : 부모가 가지고 있는 메서드를 다시 구현하는 개념
    // 매개변수의 형태, 이름 등 모든 것이 같아야 함
    // overriding한 메서드는 'override' 키워드 붙여야 함
    override fun superMethod2() {
        // 만약 부모 메서드를 호출하고자 한다면 super를 통해 접근함
        super.superMethod2()
        println("SubClass1의 superMethod2")
    }
}
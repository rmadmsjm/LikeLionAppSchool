import jdk.incubator.vector.VectorOperators.Test

// null : 값은 값인데 의미가 없는 값
// Java 에서 객체의 주소값을 담을 변수에 아직 담을 객체의 주소값이 없을 경우 넣어주는 값
// Kotlin 은 지연 초기화가 있기 때문에 변수에 null을 넣지 않아도 됨
// 하지만 호출하는 메서드가 null을 반환하는 경우도 있기 때문에 null 값에 대한 대비가 필요

// null 안전성 : 변수를 통해 객체를 사용할 때 null 값이 들어 있기 때문에 발생되는 오류를 예방하는 작업
// 변수에 null 값이 들어가지 못하도록 해줌
// 변수에 null 값이 들어가 있을 경우 객체의 접근을 막음
// 변수에 null 값이 들어가 있을 경우 메서드의 호출이나 프로퍼티 사용을 취소시킴
// 변수에 null 값이 들어가 있을 경우 메서드를 호출하거나 프로퍼티 사용을 취소시키고 지정된 기본값을 사용하게 함

fun main() {
    // null 을 허용하지 않는 프로퍼티
    // var a1:TestClass1 = null

    // null을 허용하는 프로퍼티
    // null 을 허용하는 프로퍼티를 선언할 때는 타입을 반드시 작성해야 함
    // -> ? 작성 시 타입을 작성해야 함
    var a2:TestClass1? = null
    // var a3? = null
    // var a3? = "hello"

    // 변수에 null 값이 들어있는 형태에서 메서드 호출
    // a2.testMethod() -> 오류
    // 오류 발생(NullPointerException)
    // a2!!.testMethod()

    // !! : null 을 허용하는 변수에 저장되어 있는 값을 null 을 허용하지 않는 형태로 변환함
    // null 을 허용하는 변수에 저장되어 있는 값을 null 을 허용하지 않는 변수에 담을 때 사용
    // 이때, null 값이 들어 있다면 오류 발생
    val t1:TestClass1 = TestClass1()
    val t2:TestClass1? = TestClass1()
    val t3:TestClass1? = null

    // 1) null 을 허용하지 않는 타입 변수에 null 을 허용하지 않는 타입의 변수의 값을 넣음
    val t4:TestClass1 = t1

    // 2) null 을 허용하는 타입의 변수 뒤에 !!를 붙여서 null 을 허용하지 않는 타입으로 변경함
    // 이때 null 값이 아닌 객체의 주소값이 들어 있으므로 정상 변환됨
    val t5:TestClass1 = t2!!
    println("t4 : $t4")
    println("t5 : $t5")

    // 3) null을 허용하는 타입의 변수 뒤에 !!를 붙여서 null 을 허용하는 타입의 값으로 변경함
    // 이때 null 값이 들어 있으므로 변환할 때 오류 발생
    // 변수에 null 아닌 객체의 주소값이 들어 있다는 것을 100% 보장할 수 있을 경우에만 사용
    // -> null 의 안정성 확보 X
    // val t6:TestClass1 = t3!!
    // println("t6 : $t6")

    // '변수 ?: 기본값' : null 을 허용하는 변수를 사용할 때 null 이 들어가 있을 경우 객체 대신 기본값으로 설정되어 있는 것을 전달해줌
    // 변수에 null 값이 들어가 있을 경우 객체를 생성해서 반환해주는 작업을 할 때 사용함
    var t7:TestClass1? = null
    var t8:TestClass1? = TestClass1()

    // t7이 null 이면 t9에 새로운 객체를 생성에서 넣어라
    var t9:TestClass1 = t7?:TestClass1()
    var t10:TestClass1 = t8?:TestClass1()

    println("t7 : ${t7}, t9 : $t9")
    println("t8 : ${t8}, t10 : $t10")

    // '?' : 객체가 가지고 있는 프로퍼티나 메서드를 사용할 때 사용하는 연산자
    // 변수에 null 이 들어가 있으면 수행이 무시됨
    // 변수에 객체의 주소값이 들어 있다면 객체에 접근해 프로퍼티나 메서드를 사용함
    val t11:TestClass1? = null
    val t12:TestClass1? = TestClass1()

    // 프로퍼티
    // 객체를 가지고 있는 변수에 null 이 들어 있으면 프로퍼티 접근이 중단되고 null 반환함
    println("t11.testValue1 : ${t11?.testValue1}")
    println("t12.testValue1 : ${t12?.testValue1}")

    // 메서드
    // null 이 들어 있으면 메서드 호출이 취소됨
    t11?.testMethod()
    t12?.testMethod()
}

class TestClass1 {
    var testValue1 = 100

    fun testMethod() {
        println("TestClass1의 testMethod")
    }

     // 값을 반환하는 메서드
    fun test2Method():Int {
        return 100
    }
}

// 지연 초기화
// Kotlin은 property를 정의하면 무조건 값을 저장해야 함
// 이는 개발자가 값을 저장하지 않았는데 property를 사용하려고 하는 것을 예방하기 위함
// 코드를 수행시켜서 얻어진 값을 저장해야 하는 경우 property에 일단 아무 값이나 넣어두고 나중에 얻어진 값을 저장하도록 해야 함
// 지연 초기화를 이용하면 property를 정의할 때 저장할 값을 지정하지 않고 나중에 지정해도 됨

fun main() {
    val t1 = TestClass1()
    println("t1.a1 : ${t1.a1}")
    println("t1.a2 : ${t1.a2}")
    println("t1.a3 : ${t1.a3}")
    println("t1.a4 : ${t1.a4}")

    t1.testMethod1()
}

class TestClass1 {
    // Kotlin에서 property를 정의할 때 저장할 값을 무조건 지정해야 함
    var a1:Int = 100
    var a2 = 200

    // property 정의 시 저장할 값을 지정하지 않았지만
    // init 블럭이나 생성자에서 값을 저장하는 코드를 작성해 두면 오류가 발생하지 않음
    // 이는 객체를 생성하면 무조건 init 블럭과 생성자의 코드가 동작하기 때문에
    // property에 값이 저장되는 보장을 받을 수 있어 오류가 발생하지 않음
    var a3:Int
    var a4:Int

    // 지연 초기화
    // init 블럭이나 생성자에서 값을 저장하지 않고 메서드에서 값을 저장하는 경우 사용함
    // var 변수만 가능
    // Int, Float, Double과 같은 기본 자료형은 사용할 수 없음
    lateinit var a5:String

    fun testMethod1() {
        // lateinit property는 사용 번에 반드시 초기화 되었는지 검사하는 것이 좋음
        if (::a5.isInitialized) {
            println("step1 : $a5")
        }
        // lateinit property에 값을 저장함
        a5 = "안녕하세요"
        if (::a5.isInitialized) {
            println("step2 : $a5")
        }
    }

    // val property도 init이나 생성자에서 초기화 해주는 코드를 넣으면
    // property 정의 시 저장할 값을 지정하지 않아도 됨
    val a6:Int
    val a7:Int

    // lazy를 사용하면 property에 저장될 값을 어떠한 처리를 통해 구해 저장할 수 있음
    val a8:Int by lazy {
        val temp = 1 + 2+ 3 + 4 + 5
        // 마지막에 작성한 값이나 변수의 값이 property에 저장됨
        temp
    }

    init {
        a4 = 400
        a6 = 600
    }

    constructor() {
        a3 = 300
        a7 = 700
    }
}
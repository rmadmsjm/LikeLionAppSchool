import kotlin.reflect.KClass

// Reflection : 프로그램 실행 중에 객체에 대한 다양한 정보를 파악 가능

fun main() {
    // 클래스 타입
    // 1) KClass<클래스타입> : 지정된 클래스의 타입을 파악함 (Kotlin 클래스)
    val a1:KClass<String> = String::class
    println("String의 Kotlin에서의 타입 : $a1")

    // 2) Class<클래스타입> : 지정된 클래스의 타입을 파악함 (Java 클래스)
    val a2:Class<String> = String::class.java
    println("String의 Java에서의 타입 : $a2")
    println()

    // 변수를 통해 접근할 수 있는 객체의 클래스 타입 파악함
    val str1 = "안녕하세요"
    val a3:KClass<out String> = str1::class
    println("str1을 통해 접근할 수 있는 객체의 클래스 타입(Kotlin) : $a3")
    val a4:Class<out String> = str1::class.java
    println("str1을 통해 접근할 수 있는 객체의 클래스 타입(Java) : $a4")
    println()

    val test1 = TestClass()
    val a7 = test1::class
    val a8 = test1::class.java
    println("test1의 클래스 타입(Kotlin) : $a7")
    println("test1의 클래스 타입(Java) : $a8")
    println()

    // 클래스 정보 분석
//    println("추상 클래스 인가? : ${test1::class.isAbstract}")
//    println("Companion 클래스 인가? : ${test1::class.isCompanion}")
//    println("Data 클래스 인가? : ${test1::class.isData}")
//    println("Final 클래스 인가? : ${test1::class.isFinal}")
//    println("open 클래스 인가? : ${test1::class.isOpen}")
//    println("중첩 클래스 인가? : ${test1::class.isInner}")
//    println("Sealed 클래스 인가? : ${test1::class.isSealed}")
}

class TestClass
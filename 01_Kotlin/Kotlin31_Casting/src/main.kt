fun main() {
    // 객체 생성
    // 부모 클래스형 타입에 담음
    val obj1:SuperClass1 = SubClass1()
    // 구현한 인터페이스형 타입에 담음
    val obj2:Inter1 = SubClass2()

    // 객체의 주소값을 가지고 있는 변수의 타입이 부모 클래스 타입이므로
    // 부모가 가지고 있는 메서드이거나 자식에서 overriding한 메서드만 호출할 수 있음
    obj1.superMethod1()
    // obj1.subMethod1()

    // 객체의 주소값을 가지고 잇는 변수의 타입이 구현한 인터페이스 타입이므로
    // overriding한 메서드만 호출할 수 있음
    obj2.interMethod1()
    // obj2.subMethod2()
    println("---------------------------")


    // as : 객체의 주소값을 가지고 잇는 변수의 타입을 다른 클래스 타입으로 변환함
    // obj1 의 타입을 SubClass1로 변환하고 obj1 에 담긴 값을 obj3에 넣어줌
    val obj3 = obj1 as SubClass1
    obj1.superMethod1()
    obj1.subMethod1()

    obj3.superMethod1()
    obj3.subMethod1()

    // 위의 클래스는 obj1에 SubClass1을 가지고 만든 객체의 주소값이 담겨져 있끼 때문에 가능
    // 만약 다른 클래스 타입으로 변환할 경우 오류 발생
    // ClassCastException
    // val obj4 = obj1 as String

    // is : 객체를 생성했을 때 사용했던 클래스가 무엇인지 확인 가능
    // 또한, 객체를 생성했을 때 사용했던 부모 클래스도 확인 가능
    // 의미는 객체에 지정한 클래스 부분이 있는지 확인하는 것
    val obj5 = SuperClass1()
    val chk1 = obj5 is SubClass1
    val chk2 = obj5 is SuperClass1
    val chk3 = obj5 is Inter1

    println("chk1 : $chk1")
    println("chk2 : $chk2")
    println("chk3 : $chk3")

    println("---------------------------")

    // 스마트 캐스팅 : 특정 조건을 만족하면 자동으로 형변환이 이루어지는 것을 의미
    val obj6:SuperClass1 = SubClass1()
    // SubClass1 부분이 있는지 검사함
    // 아래와 같이 객체에 특정 클래스 부분이 있는지 검사하는 코드는
    // 해당 타입으로 형변환 한 다음에 메서드나 프로퍼티를 호출하려고 하는 경우가 많음
    // 이제 코틀린은 검사 대상 타입으로 변환까지 해줌
    // obj6. subMethod1()

    if(obj6 is SubClass1) {
        obj6. subMethod1()
    }

    // 위의 if문이 끝나면 원래의 타입으로 돌아옴
    // obj6. subMethod1()

    // 매개변수가 Any인 함수 호출
    // Any는 모든 클래스의 최상위 부모이고, Kotiln에서 기본 자료형(Int, Float ...)도 사실 클래스
    anyFun(obj1)
    anyFun(obj2)
    anyFun(100)
    anyFun("안녕하세요")

    // 코틀린에서 사용하는 모든 기본 자료형은 사실 클래스임
    // 코틀인은 모든 값을 객체로 관리한다 생가하면 됨
    val str1 = "100"
    val number1 = str1.toInt()
    if(number1 is Int) {
        println("정수로 변환됨")
    }

    val str2 = number1.toString()
    if(str2 is String) {
        println("문자열로 변환됨")
    }
}

open class SuperClass1 {
    fun superMethod1() {
        println("SuperClass1의 superMethod1")
    }
}

interface Inter1 {
    fun interMethod1()
}

class SubClass1 : SuperClass1() {
    fun subMethod1() {
        println("SubClass1의 subMethod1")
    }
}

class SubClass2 : Inter1 {
    fun subMethod2() {
        println("SubClass2의 subMethod2")
    }

    override fun interMethod1() {
        println("Subclass2의 interMethod1")
    }
}

// 매개변수로 Any 타입을 가지고 있는 함수
fun anyFun(obj:Any) {
    // is를 통해 클래스 타입을 확인하고 스마트 캐스팅을 이용해 메서드를 호출할 수 있음
    if(obj is SubClass1) {
        obj.subMethod1()
    }
    if(obj is SubClass2) {
        obj.subMethod2()
    }
    if(obj is Int){
        println("정수 입니다")
    }
    if(obj is String){
        println("문자열 입니다")
    }
}
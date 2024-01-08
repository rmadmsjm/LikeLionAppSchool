// Data Class
// 객체의 멤버를 보다 쉽게 관리할 수 있는 기능이 추가됨
// abstract, open, sealed inner 클래스로 정의할 수 없음
// 반드시 주생성자가 있어야 함

fun main() {
    // 일반 클래스의 객체 생성
    var obj1 = TestClass1(100 ,200)
    var obj2 = TestClass1(100, 200)

    // 데이터 클래스 객체 생성
    var obj3 = TestClass2(100 ,200)
    var obj4 = TestClass2(100, 200)

    // 일반 클래스를 통해 생성한 객체 비교
    // 코틀린에서 == 연산자를 사용하면 equals 메서드를 호출하여 그 결과를 반환함
    // 만약 클래스에 equals 메서드를 구현하지 않으면 변수에 저장되어 있는 값을 비교함
    // 즉, 변수가 가지고 있는 객체의 주소값이 같은지를 비교함
    if(obj1 == obj2){
        println("obj1과 obj2는 같은 객체입니다")
    } else {
        println("obj1과 obj2는 다른 객체입니다")
    }

    // 데이터 클래스를 통해 생성한 객체 비교
    // 데이터 클래스는 equals 메서드가 구현되어 있으며 객체가 가지고 있는 프로퍼티의 값이 같은지 비교함
    // 주 생성자에 되어있는 것들로 한정
    if(obj3 == obj4){
        println("obj3과 obj4의 주 생성자에 정의된 프로퍼티의 값이 같습니다")
    } else {
        println("obj3과 obj4의 주 생성자에 정의된 프로퍼티의 값이 다릅니다")
    }

    // copy : 객체를 복제하여 새로운 객체를 생성함
    // 같은 클래스를 통해 객체를 생성하고 주 생성자에 정의되어 있는 프로퍼티의 값을 추출해 새롭게 생성된 객체에 저장함
    val obj5 = obj3.copy()
    println("obj5 : ${obj5.hashCode()}")
    println("obj3 : ${obj3.hashCode()}")
    println("obj5.a1 : ${obj5.a1}")
    println("obj5.a2 : ${obj5.a2}")
    println("obj3.a1 : ${obj3.a1}")
    println("obj3.a2 : ${obj3.a2}")

    obj5.a1 = 1000
    println("obj5.a1 : ${obj5.a1}")
    println("obj3.a1 : ${obj3.a1}")

    // toString : 주생성자에 정의되어 있는 프로퍼티의 값을 확인할 수 있는 형태로 구현되어 있음
    println("obj1 : $obj1")
    println("obj3 : $obj3")

    // componentN : 주 생성자에 정의되어 있는 프로퍼티의 개수 만큼 생성됨
    // component1 은 첫 번째 프로퍼티를 의미하고 component2는 두 번째 프로퍼티를 의미함
    // 각 프로퍼티가 가지고 있는 값을 반환할 수 있음
    val num1 = obj3.component1()
    val num2 = obj3.component2()
    //obj3.a1
    //obj3.a2

    println("num1 : $num1")
    println("num2 : $num2")

    // 첫번째 변수인 num3 에는 component1을 호출하여 반환하는 값이 저장되고
    // 두번째 변수인 num4 에는 component2를 호출하여 반환하는 값이 저장됨
    val (num3, num4) = obj3
    println("num3 : $num3")
    println("num4 : $num4")
}

// 일반 클래스
class TestClass1(var a1:Int, var a2:Int)

// 데이터 클래스
data class TestClass2(var a1:Int, var a2:Int)
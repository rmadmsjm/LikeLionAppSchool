// 접근 제한자
// 클래스, 메서드, 변수 등에 대한 접근 권한을 설정
// 무차별적인 접근으로 인한 동작 순서의 문제, 잘못된 데이터의 저장 등을 예방할 수 있는 수단

// 클래스
// private : 파일이 같은 경우에만 사용 가능
// public : 다른 파일, 다른 패키지, 다른 모듈에서도 사용 가능 (기본)
// internal : 패키지나 모듈이 다르면 사용 불가능

// 변수, 메서드
// private : 모든 경우에 사용 불가능
// public : 모든 경우에 사용이 가능 (기본)
// protected : 상속 관계에서만 사용 가능, 패키지와 모듈이 달라도 사용 가능
// internal : 모듈이 다르면 사용 불가능

// 생략하면 public으로 설정됨
// internal의 경우 모듈이 같으면 public과 동일

import kr.co.lion.pkg1.*
import kr.co.lion.pkg2.*

fun main() {
    // 같은 파일에 있는 클래스 사용
    // 접근 제한자에 관계없이 모두 사용 가능
    val obj10 = PrivateClass1()
    val obj11 = PublicClass1()
    val obj12 = InternalClass1()
    println("obj10 : $obj10")
    println("obj11 : $obj11")
    println("obj12 : $obj12")

    // 같은 모듈, 같은 패키지, 다른 파일
    // private는 파일이 다르면 사용 불가
    // val obj20 = PrivateClass2()
    // public은 무조건 사용 가능
    val obj21 = PublicClass2()
    // 모듈이 같으면 pulic과 동일
    val obj22 = InternalClass2()
    // println("obj20 : $obj20")
    println("obj21 : $obj21")
    println("obj22 : $obj22")

    // 같은 모듈, 다른 패키지
    // 파일이 달라 사용 불가
    //val obj30 = PrivateClass3()
    val obj31 = PublicClass3()
    val obj32 = InternalClass3()
    println("obj31 : $obj31")
    println("obj32 : $obj32")

    // 다른 모듈
    // val obj40 = PrivateClass4()
    val obj41 = PublicClass4()
    // interanal은 모듈이 다르면 사용 불가능
    // val obj42 = InternalClass4()
    println("obj41 : $obj41")

    // 같은 파일에 있는 클래스의 객체를 생성해 변수나 메서드에 접근
    val obj50 = SuperClass1()
    // private 절대 사용 불가
    // println("obj50 : ${obj50.a10}")
    println("obj50 : ${obj50.a11}")
    // protected 객체 생성을 통한 사용에는 접근 불가
    // println("obj50 : ${obj50.a12}")
    println("obj50 : ${obj50.a13}")

    // 같은 모듈, 같은 패키지, 다른 파일
    var obj60 = SuperClass2()
    // println("obj60 : ${obj60.a20}")
    println("obj60 : ${obj60.a21}")
    // protected : 상속관계가 아니면 사용 불가능
    // println("obj60 : ${obj60.a22}")
    println("obj60 : ${obj60.a23}")

    // 같은 모듈, 다른 패키지
    var obj70 = SuperClass3()
    // println("obj70 : ${obj70.a30}")
    println("obj70 : ${obj70.a31}")
    // protected : 상속관계가 아니면 사용 불가능
    // println("obj70 : ${obj70.a32}")
    println("obj70 : ${obj70.a33}")

    // 다른 모듈
    var obj80 = SuperClass4()
    // println("obj80 : ${obj80.a40}")
    println("obj80 : ${obj80.a41}")
    // protected : 상속관계가 아니면 사용 불가능
    // println("obj80 : ${obj80.a42}")
    // interanal : 모듈이 다르면 사용 불가능
    // println("obj80 : ${obj80.a43}")
}

// 같은 파일에 있는 클래스
private class PrivateClass1
public class PublicClass1

// 클래스에는 protected 사용 불가
// protected class ProtectedClass1

internal class InternalClass1

open private class PrivateClass10
open public class PublicClass10
open internal class InternalClass10

// 같은 파일 클래스 상속
// 상속 시 자식 클래스는 부모 클래스의 접근 제한자를 따라야 함
// 같은 파일에 있는 모든 클래스들은 상속이 자유로움
private class TestClass1 : PrivateClass10()
public class TestClass2 : PublicClass10()
internal class TestClass3 : InternalClass10()

// 같은 모듈, 같은 패키지, 다른 파일의 클래스 상속
// private class TestClass4 : PrivateClass20()
public class TestClass5 : PublicClass20()
internal class TestClass6 : InternalClass20()

// 같은 모듈, 다른 패키지 클래스 상속
// private class TestClass7 : PrivateClass30()
public class TestClass8 : PublicClass30()
internal class TestClass9 : InternalClass30()

// 다른 모듈 클래스 상속
// private class TestClass10 : PrivateClass40()
public class TestClass11 : PublicClass40()
// internal class TestClass12 : InternalClass40()

// 같은 파일의 클래스
open class SuperClass1 {
    private var a10 = 100
    public var a11 = 101
    protected var a12 = 102
    internal var a13 = 103
}

// 같은 파일의 클래스 상속
class SubClass1 : SuperClass1() {
    fun subMethod() {
        // println("a10 : $a10")
        println("a11 : $a11")
        // protected 상속 관계에서 사용 가능
        println("a12 : $a12")
        println("a13 : $a13")
    }
}

// 같은 모듈, 같은 패키지, 다른 파일의 클래스 상속
class SubClass2 : SuperClass2() {
    fun subMethod2() {
        // println("a20 : $a20")
        println("a21 : $a21")
        println("a22 : $a22")
        println("a23 : $a23")
    }
}

// 같은 모듈, 다른 패키지 파일의 클래스 상속
class SubClass3 : SuperClass3() {
    fun subMethod3() {
        // println("a30 : $a30")
        println("a31 : $a31")
        println("a32 : $a32")
        println("a33 : $a33")
    }
}

// 다른 모둘의 클래스 상속
class SubClass4 : SuperClass4() {
    fun subMethod3() {
        // println("a30 : $a40")
        println("a31 : $a41")
        println("a32 : $a42")
        // interanal : 모듈이 다르면 사용 불가능
        // println("a33 : $a43")
    }
}
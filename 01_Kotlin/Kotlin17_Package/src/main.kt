// 패키지(Package) : 작업하는 파일들을 폴더별로 나눠서 관리하는 개념
// 물리적인 폴더를 만들어 사용함
// 패키지명은 모두 소문자

// import를 이용해 사용하고자 하는 것을 명시하면 패키지명을 생략하고 사용할 수 있음
// 아래의 코드는 이 파일에서 TestClass2를 사용하면 해당 파일이 kr.co.lion.pkg1에 있음을 알려주는 것
import kr.co.lion.pkg1.TestClass2

// 패키지가 달라도 동일명의 클래스를 import 하는 것은 불가능
// VM 입장에서 클래스가 어떤 패키지에 있는 것인지 명확하지 않기 때문
// import kr.co.lion.pkg2.
// 모든 클래스의 이름을 다르게 사용할 것

// 특정 패키지에 있는 모든 클래스를 import하고 싶을 때 '*' 사용
// kr.co.lion.pkg3에 있는 모든 클래스들은 패키지명을 생략하고 사용 가능
// ⚠️ 주의 : pkg3에 있는 클래스와 이름은 같지만 패키지가 다른 클래스를 import하면 오류 발생
import kr.co.lion.pkg3.*
import java.awt.List
import java.util.ArrayList


fun main() {
    // 다른 패키지의 클래스 사용
    // 사용하고자 하는 클래스와 똑같은 이름의 클래스가 여러 패키지에 있을 수 있기 때문에
    // 내가 사용하고자 하는 클래스가 어떤 패키지에 있는 것인지 명시해야 함
    var obj1 = kr.co.lion.pkg1.TestClass1()
    println("obj1.t1 : ${obj1.t1}")
    obj1.t1Method()

    // TestClass2는 import로 명시했기 때문에 패키지명 생략 가능
    var obj2 = TestClass2()
    println("obj2.t2 : ${obj2.t2}")
    obj2.t2Method()

    // 이미 임포트 된 클래스 이름과 동일한 다른 패키지의 클래스를 사용할 때는 패키지명을 생략할 수 없음
    var obj22 = kr.co.lion.pkg2.TestClass2()
    println("obj22.t22 : ${obj22.t22}")
    obj22.t22Method()

    // pkg3에 있는 모든 클래스들은 import했기 때문에 패키지명 생략 가능
    var obj3 = TestClass3()
    var obj4 = TestClass4()
    obj3.t3Method()
    obj4.t4Method()

    // 개발도구가 제공하는 자동완성 기능을 이용
    //val obj5 = List()
    //val obj6 = ArrayList()
}
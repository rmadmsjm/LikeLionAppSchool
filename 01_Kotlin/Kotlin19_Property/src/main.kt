// property : 캡슐화를 적용한 변수(private 접근 제한자를 설정한 변수)에
// 값을 저장할 수 있는 메서드(setter)나 변수의 값을 반환하는 메서드(getter)를 제공하는 것

fun main() {
    // 객체 생성
    val t1 = TestClass1()

    // 캡슐화를 적용한 변수 접근 불가
    // println("t1.value1 : ${t1.value1}")

    t1.setValue2(2000)
    println("t1.value2 : ${t1.getValue2()}")

    t1.setValue2(-2000)
    println("t1.value2 : ${t1.getValue2()}")

    // property 사용
    // Java로 변환될 때 setter 메서드 호출
    t1.value3 = 3000
    t1.value5 = 5000
    // Java로 변환될 때 getter 메서드 호출
    println("t1.value3 : ${t1.value3}")
    println("t1.value4 : ${t1.value4}")
    println("t1.value5 : ${t1.value5}")

    t1.value6 = 6000
    println("t1.value6 : ${t1.value6}")
}

class TestClass1 {
    // 캡슐화를 적용한 변수들
    private var value1 = 100
    private var value2 = 200
    // private가 아닌 접근제한자를 붙인 변수는 Java로 변환될 때 변수의 접근 제한자는 private로 변경됨
    // 이때, var로 선언된 변수 setter와 getter가 추가됨
    public var value3 = 300
    // val로 선언된 변수는 getter만 추가됨
    public val value4 = 400

    // 멤버변수 = property

    // 자동 생성되는 setter는 매개변수의 값을 그냥 멤버 변수에 저장하는 코드로 되어 있음
    // 자동 생성되는 getter는 멤버변수의 값을 반환하는 코드로 되어 있음
    // 만약 자동 생성되는 setter나 getter를 사용하지 않고 직접 정의하고 싶다면 다음과 같이 작성함
    var value5 = 500
        // getter
        get() {
            println("getter 호출")
            // field = 변수(values5)
            return  field
        }
        // setter
        set(value) {
            println("setter 호출")
            if(value > 0) {
                field = value
            }
        }

    var value6 = 600
        get() {
            return  field
        }
        set(value) {
            if(value > 50) {
                field = value
            }
        }

    // var property는 setter/getter가 모두 필요하지만 setter만 정의할 경우 getter는 기본 코드로 추가됨
    var value7 = 700
        set(value) {
            if(value > 100) {
                field = value
            }
        }

    // val property는 getter만 정의함
//    val value8 = 800
//        set(value) {
//
//        }

    // 변수에 값을 저장하는 setter
    // 변수에 접근이 불가능하기 때문에 값을 직접 저장하는 것 불가능
    // 따라서 setter를 통해 값을 저장해야 하는데 메서드이므로 코드 작성이 가능
    // 이를 통해 변수에 저장될 값의 제약 조건을 설정 가능
    public fun setValue2(value2:Int) {
        if(value2 > 0) {
            this.value2 = value2
        }
    }

    // 변수에 저장되어 있는 값을 반환하는 getter
    public fun getValue2():Int{
        return value2
    }

}
// 열거형
// 프로그램 개발 시 특정 그룹 안의 구성 요소를 정의하는 값이 필요할 때 사용함
// 월을 나타내는 단어들, 혈액형을 나타내는 단어들, 방향, 성별 등
// 값 자체는 중요하지 않고, 값이 다르기만 하면 되는 경우에 사용
// 학생 수 (X), 선생님 수(X)

fun main() {
    printDirection(Direction2.SOUTH)
    printDirection2(Direction.WEST)
    println()

    val r1 = getValue1(Direction2.SOUTH)
    val r2 = getValue2(Direction.EAST)

    println("r1 : $r1")
    println("r2 : $r2")
    println()

    printValue3(Number.TWO)
}

// 열거형 정의
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

// 정적 멤버
class Direction2 {
    companion object {
        val NORTH = 0
        val SOUTH = 1
        val WEST = 2
        val EAST = 3
    }
}

// 매개변수로 들어오는 값으로 분기하여 출력하는 함수
// 정적 멤버 사용
fun printDirection(dir:Int) {
    // 정적 멤버로 정의된 변수들은 서로 간에 어떠한 관계가 있는 것이 아니기 때문에
    // 몇가지 경우를 빼더라도 문제가 발생하지 않음
    when(dir) {
        Direction2.WEST -> println("서쪽")
        Direction2.EAST -> println("동쪽")
        Direction2.NORTH -> println("북쪽")
        Direction2.SOUTH -> println("남쪽")
    }
}

// 열거형 사용
fun printDirection2(dir:Direction) {
    // 열거형에 정의된 값들은 하나의 열거형 안에 정의된 값이라는 관계를 갖기 때문에
    // 모든 경우를 다 적거나 빠진 것에 대한 처리를 할 수 있도록 else 문을 작성해야 함
    when(dir) {
        Direction.WEST -> println("서쪽")
        Direction.EAST -> println("동쪽")
        Direction.NORTH -> println("북쪽")
        Direction.SOUTH -> println("남쪽")
    }
}

// 매개변수로 들어오는 값에 따라 값을 반환하는 하무
// 정적 멤버 사용
fun getValue1(a1:Int) = when(a1){
    Direction2.EAST -> "동쪽"
    Direction2.WEST -> "서쪽"
    Direction2.SOUTH -> "남쪽"
    Direction2.NORTH -> "북쪽"
    // 각각이 독립적인 변수들이 때문에 더 있을 수도 있지 않을까? 라는 것 때문에
    // 반드시 else를 넣어줘야 한다.
    else -> "아무방향"
}

// 열거형 사용
// 하나의 열거형 안에 정의되어 있는 모든 경우에 대해 작성해주면 else 작성하지 않아도 됨
// 만약 빠진 게 있다면 else 작성해 줌
fun getValue2(a1:Direction) = when(a1) {
    Direction.EAST -> "동쪽"
    Direction.WEST -> "서쪽"
    Direction.SOUTH -> "남쪽"
    Direction.NORTH -> "북쪽"
}

// 열거형을 정의할 때 값도 설정 가능
// 열거형 클래스의 주 생성자는 열거형 하나를 만드는 양식을 의미
// 변수의 개수는 무제한

// 하나에 대해 여러 가지 값으로 표현할 수 있는 경우(원소기호 - 원소기호, 이름, 원소번호)에 사용함
enum class Number(val num:Int, val str:String) {
    ONE(1, "일"),
    TWO(2, "이"),
    THREE(3, "삼")
}

fun printValue3(v1:Number) {
    when(v1) {
        Number.ONE -> println("일")
        Number.TWO -> println("이")
        Number.THREE -> println("삼")
    }

    when(v1.num) {
        1 -> println("1")
        2 -> println("2")
        3 -> println("3")
    }

    when(v1.str) {
        "일" -> println("1")
        "이" -> println("2")
        "삼" -> println("3")
    }
}
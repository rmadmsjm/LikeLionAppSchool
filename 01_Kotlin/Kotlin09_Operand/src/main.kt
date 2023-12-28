// 연산자
// 의미를 가지고 있는 특수 문자들을 지칭함
// 하나 이상의 값을 가지고 특정 연산을 수행하기 위해 사용하는 키워드

fun main() {
    // 최우선 연산자
    // '.'은 객체, '[]'은 배열할 때 살펴봄...
    // () : 수학과 같이 가장 먼저 계산할 부분이 있다면 ()로 묶어줌
    var a1:Int = 10 + 2 * 4
    var a2:Int = (10 +2) * 4
    println("a1 : $a1")
    println("a2 : $a2")

    // 단항 연산자
    // 피연산자가 하나인 연산자
    // 1) ! : not
    // 조건식의 결과를 반대로 부정할 때 사용
    var a3 = true
    var a4 = !a3
    var a5 = !a4
    println("a4 : $a4")
    println("a5 : $a5")

    // 2) 부호
    // + : 더하기
    // - : 양수->음수, 음수->양수
    var a6 = 100
    var a7 = +a6
    var a8 = -a6
    println("a7 : $a7")
    println("a8 : $a8")

    // 증감 연산자
    // 변수가 가지고 있는 값을 1 증가하시키거나 1 감소시킨다
    // 변수 앞에 있을 때와 뒤에 있을 때 다름
    var a9 = 100
    a9++
    println("a9 : $a9")

    var a10 = 100
    a10 = a10 + 1
    println("a10 : $a10")

    ++a9
    println("a9 : $a9")

    a9--
    println("a9 : $a9")

    --a9
    println("a9 : $a9")

    var a11 = 10
    var a12 = 10

    // 1) 증감 연산자가 앞에 있는 경우
    // 최우선 연산자를 제외한 모든 연사자들보다 먼저 수행됨
    var a13 = ++a11
    var a14 = --a12
    println("a11 : ${a11}, a13 : $a13")
    println("a12 : ${a12}, a14 : $a14")

    // 2) 증감 연산자가 뒤에 있는 경우
    // 모든 연산자들보다 가장 마지막에 수행됨
    a11 = 10
    a12 = 10
    var a15 = a11++
    var a16 = a12--
    println("a11 : ${a11}, a15 : $a15")
    println("a12 : ${a12}, a16 : $a16")

    // 산술 연산자
    // 곱하기, 나누기, 나머지 연산자가 더하기, 빼기보다 먼저 수행됨
    // 1) 더하기
    var b1 = 10 + 3
    // 2) 빼기
    var b2 = 10 - 3
    // 3) 곱하기
    var b3 = 10 * 3
    // 4) 나누기
    var b4 = 10 / 3
    var b4_1 = 10.0 / 3.0
    // 5) 나머지
    var b5 = 10 % 3
    println("b1 : $b1")
    println("b2 : $b2")
    println("b3 : $b3")
    println("b4 : $b4")
    println("b4_1 : $b4_1")
    println("b5 : $b5")

    // 관계 연산자
    // 두 값의 관계를 보고 true 또는 false를 결과로 줌
    // 1) 좌측의 값이 우측의 값보다 작은가
    var b6 = 10 < 3
    var b7 = 10 < 20
    println("b6 : $b6")
    println("b7 : $b7")

    // 2) 좌측의 값이 우측의 값보다 작거나 같은가
    b6 = 10 <= 3
    b7 = 10 <= 20
    var b8 = 10 <= 10
    println("b6 : $b6")
    println("b7 : $b7")
    println("b8 : $b8")

    // 3) 좌측의 값이 우측의 값보다 큰가
    b6 = 10 > 3
    b7 = 10 > 20
    println("b6 : $b6")
    println("b7 : $b7")

    // 4) 좌측의 값이 우측의 값보다 크거나 같은가
    b6 = 10 >= 3
    b7 = 10 >= 20
    b8 = 10 >= 10
    println("b6 : $b6")
    println("b7 : $b7")
    println("b8 : $b8")

    // 5) 좌측의 값과 우측의 값이 같은가
    b6 = 10 == 20
    b7 = 10 == 10
    println("b6 : $b6")
    println("b7 : $b7")

    // 6) 좌측의 값과 우측의 값이 다른가
    b6 = 10 != 20
    b7 = 10 != 10
    println("b6 : $b6")
    println("b7 : $b7")

    // 논리 연산자
    // 좌측과 우측의 값이 true와 false인 수식에서 연산을 하는 연산자
    // 논리 연산자 좌측과 우측에는 관계 연산자를 사용하는 연산식이 들어가는 것이 일반적
    // 1) && (and) : 좌우의 수식 결과가 모두 true인 경우만 true
    // 주어진 모든 조건에 만족하는 경우인지 확인할 때 사용함
    var c1 = 10 > 2 && 10 < 20
    var c2 = 10 < 2 && 10 < 20
    var c3 = 10 > 2 && 10 > 20
    var c4 = 10 < 2 && 10 > 20
    println("c1 : $c1")
    println("c2 : $c2")
    println("c3 : $c3")
    println("c4 : $c4")

    // 2) || (or) : 좌우의 수식 결과가 모두 false인 경우만 false, 하나라도 true -> true
    // 주어진 조건들 중에 하나라도 만족하는지 확인할 때 사용함
    c1 = 10 > 2 || 10 < 20
    c2 = 10 < 2 || 10 < 20
    c3 = 10 > 2 || 10 > 20
    c4 = 10 < 2 || 10 > 20
    println("c1 : $c1")
    println("c2 : $c2")
    println("c3 : $c3")
    println("c4 : $c4")

    // 배정 대입 연산자
    // ++, -- (증감연산자)는 번수의 값을 1 증가시키거나 1 감소시키는 역할 수행
    // 만약 증가, 감소가 아닌 다른 연산을 하거나 1이 아닌 연산에 참여할 값을 직접 지정하고자 한다면 배정 대입 연산자를 사용함
    // 산술 연산자와 같이 사용하는 경우가 많음
    var d1 = 10
    var d2 = 10
    d1 = d1 + 10
    d2 += 10
    println("d1 : $d1")
    println("d2 : $d2")
}
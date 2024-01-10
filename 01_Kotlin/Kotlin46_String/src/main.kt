import java.util.*

fun main() {
    // 문자열 생성

    // String 클래스의 생성자에 StringBuffer, StringBuilder, ByteArray, CharArray를 넣어주면
    // 가지고 있는 글자 데이터를 이용해 문자열을 생성함
    val array1 = CharArray(5) {
        'a'
    }
    val str1 = String(array1)
    println("str1 : $str1")

    // " " 로 묶어준 것도 String 객체를 생성한 것
    val str2 = "안녕하세요"

    // 원하는 번째의 글자를 가져올 수 있음
    println("str2[0] : ${str2[0]}")
    println("str2[1] : ${str2[1]}")

    // 원하는 번째의 글자를 변경?
    // String 객체는 문자를 변경하는 것이 불가능
    // str1[0] = 'A'

    // 원하는 부분의 글자들을 추출해 새로운 문자열로 생성
    // 순서값을 지정
    // 두 번째 ~ 네 번째
    val str3 = str2.substring(1..3)
    println("str3 : $str3")

    // 문자열 비교
    val str4 = "Hello World"
    val str5 = "hello world"
    val str6 = "Hello World"

    // 1) ==
    // Java에서는 문자열 객체의 주소값을 비교하지만 Kotlin에서는 문자열 값 자체를 비교함
    if(str4 == str5){
        println("str4와 str5는 같습니다")
    } else {
        println("str4와 str5는 다릅니다")
    }

    if(str4 == str6){
        println("str4와 str6은 같습니다")
    } else {
        println("str4와 str6은 다릅니다")
    }

    // 2) compareTo : 두 문자열을 비교하는 메서드
    // 문자열을 구성하는 글자의 코드값을 합산하고 그걸 뺀 결과를 반환함
    println(str4.compareTo(str5))
    println(str4.compareTo(str6))

    // compareTo의 두 번째 매개변수에 true를 넣으면 모두 소문자로 변환해 계산함
    println(str4.compareTo(str5, true))

    // equals 메서드에 두 번째 매개변수에 true를 넣어주면 대소문자를 무시하고 같은지 비교함
    if(str4.equals(str5, true)){
        println("두 문자열은 대소문자를 무시하면 같습니다")
    }

    // 구분자를 기준으로 문자열을 나눔
    val str7 = "ab_cd ef_gh"

    // 1) 띄어쓰기를 기준으로 나눔
    val r6 = str7.split(" ")
    println("r6 : $r6")

    for (temp6 in r6) {
        println(temp6)
    }

    // 2) 언더바 기준으로 나눔
    val r7 = str7.split(" ")
    println("r7 : $r7")

    // uppercase : 소문자를 대문자로 하는 문자열 생성
    // lowercase : 대문자를 소문자로 하는 문자열 생성
    val str8 = str4.uppercase()
    val str9 = str8.lowercase()
    println("str8 : $str8")
    println("str9 : $str9")

    // startsWith : ~로 시작하는지...
    // endsWith : ~로 끝나는지...
    val r10 = str4.startsWith("H")
    val r11 = str4.startsWith("A")
    val r12 = str4.endsWith("d")
    val r13 = str4.endsWith("A")

    println("r10 : $r10")
    println("r11 : $r11")
    println("r12 : $r12")
    println("r13 : $r13")

    // 글자의 수를 반환 (띄어쓰기 포함)
    println("str4의 글자수 : ${str4.length}")

    // 문자열 좌우 공백 제거
    val str20 = "    aaa     "
    println("[${str20}]")
    println("[${str20.trim()}]")
}
import java.util.Scanner

/*
주민등록 번호를 입력받아 다음과 같이 출력한다.
주민등록 번호는 - 없이 13자리의 숫자를 입력받는다.
처음 두자리는 생년을 의미한다.
세번째 네번째는 생월을 의미한다.
다섯번째 여섯번째는 생일을 의미한다.
일곱번째 숫자는 다음과 같다.
9 : 1800년대생 남성
0 : 1800년대생 여성
1 : 1900년대생 남성
2 : 1900년대생 여성
3 : 2000년대생 남성
4 : 2000년대생 여성
5 : 1900년대생 외국인 남성
6 : 1900년대생 외국인 여성
7 : 2000년대생 외국인 남성
8 : 2000년대생 외국인 여성

출력은 다음과 같이 한다

1999년 10월 21에 태어난 남성입니다
 */

fun main() {
    printString(inputNum())
}

fun inputNum() : Long {
    var scanner = Scanner(System.`in`)
    print("주민번호 13자리를 '-' 없이 입력해주세요 : ")
    var num = scanner.nextLong()
    return num
}

fun printString(num:Long) {
    var year = num / 100_000_000_000L
    var monthDay = num % 100_000_000_000L
    var month = monthDay / 1_000_000_000L
    var day = (monthDay % 1_000_000_000L) / 10_000_000L
    var gender = (num % 10_000_000L) / 1_000_000L

    var yearCheck = if (year < 10) {
        "0${year}"
    } else {
        "$year"
    }

    var brithYear = when(gender) {
        9L, 0L -> "18${yearCheck}"
        1L, 2L, 5L, 6L -> "19${yearCheck}"
        3L, 4L, 7L, 8L -> "20${yearCheck}"
        else -> ""
    }

    var genderCheck = when(gender) {
        9L, 1L, 3L, 5L, 7L -> "남성"
        0L, 2L, 4L, 6L, 8L -> "여성"
        else -> ""
    }

    println("${brithYear}년 ${month}월 ${day}일에 태어난 ${genderCheck}입니다")
}